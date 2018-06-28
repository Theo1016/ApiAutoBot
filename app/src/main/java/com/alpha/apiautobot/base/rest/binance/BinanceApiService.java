package com.alpha.apiautobot.base.rest.binance;

import com.alpha.apiautobot.domain.request.binance.NewOrderResponseType;
import com.alpha.apiautobot.domain.request.binance.OrderSide;
import com.alpha.apiautobot.domain.request.binance.OrderType;
import com.alpha.apiautobot.domain.request.binance.TimeInForce;
import com.alpha.apiautobot.domain.response.*;
import com.alpha.apiautobot.domain.response.binance.rest.*;
import com.alpha.apiautobot.platform.binance.BinanceApiConstants;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by Theo
 */
public interface BinanceApiService {

    /**
     * Binance -Ping
     *
     * @return
     */
    @GET("/api/v1/ping")
    Call<Void> ping();


    /**
     * Binance -serverTime binance
     *
     * @return
     */
    @GET("/api/v1/time")
    Call<ServerTime> serverTime();

    /**
     * Binance -Current exchange trading rules and symbol information
     *
     * @return
     */
    @GET("/api/v1/exchangeInfo")
    Call<ExchangeInfo> exchangeInfo();

    /**
     * Binance -Order book
     *
     * @return
     */
    @GET("/api/v1/depth")
    Call<String> depth(@Query("symbol") String symobl, @Query("limit") int limit);

    /**
     * Binance -Get recent trades (up to last 500).
     *
     * @return
     */
    @GET("/api/v1/trades")
    Call<String> trades(@Query("symbol") String symobl, @Query("limit") int limit);

    /**
     * Binance -Get older trades.
     *
     * @return
     */
    @GET("/api/v1/historicalTrades")
    Call<String> historicalTrades(@Query("symbol") String symobl, @Query("limit") int limit, @Query("fromId") long fromId);


    /**
     * Binance -Test new order creation and signature/recvWindow long. Creates and validates a new order but does not send it into the matching engine.
     *
     * @return
     */
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/api/v3/order/test")
    Call<String> orderTest(@Query("symbol") String symbol, @Query("side") OrderSide side, @Query("type") OrderType type,
                           @Query("timeInForce") TimeInForce timeInForce, @Query("quantity") String quantity, @Query("price") String price,
                           @Query("newClientOrderId") String newClientOrderId, @Query("stopPrice") String stopPrice,
                           @Query("icebergQty") String icebergQty, @Query("newOrderRespType") NewOrderResponseType newOrderRespType,
                           @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);


    /**
     * Binance -Get compressed, aggregate trades. Trades that fill at the time, from the same order, with the same price will have the quantity aggregated.
     * @param symbol
     * @param fromId
     * @param limit
     * @param startTime
     * @param endTime
     * @return
     */
    @GET("/api/v1/aggTrades")
    Call<List<AggTrade>> getAggTrades(@Query("symbol") String symbol, @Query("fromId") String fromId, @Query("limit") Integer limit,
                                      @Query("startTime") Long startTime, @Query("endTime") Long endTime);

    @GET("/api/v1/klines")
    Call<List<Candlestick>> getCandlestickBars(@Query("symbol") String symbol, @Query("interval") String interval, @Query("limit") Integer limit,
                                               @Query("startTime") Long startTime, @Query("endTime") Long endTime);

    /**
     * Binance -24 hour price change statistics. Careful when accessing this with no symbol.
     *
     * Weight: 1 for a single symbol; number of symbols that are TRADING / 2 when the symbol parameter is omitted
     * @param symbol
     * @return
     */
    @GET("/api/v1/ticker/24hr")
    Call<TickerStatistics> get24HrPriceStatistics(@Query("symbol") String symbol);

    @GET("/api/v1/ticker/24hr")
    Call<List<TickerStatistics>> getAll24HrPriceStatistics();

    @GET("/api/v1/ticker/allPrices")
    Call<List<TickerPrice>> getLatestPrices();

    /**
     * Latest price for a symbol or symbols.
     * @param symbol
     * @return
     */
    @GET("/api/v3/ticker/price")
    Call<TickerPrice> getLatestPrice(@Query("symbol") String symbol);

    /**
     * Best price/qty on the order book for a symbol or symbols.
     * @return
     */
    @GET("/api/v1/ticker/allBookTickers")
    Call<List<BookTicker>> getBookTickers();

    // Account endpoints
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/api/v3/order")
    Call<NewOrderResponse> newOrder(@Query("symbol") String symbol, @Query("side") OrderSide side, @Query("type") OrderType type,
                                    @Query("timeInForce") TimeInForce timeInForce, @Query("quantity") String quantity, @Query("price") String price,
                                    @Query("newClientOrderId") String newClientOrderId, @Query("stopPrice") String stopPrice,
                                    @Query("icebergQty") String icebergQty, @Query("newOrderRespType") NewOrderResponseType newOrderRespType,
                                    @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/api/v3/order/test")
    Call<Void> newOrderTest(@Query("symbol") String symbol, @Query("side") OrderSide side, @Query("type") OrderType type,
                            @Query("timeInForce") TimeInForce timeInForce, @Query("quantity") String quantity, @Query("price") String price,
                            @Query("newClientOrderId") String newClientOrderId, @Query("stopPrice") String stopPrice,
                            @Query("icebergQty") String icebergQty, @Query("newOrderRespType") NewOrderResponseType newOrderRespType,
                            @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/order")
    Call<Order> getOrderStatus(@Query("symbol") String symbol, @Query("orderId") Long orderId,
                               @Query("origClientOrderId") String origClientOrderId, @Query("recvWindow") Long recvWindow,
                               @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @DELETE("/api/v3/order")
    Call<Void> cancelOrder(@Query("symbol") String symbol, @Query("orderId") Long orderId,
                           @Query("origClientOrderId") String origClientOrderId, @Query("newClientOrderId") String newClientOrderId,
                           @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/openOrders")
    Call<List<Order>> getOpenOrders(@Query("symbol") String symbol, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/allOrders")
    Call<List<Order>> getAllOrders(@Query("symbol") String symbol, @Query("orderId") Long orderId,
                                   @Query("limit") Integer limit, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/account")
    Call<Account> getAccount(@Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/myTrades")
    Call<List<Trade>> getMyTrades(@Query("symbol") String symbol, @Query("limit") Integer limit, @Query("fromId") Long fromId,
                                  @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/wapi/v3/withdraw.html")
    Call<WithdrawResult> withdraw(@Query("asset") String asset, @Query("address") String address, @Query("amount") String amount, @Query("name") String name, @Query("addressTag") String addressTag,
                                  @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);


    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/wapi/v3/depositHistory.html")
    Call<DepositHistory> getDepositHistory(@Query("asset") String asset, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/wapi/v3/withdrawHistory.html")
    Call<WithdrawHistory> getWithdrawHistory(@Query("asset") String asset, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/wapi/v3/depositAddress.html")
    Call<DepositAddress> getDepositAddress(@Query("asset") String asset, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    // User stream endpoints

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @POST("/api/v1/userDataStream")
    Call<ListenKey> startUserDataStream();

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @PUT("/api/v1/userDataStream")
    Call<Void> keepAliveUserDataStream(@Query("listenKey") String listenKey);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @DELETE("/api/v1/userDataStream")
    Call<Void> closeAliveUserDataStream(@Query("listenKey") String listenKey);

    @GET("https://support.binance.com/hc/en-us/sections/115000106672-New-Listings?page=1")
    Call<String> supportNewList();
}
