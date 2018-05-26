package com.alpha.apiautobot.domain.response.okex;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.Serializable;

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

            JSONObject borrow;
            private JSONObject free;
            private JSONObject freezed;

            public JSONObject getFree() {
                return free;
            }

            public void setFree(JSONObject free) {
                this.free = free;
            }

            public JSONObject getFreezed() {
                return freezed;
            }

            public void setFreezed(JSONObject freezed) {
                this.freezed = freezed;
            }

        }
    }
}
