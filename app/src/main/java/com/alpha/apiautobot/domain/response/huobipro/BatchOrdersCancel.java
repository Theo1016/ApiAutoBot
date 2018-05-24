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
 *     desc   : 批量撤销订单
 *     version: 1.0
 * </pre>
 */
public class BatchOrdersCancel implements Serializable {

    /**
     * status : ok
     * data : {"success":["1","3"],"failed":[{"err-msg":"记录无效","order-id":"2","err-code":"base-record-invalid"}]}
     */

    @SerializedName("status")
    public String status;
    @SerializedName("data")
    public DataBean data;

    public static BatchOrdersCancel objectFromData(String str) {

        return new Gson().fromJson(str, BatchOrdersCancel.class);
    }

    public static class DataBean {
        @SerializedName("success")
        public List<String> success;
        @SerializedName("failed")
        public List<FailedBean> failed;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static class FailedBean {
            /**
             * err-msg : 记录无效
             * order-id : 2
             * err-code : base-record-invalid
             */

            @SerializedName("err-msg")
            public String errmsg;
            @SerializedName("order-id")
            public String orderid;
            @SerializedName("err-code")
            public String errcode;

            public static FailedBean objectFromData(String str) {

                return new Gson().fromJson(str, FailedBean.class);
            }
        }
    }
}
