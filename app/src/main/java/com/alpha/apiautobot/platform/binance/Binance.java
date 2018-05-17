package com.alpha.apiautobot.platform.binance;

import com.alpha.apiautobot.platform.AbstractPlatform;

/**
 * Created by Theo on 2018/5/17.
 */
public class Binance extends AbstractPlatform {

    public String BASE_URL="https://api.binance.com";


    @Override
    public void initRestful() {

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
}
