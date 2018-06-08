package com.alpha.apiautobot.platform.huobipro;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * 心跳包
 */
public class BeatHeart implements Serializable {

    private static final long serialVersionUID = -857580852909953259L;

    public static class PING {
        public Long ping;

        public Long getPing() {
            return ping;
        }

        public void setPing(Long ping) {
            this.ping = ping;
        }
    }

    public static class PONG {
        public Long pong;

        public PONG(PING ping) {
            this.pong = ping.ping;
        }

        @Override
        public String toString() {
            try {
                JSONObject jsonObject = new JSONObject("{}");
                jsonObject.put("pong", pong);
                return jsonObject.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return super.toString();
        }
    }

}
