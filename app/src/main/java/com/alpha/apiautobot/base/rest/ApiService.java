package com.alpha.apiautobot.base.rest;

import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by Theo
 */
public interface ApiService {

    /**
     * Binance -Ping
     * @return
     */
    @GET("/api/v1/ping")
    Call<String> ping();


    /**
     * Binance -serverTime binance
     * @return
     */
    @GET("/api/v1/time")
    Call<String> serverTime();

    /**
     * Binance -Current exchange trading rules and symbol information
     * @return
     */
    @GET("/api/v1/exchangeInfo")
    Call<String> exchangeInfo();

    /**
     * Binance -Order book
     * @return
     */
    @GET("/api/v1/depth")
    Call<String> depth(@Query("symbol") String symobl, @Query("limit") int limit);

    /**
     * Binance -Get recent trades (up to last 500).
     * @return
     */
    @GET("/api/v1/trades")
    Call<String> trades(@Query("symbol") String symobl, @Query("limit") int limit);

    /**
     * Binance -Get older trades.
     * @return
     */
    @GET("/api/v1/historicalTrades")
    Call<String> historicalTrades(@Query("symbol") String symobl, @Query("limit") int limit,@Query("fromId")long fromId);


    /**
     * Binance -Test new order creation and signature/recvWindow long. Creates and validates a new order but does not send it into the matching engine.
     * @return
     */
    @POST("/api/v3/order/test")
    Call<String> orderTest(@Query("symbol") String symobl, @Query("side") Enum side,@Query("type")Enum type,@Query("timeInForce")Enum timeInForce,
                           @Query("quantity")double quantity,@Query("price")double price,@Query("newClientOrderId")String newClientOrderId,
                           @Query("stopPrice")double stopPrice,@Query("icebergQty")double icebergQty,@Query("newOrderRespType")Enum newOrderRespType,
                           @Query("recvWindow")long recvWindow,@Query("timestamp")long timestamp,@Query("signature")String signature);
}
