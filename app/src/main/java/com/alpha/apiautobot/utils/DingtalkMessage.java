package com.alpha.apiautobot.utils;

import android.support.annotation.NonNull;
import android.util.Log;
import okhttp3.*;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Message {
    String msgtype = "text";
    Map<String, String> text = new HashMap<String, String>() {{
        put("content", "");
    }};
    Map<String, Boolean> at = new HashMap<String, Boolean>() {{
        put("isAtAll", true);
    }};
}

/*
    DingtalkMessage dingtalkMessage = new DingtalkMessage("xxx");
    dingtalkMessage.send("KCS 100块了！！", new Callback() {
        @Override
        public void onFailure(Call var1, IOException var2) {

        }

        @Override
        public void onResponse(Call var1, Response var2) throws IOException {

        }
    });
 */
public class DingtalkMessage {

    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private static final String LOG_TAG = "DingtalkWebhook";
    private String webhook;
    private OkHttpClient client = new OkHttpClient();

    public DingtalkMessage(String webhookToken) {
        webhook = "https://oapi.dingtalk.com/robot/send?access_token=" + webhookToken;
    }

    public void send(String message, Callback callback) {
        Message msg = new Message();
        msg.text.put("content", message);
        String json = GsonUtil.GsonString(msg);
        post(webhook, json, callback);
    }

    private void post(String url, String json, Callback callback) {
        Log.i(LOG_TAG, json);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        /*client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(LOG_TAG, e.toString());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.w(LOG_TAG, response.body().string());
                Log.i(LOG_TAG, response.toString());
            }
        });*/
        // 后面可以改成其他回调
        client.newCall(request).enqueue(callback);
    }

}