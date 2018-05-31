package com.alpha.apiautobot.domain.response.huobipro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class MarketDepth implements Serializable {

    /**
     * status : ok
     * ch : market.btcusdt.depth.step1
     * ts : 1489472598812
     * tick : {"id":1489464585407,"ts":1489464585407,"bids":[[7964,0.0678],[7963,0.9162],[7961,0.1],[7960,12.8898],[7958,1.2],[7955,2.1009],[7954,0.4708],[7953,0.0564],[7951,2.8031],[7950,13.7785],[7949,0.125],[7948,4],[7942,0.4337],[7940,6.1612],[7936,0.02],[7935,1.3575],[7933,2.002],[7932,1.3449],[7930,10.2974],[7929,3.2226]],"asks":[[7979,0.0736],[7980,1.0292],[7981,5.5652],[7986,0.2416],[7990,1.997],[7995,0.88],[7996,0.0212],[8000,9.2609],[8002,0.02],[8008,1],[8010,0.8735],[8011,2.36],[8012,0.02],[8014,0.1067],[8015,12.9118],[8016,2.5206],[8017,0.0166],[8018,1.3218],[8019,0.01],[8020,13.6584]]}
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
         * id : 1489464585407
         * ts : 1489464585407
         * bids : [[7964,0.0678],[7963,0.9162],[7961,0.1],[7960,12.8898],[7958,1.2],[7955,2.1009],[7954,0.4708],[7953,0.0564],[7951,2.8031],[7950,13.7785],[7949,0.125],[7948,4],[7942,0.4337],[7940,6.1612],[7936,0.02],[7935,1.3575],[7933,2.002],[7932,1.3449],[7930,10.2974],[7929,3.2226]]
         * asks : [[7979,0.0736],[7980,1.0292],[7981,5.5652],[7986,0.2416],[7990,1.997],[7995,0.88],[7996,0.0212],[8000,9.2609],[8002,0.02],[8008,1],[8010,0.8735],[8011,2.36],[8012,0.02],[8014,0.1067],[8015,12.9118],[8016,2.5206],[8017,0.0166],[8018,1.3218],[8019,0.01],[8020,13.6584]]
         */

        private long id;
        private long ts;
        private List<List<BigDecimal>> bids;
        private List<List<BigDecimal>> asks;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getTs() {
            return ts;
        }

        public void setTs(long ts) {
            this.ts = ts;
        }

        public List<List<BigDecimal>> getBids() {
            return bids;
        }

        public void setBids(List<List<BigDecimal>> bids) {
            this.bids = bids;
        }

        public List<List<BigDecimal>> getAsks() {
            return asks;
        }

        public void setAsks(List<List<BigDecimal>> asks) {
            this.asks = asks;
        }
    }
}
