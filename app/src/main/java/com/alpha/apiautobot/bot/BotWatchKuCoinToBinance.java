package com.alpha.apiautobot.bot;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import com.alpha.apiautobot.ApiAutoBotApplication;
import com.alpha.apiautobot.domain.dao.kucoin.Market;
import com.alpha.apiautobot.domain.dao.kucoin.MarketDao;
import com.alpha.apiautobot.platform.binance.Binance;
import com.alpha.apiautobot.platform.kucoin.KuCoin;
import com.alpha.apiautobot.utils.NotificationUtil;
import com.alpha.apiautobot.utils.Util;

import java.util.*;

/**
 * 监控KuCoin上了的币，在币安的上币公告
 */
public class BotWatchKuCoinToBinance {

    private Binance binance;
    private KuCoin kuCoin;
    private Timer timer;

    public static String logTag = "WatchKuCoinToBinance";

    public BotWatchKuCoinToBinance() {
        kuCoin = new KuCoin();
        kuCoin.initRestful();

        binance = new Binance();
        binance.initRestful();
    }

    public void start() {
        monitoringCheckProcess();
    }

    private void monitoringCheckProcess() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                kuCoin.getMarketList();
            }
        }, 0 * 1000, 1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                checkProcess();
            }
        }, 15 * 1000, 15 * 1000);
    }

    private void checkProcess() {
        binance.pullExchangeInfo();
        binance.pullNewList();

        // KuCoin markets
        MarketDao marketDao = ApiAutoBotApplication.daoSession.getMarketDao();
        List<Market> markets = marketDao.loadAll();
        ArrayList<String> kucoinCoins = new ArrayList<String>();
        if (!(markets == null || markets.size() == 0)) {
            for (Market market : markets) {
                kucoinCoins.add(market.getCoinType());
            }
        }
        String[] kucoinCoinsUnique = Util.arrayUnique(kucoinCoins.toArray(new String[0]));

        // Binance coins
        String[] binanceCoinsUnique = binance.getUniqueBaseAssets();
        // Binance will list
        String[] binanceWillListCoins = binance.getWillListCoins();

        if (binanceCoinsUnique != null&& binanceWillListCoins != null) {
            List<String> binanceList = Arrays.asList(binanceCoinsUnique);
            List<String> willListList = Arrays.asList(binanceWillListCoins);

            if (kucoinCoinsUnique.length > 0 && binanceCoinsUnique.length > 0) {
                ArrayList<String> willList = new ArrayList<>();

                ArrayList<String> restCoins = new ArrayList<String>();
                for (String coin : kucoinCoinsUnique) {
                    if (!binanceList.contains(coin)) {
                        restCoins.add(coin);
                    }
                }

                for (String coin : restCoins) {
                    if (willListList.contains(coin)) {
                        willList.add(coin);
                    }
                }

                if (willList.size() > 0) {
                    for (String coin : willList) {
                        Log.i(logTag, "Binance will list 【"+coin+"】that has listed on KuCoin");
                        NotificationUtil.notification("上币公告", "Binance平台将上新币:" + coin);
                    }
                } else {
                    Log.i(logTag, "Binance will list empty");
                }
            } else {
                Log.i(logTag, "Get Unique Coins List Empty. kucoin:" + kucoinCoinsUnique.length +"/binance:" + binanceCoinsUnique.length);
            }
        } else {
            Log.i(logTag, "Binance coin uniqueList or willList empty");
        }
    }

}
