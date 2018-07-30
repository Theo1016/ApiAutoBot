package com.alpha.apiautobot.bot.strategy;


import com.alpha.apiautobot.bot.strategy.vol.OrderModel;
import com.alpha.apiautobot.bot.strategy.vol.OrderResult;
import com.alpha.apiautobot.bot.strategy.vol.VolModel;

/**
 * Created by Theo on 2018/7/16.
 */
public interface StrategeVol {

    void vol();

    abstract class Factory<T extends VolModel, R extends OrderModel> implements StrategeVol {
        double COEFFICIENT = 10;//系数
        int COUNT = 1;//倍数
        T t;
        R r;

        //下买单
        public abstract boolean createBuyOrders(T t);

        //下卖单
        public abstract boolean createSellOrders(T t);

        //查询是否撮合
        public abstract R queryOrders(T t);

        //如果未成功撮合，买单被吃时，停止挂卖单，将买单价格往下调整，并等待撮合
        //如果未成功撮合，卖单被吃时，阶梯式挂买单，将卖单价格往上调整，并等待撮合
        //撮合成功，递归
        public void processDatas() {
            //下买卖单
            createBuyOrders(t);
            createSellOrders(t);
            //查询是否撮合
            r = queryOrders(t);

            if (r.getTag().equals(OrderResult.COMPLETED_TRANSACTION)) {
                //成功撮合,递归
                processDatas();
                initDatas();
            }
            if (r.getTag().equals(OrderResult.BUYORDER_TRANSACTION)) {
                //如果未成功撮合，买单被吃时，停止挂卖单，将买单价格往下调整，并等待撮合
                t.setPrice(t.getPrice() - t.getTradePrecision() * COEFFICIENT * COUNT);
                createBuyOrders(t);
                COEFFICIENT=COEFFICIENT*1.2;
                COUNT++;
                queryOrders(t);
            }
            if (r.getTag().equals(OrderResult.SELLORDER_TRANSACTION)) {
                //如果未成功撮合，卖单被吃时，阶梯式挂买单，并将卖单价格往上调整，并等待撮合
                t.setPrice(t.getPrice() - t.getTradePrecision() * COEFFICIENT * COUNT);
                createBuyOrders(t);
                t.setPrice(t.getPrice() + t.getTradePrecision() * COEFFICIENT * COUNT);
                createSellOrders(t);
                COEFFICIENT=COEFFICIENT*1.2;
                COUNT++;
            }
        }

        private void initDatas() {
            COEFFICIENT=10;
            COUNT = 1;
        }


    }
}

