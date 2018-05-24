package com.alpha.apiautobot.utils;

import android.util.Base64;
import android.util.Log;
import com.alpha.apiautobot.base.Config;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Theo on 2018/5/17.
 */
public class Util {
    //时间戳格式
    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * binance 签名
     *
     * @param message
     * @param secret
     * @return
     */
    public static String sign(String message, String secret) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secretKeySpec);
            return new String(Hex.encodeHex(sha256_HMAC.doFinal(message.getBytes())));
        } catch (Exception e) {
            throw new RuntimeException("Unable to sign message.", e);
        }
    }

    /**
     * 将加密后的字节数组转换成字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString();
    }

    /**
     * 得到UTC时间，类型为字符串，格式为"yyyy-MM-dd HH:mm"<br />
     * 如果获取失败，返回null
     *
     * @return
     */
    public static String getUTCTimeStr(Date date) {
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcTime = format.format(date);
        Log.e("Util", "timestap is " + utcTime);
        return utcTime;
    }

    /**
     * 库币签名
     */
    public static String kucoinSign(String endpoint,String nonce, String queryString) {
        try {
            //splice string for signing
            String strForSign = endpoint + "/" +nonce + "/" + queryString;
            //Make a base64 encoding of the completed string
            String signatureStr = Base64.encodeToString(strForSign.getBytes("UTF-8"), 0);
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

            SecretKeySpec secretKeySpec = null;
            secretKeySpec = new SecretKeySpec(Config.KUCOIN_SECRET.getBytes("UTF-8"), "HmacSHA256");

            sha256_HMAC.init(secretKeySpec);
            //KC-API-SIGNATURE in header
            String signatureResult = Hex.encodeHexStr(sha256_HMAC.doFinal(signatureStr.getBytes("UTF-8")));
            return signatureResult;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
