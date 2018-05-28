package com.alpha.apiautobot.domain.dao.kucoin;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.ArrayList;

/**
 * Created by Theo on 2017/10/12.
 */
@Entity
public class Market {
    @Id
    private Long id;

    @Property(nameInDb = "coinType")
    String coinType;

    @Property(nameInDb = "trading")
    boolean trading;

    @Property(nameInDb = "lastDealPrice")
    Double lastDealPrice;

    @Property(nameInDb = "buy")
    Double buy;

    @Property(nameInDb = "sell")
    Double sell;

    @Property(nameInDb = "coinTypePair")
    String coinTypePair;

    @Property(nameInDb = "sort")
    Double sort;

    @Property(nameInDb = "feeRate")
    Double feeRate;

    @Property(nameInDb = "volValue")
    Double volValue;

    @Property(nameInDb = "high")
    Double high;

    @Property(nameInDb = "datetime")
    long datetime;

    @Property(nameInDb = "vol")
    Double vol;

    @Property(nameInDb = "low")
    Double low;

    @Property(nameInDb = "changeRate")
    Double changeRate;

    @Property(nameInDb = "change")
    Double change;

    @Property(nameInDb = "timeStamps")
    String timeStamps;

    @Property(nameInDb = "lastDealPrices")
    String lastDealPrices;



    @Generated(hash = 1560374568)
    public Market(Long id, String coinType, boolean trading, Double lastDealPrice,
            Double buy, Double sell, String coinTypePair, Double sort,
            Double feeRate, Double volValue, Double high, long datetime, Double vol,
            Double low, Double changeRate, Double change, String timeStamps,
            String lastDealPrices) {
        this.id = id;
        this.coinType = coinType;
        this.trading = trading;
        this.lastDealPrice = lastDealPrice;
        this.buy = buy;
        this.sell = sell;
        this.coinTypePair = coinTypePair;
        this.sort = sort;
        this.feeRate = feeRate;
        this.volValue = volValue;
        this.high = high;
        this.datetime = datetime;
        this.vol = vol;
        this.low = low;
        this.changeRate = changeRate;
        this.change = change;
        this.timeStamps = timeStamps;
        this.lastDealPrices = lastDealPrices;
    }

    @Generated(hash = 1454995179)
    public Market() {
    }
    
    
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoinType() {
        return this.coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public boolean getTrading() {
        return this.trading;
    }

    public void setTrading(boolean trading) {
        this.trading = trading;
    }

    public boolean isTrading() {
        return trading;
    }

    public Double getLastDealPrice() {
        return lastDealPrice;
    }

    public void setLastDealPrice(Double lastDealPrice) {
        this.lastDealPrice = lastDealPrice;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getSell() {
        return sell;
    }

    public void setSell(Double sell) {
        this.sell = sell;
    }

    public String getCoinTypePair() {
        return coinTypePair;
    }

    public void setCoinTypePair(String coinTypePair) {
        this.coinTypePair = coinTypePair;
    }

    public Double getSort() {
        return sort;
    }

    public void setSort(Double sort) {
        this.sort = sort;
    }

    public Double getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(Double feeRate) {
        this.feeRate = feeRate;
    }

    public Double getVolValue() {
        return volValue;
    }

    public void setVolValue(Double volValue) {
        this.volValue = volValue;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public Double getVol() {
        return vol;
    }

    public void setVol(Double vol) {
        this.vol = vol;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(Double changeRate) {
        this.changeRate = changeRate;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public String getTimeStamps() {
        return this.timeStamps;
    }

    public void setTimeStamps(String timeStamps) {
        this.timeStamps = timeStamps;
    }

    public String getLastDealPrices() {
        return this.lastDealPrices;
    }

    public void setLastDealPrices(String lastDealPrices) {
        this.lastDealPrices = lastDealPrices;
    }

}
