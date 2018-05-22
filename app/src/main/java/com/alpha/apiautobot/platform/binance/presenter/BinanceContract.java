package com.alpha.apiautobot.platform.binance.presenter;

import retrofit2.http.Query;

/**
 * Created by Theo on 2018/5/19.
 */
public class BinanceContract {
    public interface Presenter{
        public void ping();

        public void serverTime();

        public void exchangeInfo();

        public void depth(String symobl,int limit);

        public void trades(String symobl,int limit);

        public void historicalTrades(String symobl,int limit,long fromId);

        public void orderTest(String symobl,Enum side,Enum type,Enum timeInForce,
                              double quantity,double price,String newClientOrderId,
                              double stopPrice,double icebergQty,Enum newOrderRespType,
                              long recvWindow,@Query("timestamp")long timestamp);
    }
    public interface View{
        public void pingCallback();

        public void serverTimeCallback();

        public void exchangeInfoCallback();

        public void depthCallback();

        public void tradesCallback();

        public void historicalTradesCallback();

        public void orderTestCallback();
    }
}
