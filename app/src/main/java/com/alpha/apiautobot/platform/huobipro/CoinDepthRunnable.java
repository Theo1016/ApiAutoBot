package com.alpha.apiautobot.platform.huobipro;

import android.database.Cursor;

import com.alpha.apiautobot.base.rest.huobipro.HuobiApiService;
import com.alpha.apiautobot.domain.response.huobipro.MarketDepth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
    final LinkedHashMap<Long, ArrayList<MarketDepth>> depthMaps = new LinkedHashMap<>();

    public CoinDepthRunnable(HuobiApiService apiService, String symbol, int step) {
        this.apiService = apiService;
        this.symbol = symbol;
        this.step = "step" + step;
    }

    public Map<Long, ArrayList<MarketDepth>> getDepthMaps() {
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

                Long ts = depth.getTick().getTs();
                if(depthMaps.isEmpty()) {
                    ArrayList<MarketDepth> depths = new ArrayList<>();
                    depths.add(depth);
                    depthMaps.put(ts, depths);
                    first = curosr = ts;
                }else {
                    if(ts - curosr > TIME_GAP) {
                        if(depthMaps.size() == MAX_TIME_STATS / TIME_GAP) {
                            //统计队列已满
                            depthMaps.remove(first);
                            //指向下一个
                            Set<Long> set = depthMaps.keySet();
                            first = set.iterator().next();
                        }
                        ArrayList<MarketDepth> depths = new ArrayList<>();
                        depths.add(depth);
                        depthMaps.put(ts, depths);
                        //指向新的统计时间点
                        curosr = ts;
                    }else {
                        depthMaps.get(curosr).add(depth);
                    }
                }
                Thread.sleep(5000);
            }catch (IOException e) {
                e.printStackTrace();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
