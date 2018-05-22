package com.alpha.apiautobot.base.rest;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by Theo on 2018/5/21.
 */
public class BinanceInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("User-Agent", "binance/java")
                .addHeader("X-MBX-APIKEY", "iyUXyCU9JhKXp5DgWusb3ceN5NjF7NsnrfKlIFvXH3sP3RAc6UYhKuusnADm5jP7")
                .build();
        return chain.proceed(request);
    }
}
