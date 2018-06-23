package com.alpha.apiautobot.platform.huobipro;

import android.text.TextUtils;
import android.util.Log;

import com.alpha.apiautobot.platform.AbstractPlatform;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * huobi socket
 */
public class HuobiSocketClient extends AbstractPlatform {
    private static final String WS_URL = "wss://api.huobi.pro/ws";

    private HuobiSocket mSocket;

    @Override
    public void initRestful() {
    }

    @Override
    public void connection() {
        mSocket = new HuobiSocket(WS_URL,
                new OkHttpClient().newBuilder()
                .pingInterval(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build());
        mSocket.setOnSocketListener(new OnSocketListener() {
            @Override
            public void onOpen(Response response) {
                //获取币种深度
//                getMarketDepth("btcusdt", 0);
                //订阅深度消息
                subscribeDepth("btcusdt", 1);
            }

            @Override
            public void onMessage(String text) {
                if(!TextUtils.isEmpty(text)) {
                }
            }

            @Override
            public void onClosed(int code, String reason) {
            }

            @Override
            public void onFailure(Throwable t, Response response) {
                if(response != null) {
                }
            }
        });
        mSocket.connect();
    }


    @Override
    public void disConnection() {

    }

    @Override
    public void getMarketList() {

    }

    @Override
    public void getTick() {

    }

    @Override
    public void getAccountInfo() {

    }

    @Override
    public void buyCoin() {

    }

    @Override
    public void sellCoin() {

    }

    @Override
    public void deposite() {

    }

    @Override
    public void withdrawal() {

    }

    /**
     * 获取币种深度
     * @param symbol 币种名称
     * @param depth 深度
     */
    public void getMarketDepth(String symbol,int depth) {
        requestMarketDepth(false, symbol, depth);
    }

    /**
     * 订阅深度消息
     */
    public void subscribeDepth(String symbol, int depth) {
        requestMarketDepth(true, symbol, depth);
    }

    /**
     * 请求深度信息
     * @param subscibe 是否订阅消息
     * @param symbol
     * @param depth
     */
    private void requestMarketDepth(boolean subscibe, String symbol, int depth) {
        try {
            JSONObject jsonObject = new JSONObject();
//            jsonObject.put(!subscibe ? "req" : "sub", "market." + symbol + ".depth.step" + depth);

            jsonObject.put(!subscibe ? "req" : "sub", "market." + symbol + ".trade.detail");
            jsonObject.put("id", String.valueOf(new Random(100).nextInt()));
            boolean isSend = mSocket.sendMessage(jsonObject.toString());
            if(isSend) {
                Log.w("HuobiSocket", "send market depth success");
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
