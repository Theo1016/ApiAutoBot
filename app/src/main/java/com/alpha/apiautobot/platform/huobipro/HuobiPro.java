package com.alpha.apiautobot.platform.huobipro;

import com.alpha.apiautobot.base.rest.huobipro.HuobiApiService;
import com.alpha.apiautobot.base.rest.huobipro.HuobiProInterceptor;
import com.alpha.apiautobot.platform.AbstractPlatform;
import com.alpha.apiautobot.utils.ApiSignature;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 火币REST API
 * https://api.huobi.pro/market  行情API
 * https://api.huobi.pro/v1      交易API
 */
public class HuobiPro extends AbstractPlatform {
    public static final String API_HOST           = "api.huobi.pro";

    public static final String ACCESS_KEY = "f3a7ca68-7f1a9603-905b354c-c1ff2";
    public static final String SECRET_KEY = "xxxxx";

    public HuobiApiService apiService;
    public OkHttpClient httpClient;

    public OkHttpClient genericClient(Interceptor interceptor) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .build();
        this.httpClient = httpClient;
        return httpClient;
    }

    public HuobiPro() {
        initRestful();
    }

    public static String getBaseUrl() {
        return "https://" + API_HOST;
    }

    /**
     * 拼接参数并对值进行URI编码
     * @param params
     * @return
     */
    public String toQueryString(Map<String, String> params) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.append(entry.getKey()).append("=").append(ApiSignature.urlEncode(entry.getValue()))
                    .append("&");
        }
        builder.replace(builder.length()-1, builder.length(), "");
        return builder.toString();
    }

    @Override
    public void initRestful() {
        apiService = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .client(genericClient(new HuobiProInterceptor()))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(HuobiApiService.class);
    }

    @Override
    public void connection() {

    }

    @Override
    public void disConnection() {

    }

    @Override
    public Object getMarketList() {
        return null;
    }

    @Override
    public Object getTick() {
        return null;
    }

    @Override
    public Object getAccountInfo() {
        return null;
    }

    @Override
    public void buyCoin() {

    }

    @Override
    public void sellCoin() {

    }

    @Override
    public void deposite() {

    }

    @Override
    public void withdrawal() {

    }


    /**
     * 获取火币所有账户信息
     */
    public void getAccount(Callback<String> callback) {
        Map<String, String> datas = new HashMap<>();
        String uri = "/v1/account/accounts";
        //参数加密
        ApiSignature apiSignature = new ApiSignature();
        apiSignature.createSignature(ACCESS_KEY, SECRET_KEY,
                "GET",
                API_HOST,
                uri, datas);
        //url组装
        String url = HuobiPro.getBaseUrl() + uri + "?" + toQueryString(datas);
        Call<String> call = apiService.getAccount(url);
        call.enqueue(callback);
    }

    /**
     * 获取指定账户的余额
     * @param accountId
     */
    public void getBalance(int accountId, Callback<String> callback) {
        HashMap<String, String> datas = new HashMap<>();
        String uri = "/v1/account/accounts/" + accountId + "/balance";
        ApiSignature apiSignature = new ApiSignature();
        apiSignature.createSignature(ACCESS_KEY, SECRET_KEY,
                "GET",
                API_HOST,
                uri, datas);
        String url = HuobiPro.getBaseUrl() + uri + "?" + toQueryString(datas);
        Call<String> call1 = apiService.getBalanceByAccountId(url);
        call1.enqueue(callback);
    }
}
