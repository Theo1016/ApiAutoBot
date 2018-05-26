package com.alpha.apiautobot.base.rest.kucoin;

import com.alpha.apiautobot.platform.kucoin.KuCoinApiConstants;
import com.alpha.apiautobot.utils.Util;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Theo on 2018/5/21.
 */
public class KuCoinInterceptor implements Interceptor {
    private final String apiKey;

    private final String secret;

    private String endpoint;

    private String payload;

    public KuCoinInterceptor(String apiKey, String secret,String endpoint,String payload) {
        this.apiKey = apiKey;
        this.secret = secret;
        this.endpoint=endpoint;
        this.payload=payload;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String nonce = String.valueOf(new Date().getTime());
        Request original = chain.request();
        Request.Builder newRequestBuilder = original.newBuilder();
        newRequestBuilder.addHeader(KuCoinApiConstants.API_KEY_HEADER, apiKey);
        newRequestBuilder.addHeader(KuCoinApiConstants.API_TIMESTAMP, nonce);
//        String payload = original.url().query();
        // Endpoint requires signing the payload
        KuCoinApiConstants.SIGNATURE = Util.kucoinSign(endpoint,nonce, payload);
        newRequestBuilder.addHeader(KuCoinApiConstants.API_SIGNATURE, KuCoinApiConstants.SIGNATURE);
        newRequestBuilder.addHeader(KuCoinApiConstants.API_LANGUAGE, KuCoinApiConstants.HEADER_LANGUAGE);
        HttpUrl signedUrl = original.url().newBuilder().build();
        newRequestBuilder.url(signedUrl);
        // Build new request after adding the necessary authentication information
        Request newRequest = newRequestBuilder.build();
        return chain.proceed(newRequest);
    }
}
