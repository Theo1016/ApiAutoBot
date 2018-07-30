package com.alpha.apiautobot.bot.strategy.vol;

/**
 * Created by Theo on 2018/7/30.
 */
public enum  OrderResult {
    COMPLETED_TRANSACTION("completed transaction"),//完全成交
    BUYORDER_TRANSACTION("buy order transaction"),//买单被吃
    SELLORDER_TRANSACTION("sell order transaction");//卖单被吃
    String tag;
    OrderResult(String tag){this.tag= tag;}
}
