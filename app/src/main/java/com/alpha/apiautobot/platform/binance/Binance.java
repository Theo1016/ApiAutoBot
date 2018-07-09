package com.alpha.apiautobot.platform.binance;

import android.util.Log;
import com.alpha.apiautobot.domain.request.NewOrder;
import com.alpha.apiautobot.domain.request.binance.TimeInForce;
import com.alpha.apiautobot.domain.response.binance.rest.ExchangeInfo;
import com.alpha.apiautobot.domain.response.binance.rest.SymbolInfo;
import com.alpha.apiautobot.platform.AbstractPlatform;
import com.alpha.apiautobot.platform.binance.presenter.BinanceContract;
import com.alpha.apiautobot.platform.binance.presenter.BinancePresenter;
import com.alpha.apiautobot.utils.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Theo on 2018/5/17.
 */
public class Binance extends AbstractPlatform implements BinanceContract.View {

    BinancePresenter mPingPresenter;
    volatile String[] uniqueBaseAssets;
    volatile String[] willListCoins;

    public String[] getUniqueBaseAssets() {
        return uniqueBaseAssets;
    }

    public String[] getWillListCoins() {
        return willListCoins;
    }

    public void pullExchangeInfo() {
        mPingPresenter.exchangeInfo();
    }

    public void pullNewList() {
        mPingPresenter.supportNewList();
    }

    @Override
    public void initRestful() {
        mPingPresenter = new BinancePresenter(this);
//        mPingPresenter.depth("ETHBTC",100);
//        mPingPresenter.trades("ETHBTC",100);
//        mPingPresenter.historicalTrades("ETHBTC",100,0);
//       buyCoin();
//        mPingPresenter.exchangeInfo();
    }

    @Override
    public void connection() {

    }

    @Override
    public void disConnection() {

    }

    @Override
    public void getMarketList() {
    }

    @Override
    public void getTick() {
    }

    @Override
    public void getAccountInfo() {
    }

    @Override
    public void buyCoin() {
        mPingPresenter.orderTest(NewOrder.limitBuy("BNBBTC", TimeInForce.GTC, "1000", "0.0001"));
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
    public void exchangeInfoCallback(ExchangeInfo exchangeInfo) {
        List<SymbolInfo> symbols = exchangeInfo.getSymbols();

        ArrayList<String> baseAssets = new ArrayList<String>();
        for (SymbolInfo symbol : symbols) {
            baseAssets.add(symbol.getBaseAsset());
//            Log.i("binanceSymbols",  + ":" + symbol.getQuoteAsset() + "=" + symbol.getSymbol());
        }
        String[] baseAssetsUnique = Util.arrayUnique(baseAssets.toArray(new String[0]));
//        for (String symbol : baseAssetsUnique) {
//            Log.i("binanceUniqueSymbols",  symbol);
//        }
        uniqueBaseAssets = baseAssetsUnique;
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

    @Override
    public void supportNewListCallback(String body) {
        // 解析公告列表
        ArrayList<String> arr = new ArrayList<String>();
        if (body != null) {
            String[] preStrs = body.split("<ul class=\"article-list\">");
            if (preStrs.length == 2) {
                String[] nextStrs = preStrs[1].split("<nav class=\"pagination\">");
                String str = nextStrs[0];

                Pattern pattern = Pattern.compile("class=\"article-list-link\">(.*\\((.*)\\).*)</a>");
                Matcher matcher = pattern.matcher(str);
                while (matcher.find()) {
//                    Log.i("supportNewListCallback", matcher.group(2) + "=>" + matcher.group(1));
                    String title = matcher.group(1);
                    if (title.contains("List")) {
                        String coin = matcher.group(2);
                        arr.add(coin);
                    }
                }
            }
        }
        willListCoins = arr.toArray(new String[0]);
    }
}
