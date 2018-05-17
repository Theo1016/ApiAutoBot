package com.alpha.apiautobot.platform;

/**
 * Created by Theo on 2018/5/16.
 */
public interface Platform {

    /**
     * 加载配置
     */
    public void loadConfig();

    /**
     * 连接
     */
    public void connection();

    /**
     * 断开连接
     */
    public void disConnection();

    /**
     * 获取市场行情
     */
    public Object getMarketList();

    /**
     * 获取单个币种数据
     */
    public Object getTick();

    /**
     * 获取账号数据
     */
    public Object getAccountInfo();

    /**
     * 买
     */
    public void buyCoin();

    /**
     * 卖
     */
    public void sellCoin();

    /**
     * 转入
     */
    public void deposite();

    /**
     * 转出
     */
    public void withdrawal();

}
