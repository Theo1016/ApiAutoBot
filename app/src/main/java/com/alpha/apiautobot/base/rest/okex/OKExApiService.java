package com.alpha.apiautobot.base.rest.okex;

import com.alpha.apiautobot.domain.request.okex.BatchTradeRequest;
import com.alpha.apiautobot.domain.request.okex.OrderHistoryRequest;
import com.alpha.apiautobot.domain.request.okex.TradeOrderRequest;
import com.alpha.apiautobot.domain.response.okex.BatchTradeResponse;
import com.alpha.apiautobot.domain.response.okex.CoinDepth;
import com.alpha.apiautobot.domain.response.okex.CoinTrades;
import com.alpha.apiautobot.domain.response.okex.OrderHistoryResponse;
import com.alpha.apiautobot.domain.response.okex.TradeOrderResponse;
import com.alpha.apiautobot.domain.response.okex.UserInfo;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OKExApiService {
    /**
     * Get /api/v1/ticker   获取OKEx币币行情
     * @param symbol
     * @return
     */
    @GET("/api/v1/ticker.do")
    Call<String> getTicker(@Query("symbol")String symbol);

    /**
     * Get /api/v1/depth 获取OKEx币币市场深度
     * 请求参数

     参数名	参数类型	必填	描述
     symbol	String	是	币对如ltc_btc
     size	Integer	否(默认200)	value: 1-200
     */
    @GET("/api/v1/depth.do")
    Call<CoinDepth> getCoinDepth(@Query("symbol")String symbol, @Query("size")Integer size);

    /**
     * Get /api/v1/trades 获取OKEx币币交易信息(600条)
     *
     * 请求参数

     参数名	参数类型	必填	描述
     symbol	String	是	币对如ltc_btc
     since	Long	否(默认返回最近成交600条)	tid:交易记录ID(返回数据不包括当前tid值,最多返回600条数据)
     * @param symbol
     * @param since
     * @return
     */
    @GET("/api/v1/trades.do")
    Call<List<CoinTrades>> getCoinTrades(@Query("symbol")String symbol, @Query("since")Long since);

    /**
     * Get /api/v1/kline 获取OKEx币币K线数据(每个周期数据条数2000左右)
     *
     * 请求参数

     参数名	参数类型	必填	描述
     symbol	String	是	币对如ltc_btc
     type	String	是	1min/3min/5min/15min/30min/1day/3day/1week/1hour/2hour/4hour/6hour/12hour
     size	Integer	否(默认全部获取)	指定获取数据的条数
     since	Long	否(默认全部获取)	时间戳，返回该时间戳以后的数据(例如1417536000000)
     * @param symbol
     * @param type
     * @param size
     * @param since
     * @return
     * 返回值说明

    [
    1417536000000,	时间戳
    2370.16,	开
    2380,		高
    2352,		低
    2367.37,	收
    17259.83	交易量
    ]
     */
    @GET("/api/v1/kline.do")
    Call<List<List<Long>>> getKLine(@Query("symbol")String symbol, @Query("type")String type,
                                    @Query("size")Integer size, @Query("since")Long since);

    /**
     * POST /api/v1/userinfo 获取用户信息
     URL https://www.okex.com/api/v1/userinfo.do	访问频率 6次/2秒
     * @return
     */
    @Headers("SIGNED:true")
    @POST("/api/v1/userinfo.do")
    Call<UserInfo> getUserInfo();

    /**
     * 获取历史订单信息
     * @param requestBody
     * @return
     */
    @POST("/api/v1/order_history.do")
    Call<OrderHistoryResponse> getOrderHistory(@Body OrderHistoryRequest requestBody);

    /**
     * POST /api/v1/trade 下单交易
     */
    @POST("/api/v1/trade")
    Call<TradeOrderResponse> tradeOrder(@Body TradeOrderRequest orderRequest);

    /**
     * POST /api/v1/batch_trade 批量下单
     URL https://www.okex.com/api/v1/batch_trade.do	访问频率 20次/2秒
     */
    @POST("/api/v1/batch_trade")
    Call<BatchTradeResponse> batchTradeOrder(@Body BatchTradeRequest batchTradeRequest);

}
