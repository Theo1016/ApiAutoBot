package com.alpha.apiautobot.domain.response.okex;

import java.io.Serializable;

public class Ticker implements Serializable {

    /**
     * date : 1410431279
     * ticker : {"buy":"33.15","high":"34.15","last":"33.15","low":"32.05","sell":"33.16","vol":"10532696.39199642"}
     *
     * date: 返回数据时服务器时间
     buy: 买一价
     high: 最高价
     last: 最新成交价
     low: 最低价
     sell: 卖一价
     vol: 成交量(最近的24小时)
     */

    private String date;
    private TickerBean ticker;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TickerBean getTicker() {
        return ticker;
    }

    public void setTicker(TickerBean ticker) {
        this.ticker = ticker;
    }

    public static class TickerBean {
        /**
         * buy : 33.15
         * high : 34.15
         * last : 33.15
         * low : 32.05
         * sell : 33.16
         * vol : 10532696.39199642
         */

        private String buy;
        private String high;
        private String last;
        private String low;
        private String sell;
        private String vol;

        public String getBuy() {
            return buy;
        }

        public void setBuy(String buy) {
            this.buy = buy;
        }

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public String getLow() {
            return low;
        }

        public void setLow(String low) {
            this.low = low;
        }

        public String getSell() {
            return sell;
        }

        public void setSell(String sell) {
            this.sell = sell;
        }

        public String getVol() {
            return vol;
        }

        public void setVol(String vol) {
            this.vol = vol;
        }
    }
}
