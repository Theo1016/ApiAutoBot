package com.alpha.apiautobot.platform.kucoin;

import com.alpha.apiautobot.domain.request.NewOrder;
import com.alpha.apiautobot.domain.request.binance.TimeInForce;
import com.alpha.apiautobot.platform.AbstractPlatform;
import com.alpha.apiautobot.platform.binance.presenter.BinanceContract;
import com.alpha.apiautobot.platform.binance.presenter.BinancePresenter;
import com.alpha.apiautobot.platform.kucoin.presenter.KuCoinContract;
import com.alpha.apiautobot.platform.kucoin.presenter.KuCoinPresenter;


/**
 * Created by Theo on 2018/5/17.
 */
public class KuCoin extends AbstractPlatform implements KuCoinContract.View {

    KuCoinPresenter mKuCoinPresenter;

    @Override
    public void initRestful() {
        mKuCoinPresenter = new KuCoinPresenter(this);
//        mPingPresenter.depth("ETHBTC",100);
//        mPingPresenter.trades("ETHBTC",100);
//        mPingPresenter.historicalTrades("ETHBTC",100,0);
//       buyCoin();
//       mKuCoinPresenter.changeCurrency("USD");
//        mKuCoinPresenter.getTick("KCS-BTC");
        mKuCoinPresenter.listActiveOrders("KCS-BTC","BUY");
    }

    @Override
    public void connection() {

    }

    @Override
    public void disConnection() {

    }

    @Override
    public Object getMarketList() {
        return null;
    }

    @Override
    public Object getTick() {
        return null;
    }

    @Override
    public Object getAccountInfo() {
        return null;
    }

    @Override
    public void buyCoin() {
        mKuCoinPresenter.orderTest(NewOrder.limitBuy("BNBBTC", TimeInForce.GTC, "1000", "0.0001"));
    }

    @Override
    public void sellCoin() {

    }

    @Override
    public void deposite() {

    }

    @Override
    public void withdrawal() {

    }

    @Override
    public void pingCallback() {

    }

    @Override
    public void serverTimeCallback() {

    }

    @Override
    public void exchangeInfoCallback() {

    }

    @Override
    public void depthCallback() {

    }

    @Override
    public void tradesCallback() {

    }

    @Override
    public void historicalTradesCallback() {

    }

    @Override
    public void orderTestCallback() {

    }
}
