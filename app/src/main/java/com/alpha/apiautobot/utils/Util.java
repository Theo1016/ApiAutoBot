package com.alpha.apiautobot.utils;

import android.util.Base64;
import android.util.Log;
import com.alpha.apiautobot.base.Config;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
            String signatureStr = Base64.encodeToString(strForSign.getBytes("UTF-8"), 2);
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

    /**
     * OKEX MD5加密
     * @param queryString
     * @return
     */
    public static String okexSign(String queryString) {
        String result = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update((queryString).getBytes("UTF-8"));
            byte b[] = md5.digest();

            int i;
            StringBuffer buf = new StringBuffer("");

            for(int offset=0; offset<b.length; offset++){
                i = b[offset];
                if(i<0){
                    i+=256;
                }
                if(i<16){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }

            result = buf.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 生成32位大写MD5值
     */
    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static String getMD5String(String str) {
        try {
            if (str == null || str.trim().length() == 0) {
                return "";
            }
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            bytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >> 4] + ""
                        + HEX_DIGITS[bytes[i] & 0xf]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String[] delete(int index,String array[]){
        //根据删除索引，把数组后面的向前移一位
        for(int i=index;i<array.length;i++){
            if(i!=array.length-1){
                array[i]=array[i+1];
            }else{//处理最后一位超出情况
                array[i]=array[i];
            }
        }
        return array;
    }

    public static String stringArrayConvertStirng(String[] stringArray){
        StringBuilder sb = new StringBuilder();
        for(String str : stringArray){
            sb.append(str+"&&");
        }
        sb.replace(sb.length()-1,sb.length(),"");
        return sb.toString();
    }

}
