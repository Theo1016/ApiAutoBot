package com.alpha.apiautobot.domain.response.okex;

import java.io.Serializable;
import java.util.List;

public class CoinDepth implements Serializable {

    /**
     * asks :卖方深度
       bids :买方深度
     */
    private List<List<Integer>> asks;
    private List<List<Double>> bids;

    public List<List<Integer>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<Integer>> asks) {
        this.asks = asks;
    }

    public List<List<Double>> getBids() {
        return bids;
    }

    public void setBids(List<List<Double>> bids) {
        this.bids = bids;
    }
}
