package com.alpha.apiautobot.domain.response.huobipro;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * <pre>
 *     author : hedong
 *     e-mail : 739814501@qq.com
 *     time   : 2018/05/24
 *     desc   : xxxx描述
 *     version: 1.0
 * </pre>
 */
public class HRAccounts {

    /**
     * status : ok
     * data : [{"id":100009,"type":"spot","state":"working","user-id":1000}]
     */

    @SerializedName("status")
    public String status;
    @SerializedName("data")
    public List<DataBean> data;

    public static HRAccounts objectFromData(String str) {

        return new Gson().fromJson(str, HRAccounts.class);
    }

    public static String objectToString(HRAccounts accounts) {
        return new Gson().toJson(accounts);
    }

    public static class DataBean {
        /**
         * id : 100009
         * type : spot
         * state : working
         * user-id : 1000
         */

        @SerializedName("id")
        public int id;
        @SerializedName("type")
        public String type;
        @SerializedName("state")
        public String state;
        @SerializedName("user-id")
        public int userId;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }
    }
}
