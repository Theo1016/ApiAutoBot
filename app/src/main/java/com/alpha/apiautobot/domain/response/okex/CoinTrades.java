package com.alpha.apiautobot.domain.response.okex;

import java.io.Serializable;

public class CoinTrades implements Serializable {
    /**
     * date : 1367130137
     * date_ms : 1367130137000
     * price : 787.71
     * amount : 0.003
     * tid : 230433
     * type : sell
     */

    private String date;
    private String date_ms;
    private double price;
    private double amount;
    private String tid;
    private String type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate_ms() {
        return date_ms;
    }

    public void setDate_ms(String date_ms) {
        this.date_ms = date_ms;
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

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
