package com.alpha.apiautobot.bot;

import android.util.Log;
import com.alpha.apiautobot.ApiAutoBotApplication;
import com.alpha.apiautobot.base.Config;
import com.alpha.apiautobot.domain.dao.kucoin.Market;
import com.alpha.apiautobot.domain.dao.kucoin.MarketDao;
import com.alpha.apiautobot.domain.dao.kucoin.TransactionOrder;
import com.alpha.apiautobot.domain.dao.kucoin.TransactionOrderDao;
import com.alpha.apiautobot.platform.binance.Binance;
import com.alpha.apiautobot.platform.kucoin.KuCoin;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Theo on 2018/5/17.
 */
public class BotManager {

    Config config;

    KuCoin kuCoin;
    Timer timer;

    public void start() {
//       Binance binance= new Binance();
//       binance.initRestful();

        kuCoin = new KuCoin();
        kuCoin.initRestful();
        monitoringRapidRiseAndFall();
        monitoringBigCapital();
    }

    /**
     * 监控快速涨跌
     */
    public void monitoringRapidRiseAndFall() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                kuCoin.getMarketList();
            }
        }, 5 * 1000, 5 * 1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                RapidRiseAndFall();
            }
        }, 15 * 1000, 15 * 1000);
    }

    /**
     * 计算快速涨跌
     */
    private void RapidRiseAndFall() {
        try {
            MarketDao marketDao = ApiAutoBotApplication.daoSession.getMarketDao();
            List<Market> markets = marketDao.loadAll();
            if (markets != null && markets.size() >= 2) {
                for (Market market : markets) {
                    String timeStamps = market.getTimeStamps();
                    String lastDealPrices = market.getLastDealPrices();
                    String[] timeStampsArray = timeStamps.split("&&");
                    String[] lastDealPricesArray = lastDealPrices.split("&&");
                    if (timeStampsArray != null && lastDealPricesArray != null) {
                        for (int i = 0; i < lastDealPricesArray.length; i++) {
                            double absPrice = Math.abs((market.getLastDealPrice() - Double.valueOf(lastDealPricesArray[i])) / market.getLastDealPrice());
                            if (absPrice > 0.05) {//大于0.05%涨跌幅
                                Log.i("Rapid Rise or Fall", market.getCoinType() + "/" + market.getCoinTypePair() + " rate:" + (market.getLastDealPrice() - Double.valueOf(lastDealPricesArray[i])) / market.getLastDealPrice() + " time:" + (market.getDatetime() - Long.valueOf(timeStampsArray[i])));
                            }
                            if (absPrice > 0.1) {//大于1%涨跌幅
                                Log.e("Rapid Rise or Fall", market.getCoinType() + "/" + market.getCoinTypePair() + " rate:" + (market.getLastDealPrice() - Double.valueOf(lastDealPricesArray[i])) / market.getLastDealPrice() + " time:" + (market.getDatetime() - Long.valueOf(timeStampsArray[i])));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 监控大量资金入场离场
     */
    public void monitoringBigCapital() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                kuCoin.getTick();
            }
        }, 5 * 1000, 5 * 1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BigCapital();
            }
        }, 15 * 1000, 15 * 1000);
    }

    /**
     * 计算大量资金入场离场
     */
    private void BigCapital(){
        TransactionOrderDao transactionOrderDao = ApiAutoBotApplication.daoSession.getTransactionOrderDao();
        List<TransactionOrder> transactionOrders = transactionOrderDao.loadAll();
        if(transactionOrders!=null && transactionOrders.size()>0){
            for(TransactionOrder transactionOrder : transactionOrders){
                String buyVolumes= transactionOrder.getBuyVolume();
                String[] buyVolumesArray=buyVolumes.split("&&");
                String sellVolume= transactionOrder.getSellVolume();
                String[] sellVolumeArray=sellVolume.split("&&");
                if(buyVolumesArray!=null&& sellVolumeArray!=null){
                    for(int i=0;i<buyVolumesArray.length;i++){
                        if(i>=1) {
                            double bigMoney = Double.valueOf(buyVolumesArray[i]) - Double.valueOf(buyVolumesArray[i - 1]) / Double.valueOf(buyVolumesArray[i]);
                            if(bigMoney>0.01) Log.i("BigCapital BUY", "CoinType:" + transactionOrder.getCoinType()+"bigMoney:"+bigMoney*100+"%");
                        }
                    }
                    for(int i=0;i<sellVolumeArray.length;i++){
                        if(i>=1) {
                            double bigMoney = Double.valueOf(sellVolumeArray[i]) - Double.valueOf(sellVolumeArray[i - 1]) / Double.valueOf(sellVolumeArray[i]);
                            if(bigMoney>0.01) Log.i("BigCapital SELL", "CoinType:" + transactionOrder.getCoinType()+"bigMoney:"+bigMoney*100+"%");
                        }
                    }
                }
            }
        }
    }
}
