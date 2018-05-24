package com.alpha.apiautobot.domain.response.huobipro;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *     author : hedong
 *     e-mail : 739814501@qq.com
 *     time   : 2018/05/25
 *     desc   : 查询当前委托、历史委托
 *     version: 1.0
 * </pre>
 */
public class OrdersDelegate implements Serializable {

    /**
     * status : ok
     * data : [{"id":59378,"symbol":"ethusdt","account-id":100009,"amount":"10.1000000000","price":"100.1000000000","created-at":1494901162595,"type":"buy-limit","field-amount":"10.1000000000","field-cash-amount":"1011.0100000000","field-fees":"0.0202000000","finished-at":1494901400468,"user-id":1000,"source":"api","state":"filled","canceled-at":0,"exchange":"huobi","batch":""}]
     */

    @SerializedName("status")
    public String status;
    @SerializedName("data")
    public List<DataBean> data;

    public static OrdersDelegate objectFromData(String str) {

        return new Gson().fromJson(str, OrdersDelegate.class);
    }

    public static class DataBean {
        /**
         * id : 59378
         * symbol : ethusdt
         * account-id : 100009
         * amount : 10.1000000000
         * price : 100.1000000000
         * created-at : 1494901162595
         * type : buy-limit
         * field-amount : 10.1000000000
         * field-cash-amount : 1011.0100000000
         * field-fees : 0.0202000000
         * finished-at : 1494901400468
         * user-id : 1000
         * source : api
         * state : filled
         * canceled-at : 0
         * exchange : huobi
         * batch :
         */

        @SerializedName("id")
        public int id;
        @SerializedName("symbol")
        public String symbol;
        @SerializedName("account-id")
        public int accountId;
        @SerializedName("amount")
        public String amount;
        @SerializedName("price")
        public String price;
        @SerializedName("created-at")
        public long createdAt;
        @SerializedName("type")
        public String type;
        @SerializedName("field-amount")
        public String fieldAmount;
        @SerializedName("field-cash-amount")
        public String fieldCashAmount;
        @SerializedName("field-fees")
        public String fieldFees;
        @SerializedName("finished-at")
        public long finishedAt;
        @SerializedName("user-id")
        public int userId;
        @SerializedName("source")
        public String source;
        @SerializedName("state")
        public String state;
        @SerializedName("canceled-at")
        public int canceledAt;
        @SerializedName("exchange")
        public String exchange;
        @SerializedName("batch")
        public String batch;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }
    }
}
