package com.alpha.apiautobot.platform.kucoin;

import com.alpha.apiautobot.ApiAutoBotApplication;
import com.alpha.apiautobot.domain.dao.kucoin.Market;
import com.alpha.apiautobot.domain.dao.kucoin.MarketDao;
import com.alpha.apiautobot.domain.request.NewOrder;
import com.alpha.apiautobot.domain.request.binance.TimeInForce;
import com.alpha.apiautobot.domain.response.kucoin.MarketModel;
import com.alpha.apiautobot.platform.AbstractPlatform;
import com.alpha.apiautobot.platform.binance.presenter.BinanceContract;
import com.alpha.apiautobot.platform.binance.presenter.BinancePresenter;
import com.alpha.apiautobot.platform.kucoin.presenter.KuCoinContract;
import com.alpha.apiautobot.platform.kucoin.presenter.KuCoinPresenter;
import com.alpha.apiautobot.utils.PreferenceUtil;
import com.alpha.apiautobot.utils.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
//        mKuCoinPresenter.listActiveOrders("KCS-BTC","BUY");
//        mKuCoinPresenter.listTradingMarkets();
//        getMarketList();
    }

    @Override
    public void connection() {

    }

    @Override
    public void disConnection() {

    }

    @Override
    public void getMarketList() {
        mKuCoinPresenter.listTradingSymbolsMarkets("BTC");
    }

    @Override
    public void getTick() {
    }

    @Override
    public void getAccountInfo() {
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
    public void callback() {

    }

    @Override
    public void callback(MarketModel marketModel) {
        if (marketModel == null) {
            return;
        }
        MarketDao marketDao = ApiAutoBotApplication.daoSession.getMarketDao();
        List<Market> markets = marketDao.loadAll();
        if (markets == null || markets.size() == 0) {//如果为空 写入数据
            saveData(marketModel, marketDao);
        } else {//非空,更新数据
            Market market = new Market();
            for (MarketModel.data data : marketModel.data) {
                market = marketDao.queryBuilder().where(MarketDao.Properties.CoinType.eq(data.getCoinType())).build().unique();
                String timeStamps = market.getTimeStamps();
                String lastDealPrices = market.getLastDealPrices();
                try {
                    String[] timeStampsArray = timeStamps.split("&&");
                    String[] lastDealPricesArray = lastDealPrices.split("&&");
                    if ((timeStampsArray == null || timeStampsArray.length >= 0) && timeStampsArray.length < 120) {
                        market.setTimeStamps(timeStamps + "&&" + new Date().getTime());
                        market.setLastDealPrices(lastDealPrices + "&&" + data.getLastDealPrice());
                    } else if (timeStampsArray.length >= 120) {
                        timeStampsArray = Util.delete(0, timeStampsArray);
                        timeStampsArray[timeStampsArray.length - 1] = new Date().getTime() + "";
                        lastDealPricesArray = Util.delete(0, lastDealPricesArray);
                        lastDealPricesArray[lastDealPricesArray.length - 1] = data.getLastDealPrice() + "";
                        market.setTimeStamps(Util.stringArrayConvertStirng(timeStampsArray));
                        market.setLastDealPrices(Util.stringArrayConvertStirng(lastDealPricesArray));
                    }
                    initMarket(market, data);
                    marketDao.update(market);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void initMarket(Market market, MarketModel.data data) {
        market.setBuy(data.getBuy());
        market.setChange(data.getChange());
        market.setChangeRate(data.getChangeRate());
        market.setCoinType(data.getCoinType());
        market.setCoinTypePair(data.getCoinTypePair());
        market.setDatetime(data.getDatetime());
        market.setFeeRate(data.getFeeRate());
        market.setHigh(data.getHigh());
        market.setLastDealPrice(data.getLastDealPrice());
        market.setLow(data.getLow());
        market.setSell(data.getSell());
        market.setSort(data.getSort());
        market.setTrading(data.isTrading());
        market.setVol(data.getVol());
        market.setVolValue(data.getVolValue());
    }

    private void saveData(MarketModel marketModel, MarketDao marketDao) {
        for (MarketModel.data data : marketModel.data) {
            Market market = new Market();
            initMarket(market, data);
            market.setTimeStamps(new Date().getTime() + "");
            market.setLastDealPrices(data.getLastDealPrice() + "");
            marketDao.insert(market);
        }
    }
}
