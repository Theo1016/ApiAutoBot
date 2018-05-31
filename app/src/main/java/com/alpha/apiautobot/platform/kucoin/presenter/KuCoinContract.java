package com.alpha.apiautobot.platform.kucoin.presenter;


import com.alpha.apiautobot.domain.response.kucoin.MarketModel;
import com.alpha.apiautobot.domain.response.kucoin.TransactionOrderModel;

/**
 * Created by Theo on 2018/5/19.
 */
public class KuCoinContract {
    public interface Presenter {
        public void changeCurrency(String currency);

        public void getTick(String symbol);

        public void listActiveOrders(String symbol, String type);

        public void listTradingMarkets();

        public void listTradingSymbolsMarkets(String market);

        public void orderBook(String symbol,String group,String limit);

    }

    public interface View {
        public void callback();

        public void callback(MarketModel marketModel);

        public void callback(String query,TransactionOrderModel transactionOrderModel);
    }
}
