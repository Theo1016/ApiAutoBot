package com.alpha.apiautobot.platform.huobipro;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.GZIPInputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * 火币web socket管理
 */
public class HuobiSocket extends WebSocketListener{
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private String wsUrl;
    private OkHttpClient mOkHttpClient;
    private Request wsRequest;
    private WebSocket mWebSocket;
    private Lock mLock;
    private OnSocketListener mListener;

    public HuobiSocket(String url, OkHttpClient client) {
        this.wsUrl = url;
        if(client != null) {
            this.mOkHttpClient = client;
        }
        mLock = new ReentrantLock();
    }

    private void initWebSocket() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .build();
        }
        if (wsRequest == null) {
            wsRequest = new Request.Builder()
                    .url(wsUrl)
                    .build();
        }
        mOkHttpClient.dispatcher().cancelAll();
        try {
            mLock.lockInterruptibly();
            try {
                //创建web socket
                mOkHttpClient.newWebSocket(wsRequest, this);
            } finally {
                mLock.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setOnSocketListener(OnSocketListener listener) {
        this.mListener = listener;
    }

    /**
     * 连接websocket
     */
    public void connect() {
        initWebSocket();
    }

    private void disconnect() {
//        cancelReconnect();
        if (mOkHttpClient != null) {
            mOkHttpClient.dispatcher().cancelAll();
        }
        if (mWebSocket != null) {
            boolean isClosed = mWebSocket.close(1000, "close");
            if (!isClosed) {
            }
        }
    }

    @Override
    public void onOpen(WebSocket webSocket, final Response response) {
        Log.w("HuobiSocket", "onOpen:" + response.body().toString());
        this.mWebSocket = webSocket;
        if(mListener != null) {
            if(Looper.myLooper() != Looper.getMainLooper()) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mListener.onOpen(response);
                    }
                });
            }else {
                mListener.onOpen(response);
            }
        }

    }

    @Override
    public void onMessage(WebSocket webSocket, final String text) {
        Log.w("HuobiSocket", "onMessage:" + text);
        if(mListener != null) {
            if(Looper.myLooper() != Looper.getMainLooper()) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mListener.onMessage(text);
                    }
                });
            }else {
                mListener.onMessage(text);
            }
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        final String message = uncompressToString(bytes.toByteArray());
        Log.w("HuobiSocket", "onMessage:" + message);
        try {
            JSONObject data = new JSONObject(message);
            if(data.has("ping")) {
                BeatHeart.PING ping = new Gson().fromJson(message, BeatHeart.PING.class);
                send(new BeatHeart.PONG(ping).toString());
                return;
            }
            if(mListener != null) {
                if(Looper.myLooper() != Looper.getMainLooper()) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mListener.onMessage(message);
                        }
                    });
                }else {
                    mListener.onMessage(message);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClosing(WebSocket webSocket, final int code, final String reason) {
        Log.w("HuobiSocket", "onClosing:" + code + "," + reason);
        if(mListener != null) {
            if(Looper.myLooper() != Looper.getMainLooper()) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mListener.onClosed(code, reason);
                    }
                });
            }else {
                mListener.onClosed(code, reason);
            }
        }
    }

    @Override
    public void onClosed(WebSocket webSocket, final int code, final String reason) {
        Log.w("HuobiSocket", "onClosed:" + code + "," + reason);
        if(mListener != null) {
            if(Looper.myLooper() != Looper.getMainLooper()) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mListener.onClosed(code, reason);
                    }
                });
            }else {
                mListener.onClosed(code, reason);
            }
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, final Throwable t, final Response response) {
        //TODO:尝试重连
        Log.w("HuobiSocket", "onFailure");
        if (mListener != null) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mListener.onFailure(t, response);
                    }
                });
            } else {
                mListener.onFailure(t, response);
            }
        }
    }

    //发送消息
    public boolean sendMessage(String msg) {
        return send(msg);
    }

    /**
     * 发送socket消息
     * @param msg
     * @return
     */
    private boolean send(Object msg) {
        boolean isSend = false;
        if (mWebSocket != null) {
            if (msg instanceof String) {
                isSend = mWebSocket.send((String) msg);
            } else if (msg instanceof ByteString) {
                isSend = mWebSocket.send((ByteString) msg);
            }
            //发送消息失败，尝试重连
            if (!isSend) {
                //TODO:重连
            }
        }
        return isSend;
    }

    public WebSocket getWebSocket() {
        return this.mWebSocket;
    }

    public static String bytesToString(byte[] input, String charSet) {
        ByteBuffer buffer = ByteBuffer.allocate(input.length);
        buffer.put(input);
        buffer.flip();

        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;

        try {
            charset = Charset.forName(charSet);
            decoder = charset.newDecoder();
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
            return charBuffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * 字节数组解压至string
     */
    public static String uncompressToString(byte[] bytes) {
        return uncompressToString(bytes, "UTF-8");
    }

    /**
     * 字节数组解压至string，可选择encoding配置
     */
    public static String uncompressToString(byte[] bytes, String encoding) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString(encoding);
        } catch (IOException e) {
            System.out.println("gzip uncompress to string error");
        }
        return null;
    }

}
