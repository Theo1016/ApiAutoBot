package com.alpha.apiautobot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.alpha.apiautobot.domain.response.huobipro.HRAccounts;
import com.alpha.apiautobot.domain.response.huobipro.HRCoins;
import com.alpha.apiautobot.domain.response.huobipro.HRSymbols;
import com.alpha.apiautobot.platform.huobipro.HuobiPro;
import com.alpha.apiautobot.utils.ApiSignature;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {
    @BindView(R.id.content)
    TextView content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        HuobiPro huobiPro = new HuobiPro();
        huobiPro.getSymbols(new Callback<HRSymbols>() {
            @Override
            public void onResponse(Call<HRSymbols> call, Response<HRSymbols> response) {
                for (HRSymbols.Data data : response.body().data) {
                    Log.e("TEST", "\n" + data.baseCurrency + "/" + data.quoteCurrency
                    + "\n" + "precesion:" + data.pricePrecision + ",amount precesion:" + data.amountPrecision
                    + "\n" + "partition:" + data.symbolPartition + "\n");
                }
            }

            @Override
            public void onFailure(Call<HRSymbols> call, Throwable t) {

            }
        });

        //获取账户信息
        huobiPro.getAccount(new Callback<HRAccounts>() {
            @Override
            public void onResponse(Call<HRAccounts> call, retrofit2.Response<HRAccounts> response) {
                Log.e("TEST", response.body().objectToString(response.body()));
            }

            @Override
            public void onFailure(Call<HRAccounts> call, Throwable t) {
                Log.e("TEST", t.getMessage().toString());
            }
        });
        //获取指定账户余额
//        huobiPro.getBalance(3593716, new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
//                Log.e("TEST", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });

        //获取币种
        huobiPro.getCoins(new Callback<HRCoins>() {
            @Override
            public void onResponse(Call<HRCoins> call, Response<HRCoins> response) {
                Log.e("TEST", new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<HRCoins> call, Throwable t) {

            }
        });
    }
}
