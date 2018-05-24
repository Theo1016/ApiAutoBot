package com.alpha.apiautobot.domain.request.huobipro;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * <pre>
 *     author : hedong
 *     e-mail : 739814501@qq.com
 *     time   : 2018/05/24
 *     desc   : 下单
 *     version: 1.0
 * </pre>
 */
public class PlaceOrders implements Serializable {
    public static interface OrderType {
        /**
         * 限价买入
         */
        static final String BUY_LIMIT = "buy-limit";
        /**
         * 限价卖出
         */
        static final String SELL_LIMIT = "sell-limit";
        /**
         * 市价买入
         */
        static final String BUY_MARKET = "buy-market";
        /**
         * 市价卖出
         */
        static final String SELL_MARKET = "sell-market";
    }

    @SerializedName("account-id")
    public String accountId;

    public String amount;      //限价单表示下单数量，市价买单时表示买多少钱，市价卖单时表示卖多少币

    public String price;       //下单价格，市价单不传该参数

    public String source;

    public String symbol;

    public String type;        //订单类型


}
