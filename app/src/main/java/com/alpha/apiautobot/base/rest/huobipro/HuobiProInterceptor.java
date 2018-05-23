package com.alpha.apiautobot.base.rest.huobipro;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 火币pro站Http拦截器
 */
public class HuobiProInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
                .addHeader("Accept-Language", "zh-cn")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        return chain.proceed(request);
    }
}
