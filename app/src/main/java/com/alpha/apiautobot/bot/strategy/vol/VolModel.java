package com.alpha.apiautobot.bot.strategy.vol;

/**
 * Created by Theo on 2018/7/30.
 */
public class VolModel {
    private String type;
    private double price;
    private double amount;
    private double tradePrecision;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTradePrecision() {
        return tradePrecision;
    }

    public void setTradePrecision(double tradePrecision) {
        this.tradePrecision = tradePrecision;
    }
}
