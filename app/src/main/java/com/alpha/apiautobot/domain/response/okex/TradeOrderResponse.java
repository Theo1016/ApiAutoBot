package com.alpha.apiautobot.domain.response.okex;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * <pre>
 *     author : hedong
 *     e-mail : 739814501@qq.com
 *     time   : 2018/05/27
 *     desc   : 下单
 *     version: 1.0
 * </pre>
 */
public class TradeOrderResponse implements Serializable {
    boolean result;
    @SerializedName("order_id")
    long orderId;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
