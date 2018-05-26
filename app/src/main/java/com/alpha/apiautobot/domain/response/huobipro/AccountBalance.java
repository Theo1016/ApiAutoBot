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
 *     desc   : 账户余额
 *     version: 1.0
 * </pre>
 */
public class AccountBalance implements Serializable {


    /**
     * status : ok
     * data : {"id":100009,"type":"spot","state":"working","list":[{"currency":"usdt","type":"trade","balance":"500009195917.4362872650"},{"currency":"usdt","type":"frozen","balance":"328048.1199920000"},{"currency":"etc","type":"trade","balance":"499999894616.1302471000"},{"currency":"etc","type":"frozen","balance":"9786.6783000000"},{"currency":"eth","type":"trade","balance":"499999894616.1302471000"},{"currency":"eth","type":"frozen","balance":"9786.6783000000"}],"user-id":1000}
     */

    @SerializedName("status")
    public String status;
    @SerializedName("data")
    public DataBean data;

    public static AccountBalance objectFromData(String str) {

        return new Gson().fromJson(str, AccountBalance.class);
    }

    public static class DataBean {
        /**
         * id : 100009
         * type : spot
         * state : working
         * list : [{"currency":"usdt","type":"trade","balance":"500009195917.4362872650"},{"currency":"usdt","type":"frozen","balance":"328048.1199920000"},{"currency":"etc","type":"trade","balance":"499999894616.1302471000"},{"currency":"etc","type":"frozen","balance":"9786.6783000000"},{"currency":"eth","type":"trade","balance":"499999894616.1302471000"},{"currency":"eth","type":"frozen","balance":"9786.6783000000"}]
         * user-id : 1000
         */

        @SerializedName("id")
        public int id;
        @SerializedName("type")
        public String type;
        @SerializedName("state")
        public String state;
        @SerializedName("user-id")
        public int userid;
        @SerializedName("list")
        public List<ListBean> list;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static class ListBean {
            /**
             * currency : usdt
             * type : trade
             * balance : 500009195917.4362872650
             */

            @SerializedName("currency")
            public String currency;
            @SerializedName("type")
            public String type;
            @SerializedName("balance")
            public String balance;

            public static ListBean objectFromData(String str) {

                return new Gson().fromJson(str, ListBean.class);
            }
        }
    }
}
