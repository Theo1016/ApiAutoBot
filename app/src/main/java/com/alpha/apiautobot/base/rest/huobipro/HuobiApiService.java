package com.alpha.apiautobot.base.rest.huobipro;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 火币api service
 */
public interface HuobiApiService {
    @GET("/v1/common/symbols")
    Call<JSONObject> getCommonSymbols();

    //查询当前用户的所有账户(即account-id)，Pro站和HADAX account-id通用
//    @GET("/v1/account/accounts")
    @GET
    Call<String> getAccount(@Url String url);

    //查询指定账户余额
//    @GET("/v1/account/accounts/{accountId}/balance")
    @GET
    Call<String> getBalanceByAccountId(@Url String url);

}
