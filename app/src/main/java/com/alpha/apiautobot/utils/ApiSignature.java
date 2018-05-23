package com.alpha.apiautobot.utils;

import android.annotation.SuppressLint;
import android.util.Base64;
import android.util.Log;

import com.alpha.apiautobot.platform.huobipro.HuobiPro;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * API签名方法
 */
public class ApiSignature {
    private static SimpleDateFormat sdf;
    private static final String AccessKey = HuobiPro.ACCESS_KEY;
    private static final String SecretKey = HuobiPro.SECRET_KEY;

    private Map<String, String> params;

    //静态块
    {
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public HttpUrl createSignatureUrl(Request originRequest) {
        HttpUrl httpUrl = originRequest.url();
        String method = originRequest.method();
        String host = httpUrl.host();
        String path = httpUrl.encodedPath();
        //构造参数
        HashMap<String, String> params = new HashMap<>();
        String query = httpUrl.query();
        if(query != null) {
            String[] querys = query.split("&");
            for (String key_value : querys) {
                String[] keys = key_value.split("=");
                String key = keys[0];
                String value = keys[1];
                params.put(key, value);
            }
        }
        //对请求方法、host、path，参数签名
        createSignature(method, host, path, params);
        String url = httpUrl.scheme() + "://" + host + path + "?" + toQueryString(params);
        HttpUrl newUrl = HttpUrl.parse(url);
        return newUrl;
    }

    /**
     * 创建一个有效的签名。该方法为客户端调用，将在传入的params中添加AccessKeyId、Timestamp、SignatureVersion、SignatureMethod、Signature参数。
     *
     * @param method       请求方法，"GET"或"POST"
     * @param host         请求域名，例如"be.huobi.com"
     * @param uri          请求路径，注意不含?以及后的参数，例如"/v1/api/info"
     * @param params       原始请求参数，以Key-Value存储，注意Value不要编码
     */
    public void createSignature(String method, String host,
                                String uri, Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        this.params = params;
        StringBuilder sb = new StringBuilder(1024);
        sb.append(method.toUpperCase()).append('\n') // GET
                .append(host.toLowerCase()).append('\n') // Host
                .append(uri).append('\n'); // /path
        params.remove("Signature");
        params.put("AccessKeyId", AccessKey);
        params.put("SignatureVersion", "2");
        params.put("SignatureMethod", "HmacSHA256");
        params.put("Timestamp", gmtNow());
        // build signature:
        SortedMap<String, String> map = new TreeMap<>(params);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append('=').append(urlEncode(value)).append('&');
        }
        // remove last '&':
        sb.deleteCharAt(sb.length() - 1);
        // sign:
        Mac hmacSha256 = null;
        try {
            hmacSha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secKey =
                    new SecretKeySpec(SecretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmacSha256.init(secKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm: " + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid key: " + e.getMessage());
        }
        String payload = sb.toString();
        byte[] hash = hmacSha256.doFinal(payload.getBytes(StandardCharsets.UTF_8));
        String actualSign = Base64.encodeToString(hash, Base64.NO_WRAP);
        params.put("Signature", actualSign);
    }

    /**
     * 使用标准URL Encode编码。注意和JDK默认的不同，空格被编码为%20而不是+。
     *
     * @param s String字符串
     * @return URL编码后的字符串
     */
    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("UTF-8 encoding not supported!");
        }
    }

    public static String gmtNow() {
        return sdf.format(new Date());
    }

    /**
     * 拼接参数并对值进行URI编码
     * @param params
     * @return
     */
    public static String toQueryString(Map<String, String> params) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.append(entry.getKey()).append("=").append(ApiSignature.urlEncode(entry.getValue()))
                    .append("&");
        }
        builder.replace(builder.length()-1, builder.length(), "");
        return builder.toString();
    }

}
