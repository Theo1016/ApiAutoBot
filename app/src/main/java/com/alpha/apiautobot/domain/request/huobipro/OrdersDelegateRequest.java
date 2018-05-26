package com.alpha.apiautobot.domain.request.huobipro;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * <pre>
 *     author : hedong
 *     e-mail : 739814501@qq.com
 *     time   : 2018/05/25
 *     desc   : xxxx描述
 *     version: 1.0
 * </pre>
 */
public class OrdersDelegateRequest implements Serializable {
    public String symbol;

    String types;

    @SerializedName("start-date")
    String startDate;

    @SerializedName("end-date")
    String endDate;

    String states;      //查询的订单状态组合，使用','分割
                        // pre-submitted 准备提交, submitted 已提交,
                        // partial-filled 部分成交, partial-canceled 部分成交撤销, filled 完全成交, canceled 已撤销

    String from;        //查询起始id

    String direct;      //查询方向：prev 向前 next向后

    String size;        //查询记录大小

}
