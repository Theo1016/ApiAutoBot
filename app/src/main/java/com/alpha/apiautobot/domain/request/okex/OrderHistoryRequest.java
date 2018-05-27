package com.alpha.apiautobot.domain.request.okex;

import java.io.Serializable;

/**
 * <pre>
 *     author : hedong
 *     e-mail : 739814501@qq.com
 *     time   : 2018/05/27
 *     desc   : 历史订单信息请求
 *     version: 1.0
 * </pre>
 */
public class OrderHistoryRequest implements Serializable {
    /**
     * 请求参数

     参数名	参数类型	必填	描述
     api_key	String	是	用户申请的apiKey
     symbol	String	是	币对如ltc_btc
     status	Integer	是	查询状态 0：未完成的订单 1：已经完成的订单(最近两天的数据)
     current_page	Integer	是	当前页数
     page_length	Integer	是	每页数据条数，最多不超过200
     sign	String	是	请求参数的签名
     */
    String symbol;
    int status;
    int current_page;
    int page_length;

    public OrderHistoryRequest(String symbol, int status, int current_page, int page_length){
        this.symbol = symbol;
        this.status = status;
        this.current_page = current_page;
        this.page_length = page_length;
    }
}
