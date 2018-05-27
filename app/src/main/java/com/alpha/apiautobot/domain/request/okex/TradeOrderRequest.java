package com.alpha.apiautobot.domain.request.okex;

import java.io.Serializable;

/**
 * <pre>
 *     author : hedong
 *     e-mail : 739814501@qq.com
 *     time   : 2018/05/27
 *     desc   : xxxx描述
 *     version: 1.0
 * </pre>
 */
public class TradeOrderRequest implements Serializable {
    /**
     * 请求参数

     参数名	参数类型	必填	描述
     api_key	String	是	用户申请的apiKey
     symbol	String	是	币对如ltc_btc
     type	String	是	买卖类型：限价单(buy/sell) 市价单(buy_market/sell_market)
     price	Double	否	下单价格 市价卖单不传price
     amount	Double	否	交易数量 市价买单不传amount
     sign	String	是	请求参数的签名
     */
    String symbol;
    String type;
    Double price;
    Double amount;

    public TradeOrderRequest(String symbol, String type, Double price, Double amount) {
        this.symbol = symbol;
        this.type = type;
        this.price = price;
        this.amount = amount;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
