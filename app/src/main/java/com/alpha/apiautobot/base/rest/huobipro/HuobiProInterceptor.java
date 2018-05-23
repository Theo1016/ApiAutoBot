package com.alpha.apiautobot.base.rest.huobipro;

import android.util.Log;

import com.alpha.apiautobot.platform.huobipro.HuobiPro;
import com.alpha.apiautobot.utils.ApiSignature;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 火币pro站Http拦截器
 */
public class HuobiProInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl httpUrl = original.url();
        String host = httpUrl.host();
        String path = httpUrl.encodedPath();
        String method = original.method();
        //构造参数
        HashMap<String, String> params = new HashMap<>();
        String query = httpUrl.query();
        String[] querys = query.split("&");
        for (String key_value : querys) {
            String[] keys = key_value.split("=");
            String key = keys[0];
            String value = keys[1];
            params.put(key, value);
        }

        //对请求方法、host、path，参数签名
        ApiSignature signature = new ApiSignature();
        signature.createSignature(HuobiPro.ACCESS_KEY, HuobiPro.SECRET_KEY, method, host, path, params);
        String url = "https://" + host + path + "?" + HuobiPro.toQueryString(params);
        HttpUrl newUrl = HttpUrl.parse(url);

        Request request = original.newBuilder()
                .addHeader("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
                .addHeader("Accept-Language", "zh-cn")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(newUrl)
                .build();

        return chain.proceed(request);
    }
}
