package com.alpha.apiautobot.domain.response.huobipro;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *     author : hedong
 *     e-mail : 739814501@qq.com
 *     time   : 2018/05/24
 *     desc   : 最近成交记录
 *     version: 1.0
 * </pre>
 */
public class HistoryTrade implements Serializable {

    /**
     * status : ok
     * ch : market.ethusdt.trade.detail
     * ts : 1502448925216
     * data : [{"id":31459998,"ts":1502448920106,"data":[{"id":17592256642623,"amount":0.04,"price":1997,"direction":"buy","ts":1502448920106}]}]
     */

    @SerializedName("status")
    public String status;
    @SerializedName("ch")
    public String ch;
    @SerializedName("ts")
    public long ts;
    @SerializedName("data")
    public List<DataBeanX> data;

    public static HistoryTrade objectFromData(String str) {

        return new Gson().fromJson(str, HistoryTrade.class);
    }

    public static class DataBeanX {
        /**
         * id : 31459998
         * ts : 1502448920106
         * data : [{"id":17592256642623,"amount":0.04,"price":1997,"direction":"buy","ts":1502448920106}]
         */

        @SerializedName("id")
        public int id;
        @SerializedName("ts")
        public long ts;
        @SerializedName("data")
        public List<DataBean> data;

        public static DataBeanX objectFromData(String str) {

            return new Gson().fromJson(str, DataBeanX.class);
        }

        public static class DataBean {
            /**
             * id : 17592256642623
             * amount : 0.04
             * price : 1997
             * direction : buy
             * ts : 1502448920106
             */

            @SerializedName("id")
            public long id;
            @SerializedName("amount")
            public double amount;
            @SerializedName("price")
            public int price;
            @SerializedName("direction")
            public String direction;
            @SerializedName("ts")
            public long ts;

            public static DataBean objectFromData(String str) {

                return new Gson().fromJson(str, DataBean.class);
            }
        }
    }
}
