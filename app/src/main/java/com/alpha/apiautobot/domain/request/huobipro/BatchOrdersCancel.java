package com.alpha.apiautobot.domain.request.huobipro;

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
    private static final long serialVersionUID = 3198628483934597371L;
    @SerializedName("order-ids")
    public List<String> orderIds;

}
