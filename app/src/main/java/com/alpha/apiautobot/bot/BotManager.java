package com.alpha.apiautobot.bot;

import android.util.Log;
import com.alpha.apiautobot.ApiAutoBotApplication;
import com.alpha.apiautobot.base.Config;
import com.alpha.apiautobot.domain.dao.kucoin.Market;
import com.alpha.apiautobot.domain.dao.kucoin.MarketDao;
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
        }, 5 * 1000,5*1000);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                monitoring();
            }
        }, 15 * 1000,15*1000);

    }

    private void monitoring() {
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
                                Log.i("Rapid Rise or Fall", market.getCoinType() + "/" + market.getCoinTypePair() + "rate:" + absPrice);
                            }
                            if (absPrice > 0.1) {//大于1%涨跌幅
                                Log.e("Rapid Rise or Fall", market.getCoinType() + "/" + market.getCoinTypePair() + "rate:" + absPrice);
                            }

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
