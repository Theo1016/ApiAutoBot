package com.alpha.apiautobot.platform.kucoin;

import android.text.TextUtils;
import android.util.Log;
import com.alpha.apiautobot.ApiAutoBotApplication;
import com.alpha.apiautobot.domain.dao.kucoin.Market;
import com.alpha.apiautobot.domain.dao.kucoin.MarketDao;
import com.alpha.apiautobot.domain.dao.kucoin.TransactionOrder;
import com.alpha.apiautobot.domain.dao.kucoin.TransactionOrderDao;
import com.alpha.apiautobot.domain.request.NewOrder;
import com.alpha.apiautobot.domain.request.binance.TimeInForce;
import com.alpha.apiautobot.domain.response.kucoin.MarketModel;
import com.alpha.apiautobot.domain.response.kucoin.TransactionOrderModel;
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
        mKuCoinPresenter.orderBook("KCS-BTC", "1000", "7");
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

    @Override
    public void callback(String query, TransactionOrderModel transactionOrderModel) {
        if (transactionOrderModel == null) {
            return;
        }
        //分开存
//        saveTransactionOrder(query, transactionOrderModel);
        //存总量
        TransactionOrderDao transactionOrderDao = ApiAutoBotApplication.daoSession.getTransactionOrderDao();
        List<TransactionOrder> transactionOrders = transactionOrderDao.loadAll();
        int buySize = transactionOrderModel.getData().getBUY().size();
        int sellSize = transactionOrderModel.getData().getSELL().size();
        int bigSize = buySize > sellSize ? buySize : sellSize;
        if (transactionOrders == null || transactionOrders.size() == 0) {
            TransactionOrder transactionOrder = new TransactionOrder();
            double buyVolums = 0;
            double sellVolums = 0;
            for (int i = 0; i < bigSize; i++) {
                if (buySize >= i) {
                    buyVolums += transactionOrderModel.getData().getBUY().get(i)[2];
                }
                if (sellSize >= i) {
                    sellVolums += transactionOrderModel.getData().getSELL().get(i)[2];
                }
            }
            transactionOrder.setCoinType(query);
            transactionOrder.setBuyVolume(buyVolums + "");
            transactionOrder.setSellVolume(sellVolums + "");
            transactionOrder.setTimeStamp(new Date().getTime());
            transactionOrderDao.insert(transactionOrder);
        } else {
            TransactionOrder transactionOrder = new TransactionOrder();
            transactionOrder = transactionOrderDao.queryBuilder().where(TransactionOrderDao.Properties.CoinType.eq(query)).build().unique();
            double buyVolums = 0;
            double sellVolums = 0;
            String[] buyVolumeString = transactionOrder.getBuyVolume().split("&&");
            String[] sellVolumeString = transactionOrder.getSellVolume().split("&&");
            if ((buyVolumeString == null || sellVolumeString == null || buyVolumeString.length >= 0) && (buyVolumeString.length < 120 || sellVolumeString.length < 120)) {
                for (int i = 0; i < bigSize; i++) {
                    if (buySize >= i) {
                        buyVolums += transactionOrderModel.getData().getBUY().get(i)[2];
                    }
                    if (sellSize >= i) {
                        sellVolums += transactionOrderModel.getData().getSELL().get(i)[2];
                    }
                }
            } else {
                if (buyVolumeString.length >= 120) Util.delete(0, buyVolumeString);
                if (sellVolumeString.length >= 120) Util.delete(0, sellVolumeString);
                for (int i = 0; i < buyVolumeString.length; i++) {
                    buyVolums += Double.valueOf(buyVolumeString[i]);
                }
                for (int i = 0; i < sellVolumeString.length; i++) {
                    sellVolums += Double.valueOf(sellVolumeString[i]);
                }
            }
            transactionOrder.setCoinType(query);
            transactionOrder.setBuyVolume(buyVolums + "&&" + transactionOrder.getBuyVolume());
            transactionOrder.setSellVolume(sellVolums + "&&" + transactionOrder.getSellVolume());
            transactionOrder.setTimeStamp(new Date().getTime());
            transactionOrderDao.update(transactionOrder);
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

    private void saveTransactionOrder(String query, TransactionOrderModel transactionOrderModel) {
        TransactionOrderDao transactionOrderDao = ApiAutoBotApplication.daoSession.getTransactionOrderDao();
        List<TransactionOrder> transactionOrders = transactionOrderDao.loadAll();
        int buySize = transactionOrderModel.getData().getBUY().size();
        int sellSize = transactionOrderModel.getData().getSELL().size();
        int bigSize = buySize > sellSize ? buySize : sellSize;
        if (transactionOrders == null || transactionOrders.size() == 0) {  //建表存入数据
            TransactionOrder transactionOrder = new TransactionOrder();
            for (int i = 0; i < bigSize; i++) {
                transactionOrder.setCoinType(query);
                setData(transactionOrderModel, buySize, sellSize, transactionOrder, i);
            }
            transactionOrder.setTimeStamp(new Date().getTime());
            transactionOrderDao.insert(transactionOrder);
        } else {//非空,更新数据
            TransactionOrder transactionOrder = new TransactionOrder();
            transactionOrder = transactionOrderDao.queryBuilder().where(TransactionOrderDao.Properties.CoinType.eq(query)).build().unique();
            for (int i = 0; i < bigSize; i++) {
                String[] buyPriceString = transactionOrder.getBuyPrice().split("&&");
                String[] sellPriceString = transactionOrder.getSellPrice().split("&&");
                String[] buyAmountString = transactionOrder.getBuyAmount().split("&&");
                String[] sellAmountString = transactionOrder.getSellAmount().split("&&");
                String[] buyVolumeString = transactionOrder.getBuyVolume().split("&&");
                String[] sellVolumeString = transactionOrder.getSellVolume().split("&&");
                if ((buyPriceString == null || sellPriceString == null || buyPriceString.length >= 0) && buyPriceString.length < 120 && sellPriceString.length < 120) {
                    setData(transactionOrderModel, buySize, sellSize, transactionOrder, i);
                } else if (buyPriceString.length >= 120 || sellPriceString.length >= 120) {
                    buyPriceString = buyPriceString.length >= 120 ? Util.delete(0, buyPriceString) : buyPriceString;
                    buyAmountString = buyAmountString.length >= 120 ? Util.delete(0, buyAmountString) : buyAmountString;
                    buyVolumeString = buyVolumeString.length >= 120 ? Util.delete(0, buyVolumeString) : buyVolumeString;

                    sellPriceString = sellPriceString.length >= 120 ? Util.delete(0, sellPriceString) : sellPriceString;
                    sellAmountString = sellAmountString.length >= 120 ? Util.delete(0, sellAmountString) : sellAmountString;
                    sellVolumeString = sellVolumeString.length >= 120 ? Util.delete(0, sellVolumeString) : sellVolumeString;

                    transactionOrder.setBuyPrice(Util.stringArrayConvertStirng(buyPriceString));
                    transactionOrder.setBuyAmount(Util.stringArrayConvertStirng(buyAmountString));
                    transactionOrder.setBuyVolume(Util.stringArrayConvertStirng(buyVolumeString));

                    transactionOrder.setSellPrice(Util.stringArrayConvertStirng(sellPriceString));
                    transactionOrder.setSellAmount(Util.stringArrayConvertStirng(sellAmountString));
                    transactionOrder.setSellVolume(Util.stringArrayConvertStirng(sellVolumeString));
                }
                transactionOrder.setTimeStamp(new Date().getTime());
                transactionOrderDao.update(transactionOrder);
            }
        }
    }

    private void setData(TransactionOrderModel transactionOrderModel, int buySize, int sellSize, TransactionOrder transactionOrder, int i) {
        if (buySize >= i) {
            String buyPrice = TextUtils.isEmpty(transactionOrder.getBuyPrice()) ? "" : transactionOrder.getBuyPrice() + "&&";
            String buyAmount = TextUtils.isEmpty(transactionOrder.getBuyAmount()) ? "" : transactionOrder.getBuyAmount() + "&&";
            String buyVolume = TextUtils.isEmpty(transactionOrder.getBuyVolume()) ? "" : transactionOrder.getBuyVolume() + "&&";

            transactionOrder.setBuyPrice(buyPrice + transactionOrderModel.getData().getBUY().get(i)[0] + "");
            transactionOrder.setBuyAmount(buyAmount + transactionOrderModel.getData().getBUY().get(i)[1] + "");
            transactionOrder.setBuyVolume(buyVolume + transactionOrderModel.getData().getBUY().get(i)[2] + "");
        }
        if (sellSize >= i) {
            String sellPrice = TextUtils.isEmpty(transactionOrder.getSellPrice()) ? "" : transactionOrder.getSellPrice() + "&&";
            String sellAmount = TextUtils.isEmpty(transactionOrder.getSellAmount()) ? "" : transactionOrder.getSellAmount() + "&&";
            String sellVolume = TextUtils.isEmpty(transactionOrder.getSellVolume()) ? "" : transactionOrder.getSellVolume() + "&&";

            transactionOrder.setSellPrice(sellPrice + transactionOrderModel.getData().getSELL().get(i)[0] + "");
            transactionOrder.setSellAmount(sellAmount + transactionOrderModel.getData().getSELL().get(i)[1] + "");
            transactionOrder.setSellVolume(sellVolume + transactionOrderModel.getData().getSELL().get(i)[2] + "");
        }
    }
}
