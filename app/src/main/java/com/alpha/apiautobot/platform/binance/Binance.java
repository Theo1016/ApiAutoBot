package com.alpha.apiautobot.platform.binance;

import com.alpha.apiautobot.base.rest.side;
import com.alpha.apiautobot.base.rest.timeInForce;
import com.alpha.apiautobot.base.rest.type;
import com.alpha.apiautobot.platform.AbstractPlatform;
import com.alpha.apiautobot.platform.binance.presenter.BinanceContract;
import com.alpha.apiautobot.platform.binance.presenter.BinancePresenter;

import java.util.Date;


/**
 * Created by Theo on 2018/5/17.
 */
public class Binance extends AbstractPlatform implements BinanceContract.View {

    public String BASE_URL="https://api.binance.com";

    BinancePresenter mPingPresenter;

    @Override
    public void initRestful() {
        mPingPresenter = new BinancePresenter(BASE_URL,this);
//        mPingPresenter.depth("ETHBTC",100);
//        mPingPresenter.trades("ETHBTC",100);
//        mPingPresenter.historicalTrades("ETHBTC",100,0);
        mPingPresenter.orderTest("ETHBTC", side.BUY, type.LIMIT, timeInForce.GTC,1,
                0.01,"28",0.02,1,null,5000,new Date().getTime());
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
