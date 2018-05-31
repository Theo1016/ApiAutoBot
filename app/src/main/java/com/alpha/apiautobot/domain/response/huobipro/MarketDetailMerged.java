package com.alpha.apiautobot.domain.response.huobipro;

import java.io.Serializable;
import java.util.List;

public class MarketDetailMerged implements Serializable {

    /**
     * status : ok
     * ch : market.ethusdt.detail.merged
     * ts : 1499225276950
     * tick : {"id":1499225271,"ts":1499225271000,"close":1885,"open":1960,"high":1985,"low":1856,"amount":81486.2926,"count":42122,"vol":1.57052744857082E8,"ask":[1885,21.8804],"bid":[1884,1.6702]}
     */

    private String status;
    private String ch;
    private long ts;
    private TickBean tick;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public TickBean getTick() {
        return tick;
    }

    public void setTick(TickBean tick) {
        this.tick = tick;
    }

    public static class TickBean {
        /**
         * id : 1499225271
         * ts : 1499225271000
         * close : 1885
         * open : 1960
         * high : 1985
         * low : 1856
         * amount : 81486.2926
         * count : 42122
         * vol : 1.57052744857082E8
         * ask : [1885,21.8804] [买1价， 买1量]
         * bid : [1884,1.6702]  [卖1价，卖1量]
         */

        private int id;
        private long ts;
        private int close;
        private int open;
        private int high;
        private int low;
        private double amount;
        private int count;
        private double vol;
        private List<Integer> ask;
        private List<Integer> bid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getTs() {
            return ts;
        }

        public void setTs(long ts) {
            this.ts = ts;
        }

        public int getClose() {
            return close;
        }

        public void setClose(int close) {
            this.close = close;
        }

        public int getOpen() {
            return open;
        }

        public void setOpen(int open) {
            this.open = open;
        }

        public int getHigh() {
            return high;
        }

        public void setHigh(int high) {
            this.high = high;
        }

        public int getLow() {
            return low;
        }

        public void setLow(int low) {
            this.low = low;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public double getVol() {
            return vol;
        }

        public void setVol(double vol) {
            this.vol = vol;
        }

        public List<Integer> getAsk() {
            return ask;
        }

        public void setAsk(List<Integer> ask) {
            this.ask = ask;
        }

        public List<Integer> getBid() {
            return bid;
        }

        public void setBid(List<Integer> bid) {
            this.bid = bid;
        }
    }
}
