package com.alpha.apiautobot.domain.response.okex;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

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
public class OrderHistoryResponse implements Serializable {

    /**
     * current_page : 1
     * orders : [{"amount":0,"avg_price":0,"create_date":1405562100000,"deal_amount":0,"order_id":0,"price":0,"status":2,"symbol":"btc_usd","type":"sell"}]
     * page_length : 1
     * result : true
     * total : 3
     */

    @SerializedName("currency_page")
    public int currentPage;
    @SerializedName("page_length")
    public int pageLength;
    @SerializedName("result")
    public boolean result;
    @SerializedName("total")
    public int total;
    @SerializedName("orders")
    public List<OrdersBean> orders;

    public static OrderHistoryResponse objectFromData(String str) {

        return new Gson().fromJson(str, OrderHistoryResponse.class);
    }

    public static class OrdersBean {
        /**
         * amount : 0
         * avg_price : 0
         * create_date : 1405562100000
         * deal_amount : 0
         * order_id : 0
         * price : 0
         * status : 2
         * symbol : btc_usd
         * type : sell
         */

        @SerializedName("amount")
        public int amount;
        @SerializedName("avg_price")
        public int avgPrice;
        @SerializedName("create_date")
        public long createDate;
        @SerializedName("deal_amount")
        public int dealAmount;
        @SerializedName("order_id")
        public int orderId;
        @SerializedName("price")
        public int price;
        @SerializedName("status")
        public int status;
        @SerializedName("symbol")
        public String symbol;
        @SerializedName("type")
        public String type;

        public static OrdersBean objectFromData(String str) {

            return new Gson().fromJson(str, OrdersBean.class);
        }
    }
}
