package com.alpha.apiautobot.domain.response.kucoin;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Theo on 2017/10/12.
 */
public class MarketModel extends BaseModel {
    public ArrayList<data> data;

    public ArrayList<data> getData() {
        return data;
    }

    public void setData(ArrayList<data> data) {
        this.data = data;
    }

    public static class data implements Serializable {
        String coinType;
        boolean trading;
        double lastDealPrice;
        double buy;
        double sell;
        double change;
        String coinTypePair;
        double sort;
        double feeRate;
        double volValue;
        double high;
        long datetime;
        double vol;
        double low;
        double changeRate;

        public String getCoinType() {
            return coinType;
        }

        public void setCoinType(String coinType) {
            this.coinType = coinType;
        }

        public boolean isTrading() {
            return trading;
        }

        public void setTrading(boolean trading) {
            this.trading = trading;
        }

        public double getLastDealPrice() {
            return lastDealPrice;
        }

        public void setLastDealPrice(double lastDealPrice) {
            this.lastDealPrice = lastDealPrice;
        }

        public double getBuy() {
            return buy;
        }

        public void setBuy(double buy) {
            this.buy = buy;
        }

        public double getSell() {
            return sell;
        }

        public void setSell(double sell) {
            this.sell = sell;
        }

        public String getCoinTypePair() {
            return coinTypePair;
        }

        public void setCoinTypePair(String coinTypePair) {
            this.coinTypePair = coinTypePair;
        }

        public double getSort() {
            return sort;
        }

        public void setSort(double sort) {
            this.sort = sort;
        }

        public double getFeeRate() {
            return feeRate;
        }

        public void setFeeRate(double feeRate) {
            this.feeRate = feeRate;
        }

        public double getVolValue() {
            return volValue;
        }

        public void setVolValue(double volValue) {
            this.volValue = volValue;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public long getDatetime() {
            return datetime;
        }

        public void setDatetime(long datetime) {
            this.datetime = datetime;
        }

        public double getVol() {
            return vol;
        }

        public void setVol(double vol) {
            this.vol = vol;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public double getChangeRate() {
            return changeRate;
        }

        public void setChangeRate(double changeRate) {
            this.changeRate = changeRate;
        }

        public double getChange() {
            return change;
        }

        public void setChange(double change) {
            this.change = change;
        }
    }




}
