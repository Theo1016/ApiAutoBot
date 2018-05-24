package com.alpha.apiautobot.domain.response.huobipro;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * <pre>
 *     author : hedong
 *     e-mail : 739814501@qq.com
 *     time   : 2018/05/24
 *     desc   : 24小时成交量数据
 *     version: 1.0
 * </pre>
 */
public class MarketDetail implements Serializable {

    /**
     * status : ok
     * ch : market.btcusdt.detail
     * ts : 1489473538996
     * tick : {"amount":4316.4346,"open":8090.54,"close":7962.62,"high":8119,"ts":1489464451000,"id":1489464451,"count":9595,"low":7875,"vol":3.449727690576E7}
     */

    @SerializedName("status")
    public String status;
    @SerializedName("ch")
    public String ch;
    @SerializedName("ts")
    public long ts;
    @SerializedName("tick")
    public TickBean tick;

    @SerializedName("err-code")
    public String errCode;
    @SerializedName("err-msg")
    public String errMsg;

    public static MarketDetail objectFromData(String str) {

        return new Gson().fromJson(str, MarketDetail.class);
    }

    public static class TickBean {
        /**
         * amount : 4316.4346
         * open : 8090.54
         * close : 7962.62
         * high : 8119
         * ts : 1489464451000
         * id : 1489464451
         * count : 9595
         * low : 7875
         * vol : 3.449727690576E7
         */

        @SerializedName("amount")
        public double amount;   //24小时成交量
        @SerializedName("open")
        public double open;     //前推24小时成交量
        @SerializedName("close")
        public double close;    //当前成交价
        @SerializedName("high")
        public int high;        //近24小时最高价
        @SerializedName("id")
        public int id;
        @SerializedName("count")
        public int count;       //近24小时累积成交数
        @SerializedName("low")
        public int low;         //近24小时最低价
        @SerializedName("vol")
        public double vol;      //近24小时累积成交额，即sum（每一笔成交价 * 该笔的数量）

        public double version;

        public static TickBean objectFromData(String str) {

            return new Gson().fromJson(str, TickBean.class);
        }
    }
}
