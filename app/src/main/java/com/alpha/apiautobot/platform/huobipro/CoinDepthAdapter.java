package com.alpha.apiautobot.platform.huobipro;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alpha.apiautobot.R;
import com.alpha.apiautobot.domain.dao.kucoin.Market;
import com.alpha.apiautobot.domain.response.huobipro.MarketDepth;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoinDepthAdapter extends RecyclerView.Adapter<CoinDepthAdapter.ViewHolder> {
    private List<Map<Long, CopyOnWriteArrayList<MarketDepth>>> mDatas;

    public CoinDepthAdapter(List<Map<Long, CopyOnWriteArrayList<MarketDepth>>> datas) {
        this.mDatas = datas;
    }

    public void updateData(List<Map<Long, CopyOnWriteArrayList<MarketDepth>>> datas) {
        this.mDatas = datas;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coin_depth_recyclerview_item, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map<Long, CopyOnWriteArrayList<MarketDepth>> map = mDatas.get(position);
        Iterator<Long> it = map.keySet().iterator();
        if(!it.hasNext()) {
            return;
        }
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(8);
        Long first = it.next();
        if(map.get(first).get(0) == null) {
            return;
        }
        holder.tv_symbol.setText(map.get(first).get(0).symbol);
        if(it.hasNext()) {
            //近60min
            List<Long> ts = new ArrayList<>();
            ts.add(first);
            while (it.hasNext()) {
                Long tmp = it.next();
                ts.add(tmp);
            }
            int i=ts.size()-1;
            int j = 0;

            if(ts.size() == 2) {
                //近5min
                j = 1;
            }else if(ts.size() >= 3) {
                //统计时间有3个点表示可以统计到至少近15min数据
                j = ts.size() - 3;
           }
           int k = 1;
            double totalBuyVolume = 0, totalSellVolume = 0;
            //最新成交价
            double buyVolume = 0, sellVolume = 0;
            //取最后一个统计时间点的值
            List<MarketDepth> depths = map.get(ts.get(ts.size()-1));
            //取最后一个统计的深度盘
            MarketDepth depth = depths.get(depths.size()-1);
            List<List<BigDecimal>> bids = depth.getTick().getBids();  //买盘
            for (List<BigDecimal> decimals : bids) {
                //价格*数量
                buyVolume += decimals.get(1).multiply(decimals.get(0)).doubleValue();
            }
            List<List<BigDecimal>> asks = depth.getTick().getAsks();  //卖盘
            for (List<BigDecimal> decimals : asks) {
                sellVolume += decimals.get(0).multiply(decimals.get(1)).doubleValue();
            }

            holder.tv_bids_5min.setText(format.format(buyVolume));
            holder.tv_asks_5min.setText(format.format(sellVolume));

            //计算涨幅
            for (;i>=0 && i>= j;i--) {
                List<MarketDepth> depths1 = map.get(ts.get(i));
                //取出第一个深度盘数据
                MarketDepth depth1 = depths1.get(0);
                List<List<BigDecimal>> bids1 = depth1.getTick().getBids();  //买盘
                for (List<BigDecimal> decimals : bids1) {
                    //价格*数量
                    totalBuyVolume += decimals.get(1).multiply(decimals.get(0)).doubleValue();
                }
                List<List<BigDecimal>> asks1 = depth1.getTick().getAsks();  //卖盘
                for (List<BigDecimal> decimals : asks1) {
                    totalSellVolume += decimals.get(0).multiply(decimals.get(1)).doubleValue();
                }
                //计算涨幅

                double percent1 = Math.abs(buyVolume - totalBuyVolume) / totalBuyVolume;
                double percent2 = Math.abs(sellVolume - totalSellVolume) / totalSellVolume;
                if(k == 3) {
                    //近15min涨幅
                    holder.tv_bids_15min.setText((buyVolume > totalBuyVolume ? "+" : "-") + format.format(percent1 * 100) + "%");
                    holder.tv_asks_15min.setText((sellVolume > totalSellVolume ? "+" : "-") + format.format(percent2 * 100) + "%");
                }else if(k == 6) {
                    //近30min涨幅
                    holder.tv_bids_30min.setText((buyVolume > totalBuyVolume ? "+" : "-") + format.format(percent1 * 100) + "%");
                    holder.tv_asks_30min.setText((sellVolume > totalSellVolume ? "+" : "-") + format.format(percent2 * 100) + "%");
                }else if(k == 12) {
                    //近60min涨幅
                    holder.tv_bids_60min.setText((buyVolume > totalBuyVolume ? "+" : "-") + format.format(percent1 * 100) + "%");
                    holder.tv_asks_60min.setText((sellVolume > totalSellVolume ? "+" : "-") + format.format(percent2 * 100) + "%");
                }
                String buyP = (buyVolume > totalBuyVolume ? "+" : "-") + format.format(percent1 * 100) + "%";
                String sellP = (sellVolume > totalSellVolume ? "+" : "-") + format.format(percent2 * 100) + "%";
                Log.e("Depth", (k * 5) + "min钟内深度，买盘涨幅:" + buyP + ", 卖盘涨幅:" + sellP);
                totalBuyVolume = 0;
                totalSellVolume = 0;
                k++;
            }

        }else {
            //最新成交价
            double buyVolume = 0, sellVolume = 0;
            List<MarketDepth> depths = map.get(first);
            //取最后一个值
            MarketDepth depth = depths.get(depths.size()-1);
            List<List<BigDecimal>> bids = depth.getTick().getBids();  //买盘
            for (List<BigDecimal> decimals : bids) {
                //价格*数量
                buyVolume += decimals.get(1).multiply(decimals.get(0)).doubleValue();
            }
            List<List<BigDecimal>> asks = depth.getTick().getAsks();  //卖盘
            for (List<BigDecimal> decimals : asks) {
                sellVolume += decimals.get(0).multiply(decimals.get(1)).doubleValue();
            }

            //取第一个值
            double totalBuVolume = 0;
            double totalSellVolume = 0;
            MarketDepth depth1 = depths.get(0);
            List<List<BigDecimal>> bids1 = depth1.getTick().getBids();  //买盘
            for (List<BigDecimal> decimals : bids1) {
                //价格*数量
                totalBuVolume += decimals.get(1).multiply(decimals.get(0)).doubleValue();
            }
            List<List<BigDecimal>> asks1 = depth1.getTick().getAsks();  //卖盘
            for (List<BigDecimal> decimals : asks1) {
                totalSellVolume += decimals.get(0).multiply(decimals.get(1)).doubleValue();
            }

            holder.tv_bids_5min.setText(format.format(buyVolume));
            holder.tv_asks_5min.setText(format.format(sellVolume));

            double percent1 = Math.abs(buyVolume - totalBuVolume) / totalBuVolume;
            double percent2 = Math.abs(sellVolume - totalSellVolume) / totalSellVolume;

            String buyP = (buyVolume > totalBuVolume ? "+" : "-") + format.format(percent1 * 100) + "%";
            String sellP = (sellVolume > totalSellVolume ? "+" : "-") + format.format(percent2 * 100) + "%";
            Log.e("Depth", (1 * 5) + "min钟内深度，买盘涨幅:" + buyP + ", 卖盘涨幅:" + sellP);

        }

    }

    @Override
    public int getItemCount() {
        return this.mDatas == null ? 0 : this.mDatas.size();
    }

    public final class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_bids_5min)
        TextView tv_bids_5min;
        @BindView(R.id.tv_bids_15min)
        TextView tv_bids_15min;
        @BindView(R.id.tv_bids_30min)
        TextView tv_bids_30min;
        @BindView(R.id.tv_bids_60min)
        TextView tv_bids_60min;

        @BindView(R.id.tv_asks_5min)
        TextView tv_asks_5min;
        @BindView(R.id.tv_asks_15min)
        TextView tv_asks_15min;
        @BindView(R.id.tv_asks_30min)
        TextView tv_asks_30min;
        @BindView(R.id.tv_asks_60min)
        TextView tv_asks_60min;

        @BindView(R.id.tv_symbol)
        TextView tv_symbol;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
