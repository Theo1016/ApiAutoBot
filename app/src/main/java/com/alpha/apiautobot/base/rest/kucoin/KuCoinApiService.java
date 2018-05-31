package com.alpha.apiautobot.base.rest.kucoin;

import com.alpha.apiautobot.domain.request.binance.NewOrderResponseType;
import com.alpha.apiautobot.domain.request.binance.OrderSide;
import com.alpha.apiautobot.domain.request.binance.OrderType;
import com.alpha.apiautobot.domain.request.binance.TimeInForce;
import com.alpha.apiautobot.domain.response.Order;
import com.alpha.apiautobot.domain.response.binance.*;
import com.alpha.apiautobot.domain.response.kucoin.BaseModel;
import com.alpha.apiautobot.domain.response.kucoin.MarketModel;
import com.alpha.apiautobot.domain.response.kucoin.TransactionOrderModel;
import com.alpha.apiautobot.platform.binance.BinanceApiConstants;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by Theo
 */
public interface KuCoinApiService {

    /**
     * Kucoin -change currency
     *
     * @return
     */
    @POST("/v1/user/change-currency")
    @FormUrlEncoded
    Call<String> changeCurrency(@Field("currency")String currency);

    /**
     * Kucoin -tick
     *
     * @return
     */
    @GET("/v1/open/tick")
    Call<String> getTick(@Query("symbol") String symbol);

    /**
     * Kucoin -Trading / List active orders
     *
     * @return
     */
    @GET("/v1/order/active")
    Call<String> listActiveOrders(@Query("symbol") String symbol,@Query("type")String type);

    /**
     * Market / List trading markets(Open)
     */
    @GET("/v1/open/markets")
    Call<String> listTradingMarkets();

    /**
     * Market / List trading symbols tick (Open)
     */
    @GET("/v1/market/open/symbols")
    Call<MarketModel> listTradingSymbolsMarkets(@Query("market") String market);

    /**
     * 挂单买卖记录
     * @param symobl
     * @param group
     * @param limit
     * @return
     */
    @GET("/v1/open/orders")
    Call<TransactionOrderModel> requestOrders(@Query("symbol") String symobl, @Query("group")String group, @Query("limit")String limit);

}
