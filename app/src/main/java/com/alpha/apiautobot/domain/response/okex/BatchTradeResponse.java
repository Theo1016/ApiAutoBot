package com.alpha.apiautobot.domain.response.okex;

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
public class BatchTradeResponse implements Serializable {
    boolean result;
    @SerializedName("order_info")
    List<OrderInfo> ordeInfo;

    public static class OrderInfo {
        long order_id;
        int error_code;

        public long getOrder_id() {
            return order_id;
        }

        public void setOrder_id(long order_id) {
            this.order_id = order_id;
        }

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }
    }
}
