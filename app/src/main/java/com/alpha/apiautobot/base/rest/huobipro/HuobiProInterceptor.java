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
        String api_type = original.header("API-TYPE");
        HttpUrl newUrl = null;
        if(api_type != null && api_type.equals("User")) {
            //个人账户接口需进行参数加密
            newUrl = new ApiSignature().createSignatureUrl(chain.request());
        }else if (api_type != null && api_type.equals("Common")){
            //公共接口添加访问密钥
            newUrl = original.url().newBuilder().addQueryParameter("AccessKeyId", HuobiPro.ACCESS_KEY).build();
        }else {
            newUrl = null;
        }
        Request request = original.newBuilder()
                .addHeader("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
                .addHeader("Accept-Language", "zh-cn")
                .addHeader("Content-Type", original.method().toUpperCase().equals("GET") ? "application/x-www-form-urlencoded" : "application/json")
                .url(newUrl==null ? original.url() : newUrl)
                .build();
        Log.d("HuobiProInterceptor", request.toString());
        return chain.proceed(request);
    }
}
