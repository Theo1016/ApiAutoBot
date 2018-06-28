package com.alpha.apiautobot.platform.binance.presenter;

import com.alpha.apiautobot.domain.request.NewOrder;
import com.alpha.apiautobot.domain.response.binance.rest.ExchangeInfo;

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

        public void orderTest(NewOrder order);

        public void supportNewList();
    }
    public interface View{
        public void pingCallback();

        public void serverTimeCallback();

        public void exchangeInfoCallback(ExchangeInfo exchangeInfo);

        public void depthCallback();

        public void tradesCallback();

        public void historicalTradesCallback();

        public void orderTestCallback();

        public void supportNewListCallback(String body);
    }
}
