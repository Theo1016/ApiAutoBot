package com.alpha.apiautobot.platform.binance;

import com.alpha.apiautobot.domain.request.NewOrder;
import com.alpha.apiautobot.domain.request.binance.TimeInForce;
import com.alpha.apiautobot.platform.AbstractPlatform;
import com.alpha.apiautobot.platform.binance.presenter.BinanceContract;
import com.alpha.apiautobot.platform.binance.presenter.BinancePresenter;


/**
 * Created by Theo on 2018/5/17.
 */
public class Binance extends AbstractPlatform implements BinanceContract.View {

    BinancePresenter mPingPresenter;

    @Override
    public void initRestful() {
        mPingPresenter = new BinancePresenter(this);
//        mPingPresenter.depth("ETHBTC",100);
//        mPingPresenter.trades("ETHBTC",100);
//        mPingPresenter.historicalTrades("ETHBTC",100,0);
        mPingPresenter.orderTest(NewOrder.limitBuy("BNBBTC", TimeInForce.GTC, "1000", "0.0001"));
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
