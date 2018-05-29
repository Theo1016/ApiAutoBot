package com.alpha.apiautobot.platform.huobipro;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alpha.apiautobot.R;
import com.alpha.apiautobot.domain.response.huobipro.MarketDetail;

import java.text.NumberFormat;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoinIncreaseAdapter extends RecyclerView.Adapter<CoinIncreaseAdapter.ViewHolder> {
    private List<List<MarketDetail>> mDatas;

    public CoinIncreaseAdapter(List<List<MarketDetail>> details) {
        this.mDatas = details;
    }

    public void update(List<List<MarketDetail>> details) {
        this.mDatas = details;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_increase_recyclerview_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List<MarketDetail> marketDetails = mDatas.get(position);
        if(marketDetails.size() != 0) {
            String symbol = marketDetails.get(0).symbol;
            holder.tv_coin.setText(symbol);
            int last = marketDetails.size() - 1;
            Double price1 = marketDetails.get(last).price;
            Double price2 = null;

            NumberFormat format = NumberFormat.getInstance();
            format.setMaximumFractionDigits(2);

            if(marketDetails.size() >= 1) {
                //5min
                if(marketDetails.size() == 1) {
                    price2 = marketDetails.get(0).tick.close;
                }else {
                    price2 = marketDetails.get(last - 1).price;
                }
                double increase = caculateIncrease(price1, price2);
                if(price1 > price2) {
                    holder.tv_5min.setText("+" + format.format(increase) + "%");
                }else if(price1 < price2){
                    holder.tv_5min.setText("-" + format.format(increase) + "%");
                }else {
                    holder.tv_5min.setText(format.format(increase) + "%");
                }
                if(increase > 2) {
                    if(price1 > price2) {
                        holder.tv_5min.setTextColor(Color.parseColor("#01aa78"));
                    }else {
                        holder.tv_5min.setTextColor(Color.parseColor("#e53600"));
                    }
                }else {
                    holder.tv_5min.setTextColor(Color.parseColor("#000000"));
                }
            }
            if(marketDetails.size() >= 3) {
                //15min
                if(last + 1 == 3) {
                    MarketDetail marketDetail = marketDetails.get(0);
                    price2 = marketDetail.tick.close;
                }else {
                    price2 = marketDetails.get(last- 3).price;
                }
                double increase = caculateIncrease(price1, price2);
                if(price1 > price2) {
                    holder.tv_15min.setText("+" + format.format(increase) + "%");
                }else if(price1 < price2){
                    holder.tv_15min.setText("-" + format.format(increase) + "%");
                }else {
                    holder.tv_15min.setText(format.format(increase) + "%");
                }
                if(increase > 2) {
                    if(price1 > price2) {
                        holder.tv_15min.setTextColor(Color.parseColor("#01aa78"));
                    }else {
                        holder.tv_15min.setTextColor(Color.parseColor("#e53600"));
                    }
                }else {
                    holder.tv_15min.setTextColor(Color.parseColor("#000000"));
                }
            }
            if(marketDetails.size() >= 6) {
                //30min
                if(last + 1 == 6) {
                    price2 = marketDetails.get(0).tick.close;
                }else {
                    price2 = marketDetails.get(last - 6).price;
                }
                double increase = caculateIncrease(price1, price2);
                if(price1 > price2) {
                    holder.tv_30min.setText("+" + format.format(increase) + "%");
                }else if(price1 < price2){
                    holder.tv_30min.setText("-" + format.format(increase) + "%");
                }else {
                    holder.tv_30min.setText(format.format(increase) + "%");
                }
                if(increase > 2) {
                    if(price1 > price2) {
                        holder.tv_30min.setTextColor(Color.parseColor("#01aa78"));
                    }else {
                        holder.tv_30min.setTextColor(Color.parseColor("#e53600"));
                    }
                }else {
                    holder.tv_30min.setTextColor(Color.parseColor("#000000"));
                }
            }
            if(marketDetails.size() == 12) {
                //60min
                if(last + 1 == 12) {
                    price2 = marketDetails.get(0).tick.close;
                }else {
                    price2 = marketDetails.get(last - 12).price;
                }
                double increase = caculateIncrease(price1, price2);
                if(price1 > price2) {
                    holder.tv_60min.setText("+" + format.format(increase) + "%");
                }else if(price1 < price2){
                    holder.tv_60min.setText("-" + format.format(increase) + "%");
                }else {
                    holder.tv_60min.setText(format.format(increase) + "%");
                }
                if(increase > 2) {
                    if(price1 > price2) {
                        holder.tv_60min.setTextColor(Color.parseColor("#01aa78"));
                    }else {
                        holder.tv_60min.setTextColor(Color.parseColor("#e5360"));
                    }
                }else {
                    holder.tv_60min.setTextColor(Color.parseColor("#000000"));
                }
            }
        }

    }

    private double caculateIncrease(Double price1, Double price2) {
        if(price1 != null && price2 != null) {
            double diff = Math.abs(price1 - price2) / price1;
            return diff * 100;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return this.mDatas == null ? 0 : this.mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_coin)
        TextView tv_coin;
        @BindView(R.id.tv_5min)
        TextView tv_5min;
        @BindView(R.id.tv_15min)
        TextView tv_15min;
        @BindView(R.id.tv_30min)
        TextView tv_30min;
        @BindView(R.id.tv_60min)
        TextView tv_60min;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
