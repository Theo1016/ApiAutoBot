package com.alpha.apiautobot.base.rest;


import android.content.Context;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Theo
 */
public final class ApiClient {
    private ApiClient() {
    }
        public static String BASE_URL = "";
    public static Context context;

    public static ApiService service = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(genericClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(ApiService.class);

    public static void setContext(Context ctx) {
        context = ctx;
    }

    public static void reCreateApiService(String url){
        service = new Retrofit.Builder()
                .baseUrl(url)
                .client(genericClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(ApiService.class);
        BASE_URL = url;
    }

    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Accept", "*/*")
                                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                .addHeader("Accept-Encoding", "gzip, deflate")
                                .addHeader("Connection", "keep-alive")
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();
        return httpClient;
    }
}
