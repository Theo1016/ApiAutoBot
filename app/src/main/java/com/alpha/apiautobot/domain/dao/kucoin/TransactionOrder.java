package com.alpha.apiautobot.domain.dao.kucoin;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Theo on 2018/5/29.
 */
@Entity
public class TransactionOrder {
    @Id
    private Long id;

    @Property(nameInDb = "coinType")
    String coinType;

    @Property(nameInDb = "buy_price")
    String buyPrice;

    @Property(nameInDb = "buy_amount")
    String buyAmount;

    @Property(nameInDb = "buy_volume")
    String buyVolume;

    @Property(nameInDb = "sell_price")
    String sellPrice;

    @Property(nameInDb = "sell_amount")
    String sellAmount;

    @Property(nameInDb = "sell_volume")
    String sellVolume;

    @Property(nameInDb = "timeStamp")
    long timeStamp;

    @Generated(hash = 1102360354)
    public TransactionOrder(Long id, String coinType, String buyPrice,
            String buyAmount, String buyVolume, String sellPrice, String sellAmount,
            String sellVolume, long timeStamp) {
        this.id = id;
        this.coinType = coinType;
        this.buyPrice = buyPrice;
        this.buyAmount = buyAmount;
        this.buyVolume = buyVolume;
        this.sellPrice = sellPrice;
        this.sellAmount = sellAmount;
        this.sellVolume = sellVolume;
        this.timeStamp = timeStamp;
    }

    @Generated(hash = 1137796498)
    public TransactionOrder() {
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(String buyAmount) {
        this.buyAmount = buyAmount;
    }

    public String getBuyVolume() {
        return buyVolume;
    }

    public void setBuyVolume(String buyVolume) {
        this.buyVolume = buyVolume;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(String sellAmount) {
        this.sellAmount = sellAmount;
    }

    public String getSellVolume() {
        return sellVolume;
    }

    public void setSellVolume(String sellVolume) {
        this.sellVolume = sellVolume;
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

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
