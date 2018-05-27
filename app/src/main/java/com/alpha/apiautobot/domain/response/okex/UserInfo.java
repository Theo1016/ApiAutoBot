package com.alpha.apiautobot.domain.response.okex;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

public class UserInfo implements Serializable {

    /**
     * info : {"funds":{"free":{"btc":"0","usd":"0","ltc":"0","eth":"0"},"freezed":{"btc":"0","usd":"0","ltc":"0","eth":"0"}}}
     * result : true
     */

    private InfoBean info;
    private boolean result;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public static class InfoBean {
        /**
         * funds : {"free":{"btc":"0","usd":"0","ltc":"0","eth":"0"},"freezed":{"btc":"0","usd":"0","ltc":"0","eth":"0"}}
         */

        private FundsBean funds;

        public FundsBean getFunds() {
            return funds;
        }

        public void setFunds(FundsBean funds) {
            this.funds = funds;
        }

        public static class FundsBean {
            /**
             * free : {"btc":"0","usd":"0","ltc":"0","eth":"0"}
             * freezed : {"btc":"0","usd":"0","ltc":"0","eth":"0"}
             */

            private Map<String, String> borrow;
            private Map<String, String> free;
            private Map<String, String> freezed;
//
//            public Map<String, String> getBorrow() {
//                return borrow;
//            }
//
//            public void setBorrow(Map<String, String> borrow) {
//                this.borrow = borrow;
//            }
//
//            public Map<String, String> getFree() {
//                return free;
//            }
//
//            public void setFree(Map<String, String> free) {
//                this.free = free;
//            }
//
//            public Map<String, String> getFreezed() {
//                return freezed;
//            }
//
//            public void setFreezed(Map<String, String> freezed) {
//                this.freezed = freezed;
//            }

            //            public JSONObject getFree() {
//                return free;
//            }
//
//            public void setFree(JSONObject free) {
//                this.free = free;
//            }
//
//            public JSONObject getFreezed() {
//                return freezed;
//            }
//
//            public void setFreezed(JSONObject freezed) {
//                this.freezed = freezed;
//            }

        }
    }
}
