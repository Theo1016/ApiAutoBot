package com.alpha.apiautobot.platform.huobipro;

import android.database.Cursor;
import android.util.Log;

import com.alpha.apiautobot.base.rest.huobipro.HuobiApiService;
import com.alpha.apiautobot.domain.response.huobipro.MarketDepth;
import com.alpha.apiautobot.utils.NotificationUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * <pre>
 *     author : hedong
 *     e-mail : 739814501@qq.com
 *     time   : 2018/05/31
 *     desc   : 币种交易深度统计
 *     version: 1.0
 * </pre>
 */
public class CoinDepthRunnable implements Runnable {
    static final long TIME_GAP = 5 * 60;        //统计间隔时间
    static final long MAX_TIME_STATS = 60 * 60; //最大统计时间

    final String symbol;
    final String step;
    final HuobiApiService apiService;
    final Map<Long, CopyOnWriteArrayList<MarketDepth>> depthMaps = Collections.synchronizedMap(new LinkedHashMap<Long, CopyOnWriteArrayList<MarketDepth>>());

    public CoinDepthRunnable(HuobiApiService apiService, String symbol, int step) {
        this.apiService = apiService;
        this.symbol = symbol;
        this.step = "step" + step;
    }

    public Map<Long, CopyOnWriteArrayList<MarketDepth>> getDepthMaps() {
        return this.depthMaps;
    }

    @Override
    public void run() {
        Long first = null;      //指向第一个时间点
        Long curosr = null;     //指向统计间隔时间点
        while (true) {
            try {
                Response<MarketDepth> response = apiService.getMarketDepth(symbol, step).execute();
                if (!response.isSuccessful()) {
                    continue;
                }

                MarketDepth depth = response.body();
                if(!depth.getStatus().equals("ok")) {
                    return;
                }

                Long ts = depth.getTs()/*depth.getTick().getTs()*/;
                if(depthMaps.isEmpty()) {
                    depth.symbol = symbol;
                    CopyOnWriteArrayList<MarketDepth> depths = new CopyOnWriteArrayList<>();
                    depths.add(depth);
                    depthMaps.put(ts, depths);
                    first = curosr = ts;
                }else {
                    if((ts - curosr)/ 1000 > TIME_GAP) {
                        if(depthMaps.size() == MAX_TIME_STATS / TIME_GAP) {
                            //统计队列已满
                            depthMaps.remove(first);
                            //指向下一个
                            Set<Long> set = depthMaps.keySet();
                            first = set.iterator().next();
                        }
                        CopyOnWriteArrayList<MarketDepth> depths = new CopyOnWriteArrayList<>();
                        depths.add(depth);
                        depthMaps.put(ts, depths);
                        //指向新的统计时间点
                        curosr = ts;
                    }else {
                        depthMaps.get(curosr).add(depth);
                    }
                }
                //计算涨幅
                caculateDepth(depthMaps);
                Thread.sleep(5000);

            }catch (IOException e) {
//                e.printStackTrace();
                Log.w("Depth", "request " + symbol + " depth network error!");
            }catch (InterruptedException e) {
                e.printStackTrace();
                Log.w("Depth", "request " + symbol + " depth sleep error!");
            }
        }
    }

    private void caculateDepth(Map<Long, CopyOnWriteArrayList<MarketDepth>> map) {
        Iterator<Long> it = map.keySet().iterator();
        if(!it.hasNext()) {
            return;
        }
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        format.setMaximumFractionDigits(4);
        Long first = it.next();
        if(map.get(first).get(0) == null) {
            //没有深度列表
            return;
        }
        String symbol = map.get(first).get(0).symbol;
        //近60min
        List<Long> ts = new ArrayList<>();
        ts.add(first);
        //取出统计的时间节点
        while (it.hasNext()) {
            Long tmp = it.next();
            ts.add(tmp);
        }
        int i = ts.size() - 1;
        int j = 0;      //统计区间起始位置

        if (ts.size() == 2) {
            //近5min
            j = 1;
        } else if (ts.size() >= 3 && ts.size() < 6) {
            //统计时间有3个点表示可以统计到至少近15min数据
            j = ts.size() - 3;
        } else if (ts.size() >= 6 && ts.size() < 12) {
            //统计至少30min钟数据
            j = ts.size() - 6;
        } else {
            //统计1h以上数据
            j = ts.size() - 12;
        }

        double totalBuyVolume = 0, totalSellVolume = 0;
        //最新成交价
        double buyVolume = 0, sellVolume = 0;
        //取最后一个统计时间点的值
        List<MarketDepth> depths = map.get(ts.get(ts.size() - 1));
        //取最后一个统计的深度盘
        MarketDepth depth = depths.get(depths.size() - 1);
        List<List<BigDecimal>> bids = depth.getTick().getBids();  //买盘
        buyVolume = caculateVolume(bids);
        List<List<BigDecimal>> asks = depth.getTick().getAsks();  //卖盘
        sellVolume = caculateVolume(asks);

        int k = 1;
        //计算涨幅
        for (; i >= 0 && i >= j; i--) {
            List<MarketDepth> depths1 = map.get(ts.get(i));
            //取出第一个深度盘数据
            MarketDepth depth1 = depths1.get(0);
            List<List<BigDecimal>> bids1 = depth1.getTick().getBids();  //买盘
            totalBuyVolume = caculateVolume(bids1);
            List<List<BigDecimal>> asks1 = depth1.getTick().getAsks();  //卖盘
            totalSellVolume = caculateVolume(asks1);
            //计算涨幅
            double buyPercent = (totalBuyVolume == 0 ? 0 : Math.abs(buyVolume - totalBuyVolume) / totalBuyVolume) * 100;
            double sellPercent = (totalSellVolume == 0 ? 0 : Math.abs(sellVolume - totalSellVolume) / totalSellVolume) * 100;
            String buyP = (buyVolume >= totalBuyVolume ? "+" : "-") + format.format(buyPercent) + "%";
            String sellP = (sellVolume >= totalSellVolume ? "+" : "-") + format.format(sellPercent) + "%";
            String msg = symbol + " " + (k * 5) + "min钟内深度，买盘涨幅:" + buyP + ", 卖盘涨幅:" + sellP;
            Log.e("Depth", msg);
            if(buyPercent > 0.02 || sellPercent > 0.02) {
                //买盘大于2%
                NotificationUtil.notification("火币买卖盘监控", msg);
            }
            totalBuyVolume = 0;
            totalSellVolume = 0;
            k++;
        }
    }

    private double caculateVolume(List<List<BigDecimal>> depths) {
        double totalVolume = 0;
        for (List<BigDecimal> decimals : depths) {
            //价格*数量
            totalVolume += decimals.get(1).multiply(decimals.get(0)).doubleValue();
        }
        return totalVolume;
    }

}
