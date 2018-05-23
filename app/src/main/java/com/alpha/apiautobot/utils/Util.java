package com.alpha.apiautobot.utils;

import android.util.Log;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Theo on 2018/5/17.
 */
public class Util {
    //时间戳格式
    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss") ;

    public static String sign(String message, String secret) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secretKeySpec);
<<<<<<< HEAD
            return new String(Hex.encodeHex(sha256_HMAC.doFinal(message.getBytes())));
        } catch (Exception e) {
            throw new RuntimeException("Unable to sign message.", e);
=======
            return byteArrayToHexString(sha256_HMAC.doFinal(signatureStr.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] HMACB(String signatureStr, String secret) {
        Mac sha256_HMAC = null;
        try {
            sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = null;
            secretKeySpec = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secretKeySpec);
            return sha256_HMAC.doFinal(signatureStr.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
>>>>>>> d3b887c9b9e82ffd5dd0456c055e399bdb02ae7e
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
        for (int n = 0; b!=null && n < b.length; n++) {
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
     * @return
     */
    public static String getUTCTimeStr(Date date) {
//        StringBuffer UTCTimeBuffer = new StringBuffer();
//        // 1、取得本地时间：
//        Calendar cal = Calendar.getInstance() ;
//        // 2、取得时间偏移量：
//        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
//        // 3、取得夏令时差：
//        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
//        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
//        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH)+1;
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        int hour = cal.get(Calendar.HOUR_OF_DAY);
//        int minute = cal.get(Calendar.MINUTE);
//        int second = cal.get(Calendar.MILLISECOND);
//        UTCTimeBuffer.append(year).append("-").append(month).append("-").append(day) ;
//        UTCTimeBuffer.append("T").append(hour).append(":").append(minute).append(":").append(second) ;
//        try{
//            format.parse(UTCTimeBuffer.toString()) ;
//            Log.e("Util", "timestap is " + UTCTimeBuffer.toString());
//            return UTCTimeBuffer.toString();
//        }catch(ParseException e)
//        {
//            e.printStackTrace() ;
//        }
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcTime = format.format(date);
        Log.e("Util", "timestap is " + utcTime);
        return utcTime;
    }

}
