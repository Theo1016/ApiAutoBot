package com.alpha.apiautobot.platform.okex;

import android.text.TextUtils;
import android.util.Log;

import com.alpha.apiautobot.base.rest.CustomGsonConverterFactory;
import com.alpha.apiautobot.base.rest.okex.OKExApiService;
import com.alpha.apiautobot.platform.AbstractPlatform;
import com.alpha.apiautobot.platform.binance.BinanceApiConstants;
import com.alpha.apiautobot.utils.Util;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.FormUrlEncoded;

public class OKExClient extends AbstractPlatform {

    public static final String API_HOST           = "https://www.okex.com";

    public static final String ACCESS_KEY = "f297ae1c-e2e3-4ee7-8ee4-86488a3ed8cf";
    public static final String SECRET_KEY = "2A4736130F3DE0102A1921281DA24F05";

    public OKExApiService apiService;
    public OkHttpClient httpClient;

    public OkHttpClient genericClient(Interceptor interceptor) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .build();
        this.httpClient = httpClient;
        return httpClient;
    }

    public OKExClient() {
        initRestful();
    }

    @Override
    public void initRestful() {
        apiService = new Retrofit.Builder()
                .baseUrl(API_HOST)
                .client(genericClient(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder newRequestBuilder = original.newBuilder();
                        newRequestBuilder.addHeader("Content-Type","application/x-www-form-urlencoded");

                        boolean isApiKeyRequired = original.header(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY) != null;
                        boolean isSignatureRequired = original.header(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED) != null;
                        newRequestBuilder.removeHeader(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY)
                                .removeHeader(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED);
                        String method = original.method();
                        // Endpoint requires sending a valid API-KEY
//                        if (isApiKeyRequired || isSignatureRequired) {
//                            if("GET".equals(method)) {
//                                url = original.url().newBuilder().addQueryParameter("api_key", ACCESS_KEY).build();
//                            }
//                        }

                        //POST默认需要签名
                        isSignatureRequired = isSignatureRequired || method.equals("POST");
                        // Endpoint requires signing the payload
                        if (isSignatureRequired) {
                            if("GET".equals(method)) {
                                String payload = original.url().query();
                                if (!TextUtils.isEmpty(payload)) {
                                    payload += "&";
                                }else {
                                    payload = "";
                                }
                                //参数添加私钥
                                payload += "api_key=" + ACCESS_KEY
                                        + "&" + "secret_key=" + SECRET_KEY;
                                //参数加密
                                String signature = Util.getMD5String(payload);
                                //构造新url
                                HttpUrl signedUrl = original.url().newBuilder()
                                        .addQueryParameter("api_key", ACCESS_KEY)
                                        .addQueryParameter("sign", signature).build();
                                newRequestBuilder.url(signedUrl);
                            }else if("POST".equals(method)) {
                                //重造requestbody
                                //1. 对参数签名 2. 使用Form表单提交
                                String body = "";
                                if (original.body() != null) {
                                    Buffer buffer = new Buffer();
                                    original.body().writeTo(buffer);
                                    body = buffer.readUtf8();
                                }
                                if(TextUtils.isEmpty(body)) {
                                    body = "{}";
                                }
                                try {
                                    JSONObject jsonObject = new JSONObject(body);
                                    jsonObject.put("api_key", ACCESS_KEY);
                                    //构造参数
                                    Iterator<String> iterator = jsonObject.keys();
                                    List<String> params = new ArrayList<>();
                                    FormBody.Builder formBuilder = new FormBody.Builder();
                                    while (iterator.hasNext()) {
                                        String key = iterator.next();
                                        String value = jsonObject.getString(key);
                                        String key_value = key + "=" + value;
                                        params.add(key_value);
                                        formBuilder.addEncoded(key, value);
                                    }

                                    //对参数排序
                                    Collections.sort(params);
                                    StringBuilder builder = new StringBuilder();
                                    for (String param : params) {
                                        builder.append(param).append("&");
                                    }
                                    //参数加入私钥
                                    builder.append("secret_key").append("=").append(SECRET_KEY);
                                    //参数签名
                                    String signature = Util.getMD5String(builder.toString());
                                    formBuilder.addEncoded("sign", signature);
                                    newRequestBuilder.method(original.method(), formBuilder.build());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }

                        // Build new request after adding the necessary authentication information
                        Request newRequest = newRequestBuilder
                                .addHeader("User-Agent",
                                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
                                .addHeader("Accept-Language", "zh-cn")
                                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                        .build();
                        return chain.proceed(newRequest);
                    }
                }))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OKExApiService.class);
    }

    @Override
    public void connection() {

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
}
