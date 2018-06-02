package com.alpha.apiautobot.platform.huobipro;
import android.util.Log;

import com.alpha.apiautobot.base.rest.huobipro.HuobiApiService;
import com.alpha.apiautobot.domain.response.huobipro.MarketDetail;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CoinIncreaseRunnable implements Runnable {
    static final long TIME_GAP = 5 * 60;    //单位s
    static final long MAX_STATIS = 60 * 60;
    static final boolean DEBUG = true;

    final List<MarketDetail> marketDetailList;
    final String symbol;
    final HuobiApiService apiService;
    final String requestSymbol;

    public CoinIncreaseRunnable(String symbol, HuobiApiService service, List<MarketDetail> details) {
        this.marketDetailList = details;
        this.symbol = symbol;
        this.apiService = service;
        String[] symbols = symbol.split("/");
        requestSymbol = symbols[0] + symbols[1];
    }

    @Override
    public void run() {
        while (true) {
            Call<MarketDetail> call = apiService.getMarketDetail(requestSymbol);
            try {
                Response<MarketDetail> response = call.execute();
                if(!response.isSuccessful()) {
                    continue;
                }

                MarketDetail marketDetail = response.body();
                //记录交易对
                marketDetail.symbol = symbol;
                double lastDealPrice = marketDetail.tick.close;
                long timestamp = marketDetail.ts;
                if (marketDetailList.isEmpty() || marketDetailList.size() == 1) {
                    //当列表为空时，添加第一个时间间隔，相当于开盘价
                    //当列表只有一个统计节点时，直接添加第二个统计节点,此后数据从第二个节点开始更新
                    marketDetail.price = lastDealPrice;
                    marketDetail.timeStamp = timestamp;
                    marketDetailList.add(marketDetail);
                } else {
                    int pos = marketDetailList.size() - 1;
                    //当前时间与最近时间比较
                    long lastTime = marketDetailList.get(pos).ts;
                    //当前最新价时间与上次价格时间相差
                    int second = (int) ((timestamp - lastTime) / 1000);
                    if (second > TIME_GAP) {
                        //到达统计时间间隔，新增统计点
                        if (marketDetailList.size() == MAX_STATIS / TIME_GAP) {
                            //统计点已满，删除第一个统计值
                            marketDetailList.remove(0);
                        }
                        marketDetail.price = lastDealPrice;
                        marketDetail.timeStamp = timestamp;
                        marketDetailList.add(marketDetail);
                    } else {
                        //时间间隔内，更新时间戳，价格
                        marketDetailList.get(pos).timeStamp = timestamp;
                        marketDetailList.get(pos).price = lastDealPrice;
                    }
                }

                //这里可以将数据存储至数据库
                if (DEBUG) {
                    Log.e("CoinIncreaseRunnable", symbol + "已统计" + marketDetailList.size());
                }

                //间隔5s请求
                Thread.sleep(5000);

            } catch (IOException e) {
                e.printStackTrace();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
