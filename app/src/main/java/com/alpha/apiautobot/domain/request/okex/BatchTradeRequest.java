package com.alpha.apiautobot.domain.request.okex;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *     author : hedong
 *     e-mail : 739814501@qq.com
 *     time   : 2018/05/27
 *     desc   : xxxx描述
 *     version: 1.0
 * </pre>
 */
public class BatchTradeRequest implements Serializable {
    /**
     * 请求参数

     参数名	参数类型	必填	描述
     api_key	String	是	用户申请的apiKey
     symbol	String	是	币对如ltc_btc
     type	String	否	买卖类型：限价单(buy/sell)
     orders_data	String(格式[{price:3,amount:5,type:'sell'},{price:3,amount:3,type:'buy'}])	是	最大下单量为5， price和amount参数参考trade接口中的说明，最终买卖类型由orders_data 中type 为准，如orders_data不设定type 则由上面type设置为准。
     sign	String	是	请求参数的签名
     */
    public String symbol;
    String type;
    String orders_data;

    public BatchTradeRequest(String symbol, String type, List<OrdersData> ordersDataList) {
        this.symbol = symbol;
        this.type = type;
        this.orders_data = new Gson().toJson(ordersDataList);
    }

    public static class OrdersData{
        public double price;
        public double amount;
        public String type;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
