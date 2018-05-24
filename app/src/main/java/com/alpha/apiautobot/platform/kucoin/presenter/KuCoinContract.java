package com.alpha.apiautobot.platform.kucoin.presenter;

import com.alpha.apiautobot.domain.request.NewOrder;

/**
 * Created by Theo on 2018/5/19.
 */
public class KuCoinContract {
    public interface Presenter{
        public void changeCurrency(String currency);

        public void getTick(String symbol);

        public void listActiveOrders(String symbol,String type);

        public void ping();

        public void serverTime();

        public void exchangeInfo();

        public void depth(String symobl, int limit);

        public void trades(String symobl, int limit);

        public void historicalTrades(String symobl, int limit, long fromId);

        public void orderTest(NewOrder order);
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
