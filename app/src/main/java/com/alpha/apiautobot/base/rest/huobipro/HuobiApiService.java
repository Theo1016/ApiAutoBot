package com.alpha.apiautobot.base.rest.huobipro;

import com.alpha.apiautobot.domain.request.huobipro.OrdersDelegateRequest;
import com.alpha.apiautobot.domain.response.huobipro.AccountBalance;
import com.alpha.apiautobot.domain.response.huobipro.BatchOrdersCancel;
import com.alpha.apiautobot.domain.response.huobipro.HRAccounts;
import com.alpha.apiautobot.domain.response.huobipro.HRCoins;
import com.alpha.apiautobot.domain.response.huobipro.HRSymbols;
import com.alpha.apiautobot.domain.response.huobipro.HRTimestamp;
import com.alpha.apiautobot.domain.response.huobipro.HistoryTrade;
import com.alpha.apiautobot.domain.response.huobipro.MarketDepth;
import com.alpha.apiautobot.domain.response.huobipro.MarketDetail;
import com.alpha.apiautobot.domain.response.huobipro.MarketDetailMerged;
import com.alpha.apiautobot.domain.response.huobipro.OrdersDelegate;
import com.alpha.apiautobot.domain.response.huobipro.OrdersDetail;
import com.alpha.apiautobot.domain.response.huobipro.PlaceOrdersResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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

    /*******************用户资产API****************/
    //查询当前用户的所有账户(即account-id)，Pro站和HADAX account-id通用
    @Headers("API-TYPE:User")
    @GET("/v1/account/accounts")
    Call<HRAccounts> getAccount();

    //查询指定账户余额
    @Headers("API-TYPE:User")
    @GET("/v1/account/accounts/{accountId}/balance")
    Call<AccountBalance> getBalanceByAccountId(@Path("accountId")int accountId);


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

    /**
     * GET /market/history/trade 批量获取最近的交易记录
     请求参数:

     参数名称	是否必须	类型	描述	默认值	取值范围
     symbol	true	string	交易对		btcusdt, bchbtc, rcneth ...
     size	false	integer	获取交易记录的数量	1	[1, 2000]

     */
    @GET("/market/history/trade")
    Call<HistoryTrade> getMarketHistoryTrade(@Query("symbol")String symbol, @Query("size")Integer size);

    /**
     * 获取Market Detail 24小时成交量数据
     * @param symbol
     * @return
     */
    @GET("/market/detail")
    Call<MarketDetail> getMarketDetail(@Query("symbol")String symbol);

    /**********交易API***********/
    /**
     * POST /v1/order/orders/place Pro站下单
     POST /v1/hadax/order/orders/place HADAX站下单
     请求参数

     参数名称	是否必须	类型	描述	默认值	取值范围
     account-id	true	string	账户 ID，使用accounts方法获得。币币交易使用‘spot’账户的accountid；借贷资产交易，请使用‘margin’账户的accountid
     amount	true	string	限价单表示下单数量，市价买单时表示买多少钱，市价卖单时表示卖多少币
     price	false	string	下单价格，市价单不传该参数
     source	false	string	订单来源	api，如果使用借贷资产交易，请填写‘margin-api’
     symbol	true	string	交易对		btcusdt, bchbtc, rcneth ...
     type	true	string	订单类型		buy-market：市价买, sell-market：市价卖, buy-limit：限价买, sell-limit：限价卖, buy-ioc：IOC买单, sell-ioc：IOC卖单
     响应数据:

     参数名称	是否必须	数据类型	描述	取值范围
     data
     */
    @Headers("API-TYPE:User")
    @POST("/v1/order/orders/place")
    Call<PlaceOrdersResponse> postOrdersPlace(@Body RequestBody requestBody);

    /**
     * 申请撤销一个订单请求
     * @param orderId
     * @return
     */
    @Headers("API-TYPE:User")
    @POST("/v1/order/orders/{order-id}/submitcancel")
    Callback<PlaceOrdersResponse> postSubmitcancel(@Path("order-id")String orderId);

    @Headers("API-TYPE:User")
    @POST("/v1/order/orders/batchcancel")
    Call<BatchOrdersCancel> postBatchOrdersCancel(@Body RequestBody requestBody);

    /**
     * 查询某个订单详情
     * @param orderId
     * @return
     */
    @Headers("API-TYPE:User")
    @GET("/v1/order/orders/{order-id}")
    Call<OrdersDetail> getOrdersDetail(@Path("order-id")String orderId);

    /**
     * GET /v1/order/orders 查询当前委托、历史委托
     */
    @Headers("API-TYPE:User")
    @GET("/v1/order/orders")
    Call<OrdersDelegate> getOrdersDelegate(@Body OrdersDelegateRequest request);

    /**
     * GET /market/detail/merged 获取聚合行情
     *
     */
    @GET("/market/detail/merged")
    Call<MarketDetailMerged> getMarketDetailMerged(@Query("symbol")String symbol);


    /**
     * GET /market/depth 获取 Market Depth 数据
     请求参数:

     参数名称	是否必须	类型	描述	默认值	取值范围
     symbol	true	string	交易对		btcusdt, bchbtc, rcneth ...
     type	true	string	Depth 类型		step0, step1, step2, step3, step4, step5（合并深度0-5）；step0时，不合并深度
     用户选择“合并深度”时，一定报价精度内的市场挂单将予以合并显示。合并深度仅改变显示方式，不改变实际成交价格。
     */
    @GET("/market/depth")
    Call<MarketDepth> getMarketDepth(@Query("symbol")String symbol, @Query("type")String type);

    /**
     * GET /v1/common/timestamp 查询系统当前时间
     */
    @GET("/v1/common/timestamp")
    Call<HRTimestamp> getTimestamp();

}


