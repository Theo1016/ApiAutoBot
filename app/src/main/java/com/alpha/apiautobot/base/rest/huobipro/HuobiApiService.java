package com.alpha.apiautobot.base.rest.huobipro;

import com.alpha.apiautobot.domain.response.huobipro.HRAccounts;
import com.alpha.apiautobot.domain.response.huobipro.HRCoins;
import com.alpha.apiautobot.domain.response.huobipro.HRSymbols;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 火币api service
 */
public interface HuobiApiService {
    @Headers("API-TYPE:Common")
    @GET("/v1/common/symbols")
    Call<HRSymbols> getCommonSymbols();

    //查询当前用户的所有账户(即account-id)，Pro站和HADAX account-id通用
    @Headers("API-TYPE:User")
    @GET("/v1/account/accounts")
    Call<HRAccounts> getAccount();

    //查询指定账户余额
    @Headers("API-TYPE:User")
    @GET("/v1/account/accounts/{accountId}/balance")
    Call<String> getBalanceByAccountId(@Path("accountId")int accountId);

    /**
     * GET /market/history/kline 获取K线数据
     * 请求参数:
     *
     * 参数名称	是否必须	    类型	        描述	        默认值	    取值范围
     * symbol	true	    string	    交易对		            btcusdt, bchbtc, rcneth ...
     * period	true	    string	    K线类型		            1min, 5min, 15min, 30min, 60min, 1day, 1mon, 1week, 1year
     * size	    false	    integer	    获取数量	    150	        [1,1000]
     * @param symbo
     * @param period
     * @param size
     * @return
     */
    @Headers("API-TYPE:User")
    @GET("/market/history/kline")
    Call<String> getKLine(@Query("symbol")String symbo, @Query("period")String period, @Query("size")Integer size);

    @Headers("API-TYPE:Common")
    @GET("/v1/common/currencys")
    Call<HRCoins> getCurrencys();

}
