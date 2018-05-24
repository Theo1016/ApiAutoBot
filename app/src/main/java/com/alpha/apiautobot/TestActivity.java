package com.alpha.apiautobot;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.alpha.apiautobot.domain.request.huobipro.PlaceOrders;
import com.alpha.apiautobot.domain.response.huobipro.AccountBalance;
import com.alpha.apiautobot.domain.response.huobipro.HRAccounts;
import com.alpha.apiautobot.domain.response.huobipro.HRCoins;
import com.alpha.apiautobot.domain.response.huobipro.HRSymbols;
import com.alpha.apiautobot.domain.response.huobipro.PlaceOrdersResponse;
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
//        huobiPro.getSymbols(new Callback<HRSymbols>() {
//            @Override
//            public void onResponse(Call<HRSymbols> call, Response<HRSymbols> response) {
//                for (HRSymbols.Data data : response.body().data) {
//                    Log.e("TEST", "\n" + data.baseCurrency + "/" + data.quoteCurrency
//                    + "\n" + "precesion:" + data.pricePrecision + ",amount precesion:" + data.amountPrecision
//                    + "\n" + "partition:" + data.symbolPartition + "\n");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<HRSymbols> call, Throwable t) {
//
//            }
//        });

        //获取账户信息
        huobiPro.getAccount(new Callback<HRAccounts>() {
            @Override
            public void onResponse(Call<HRAccounts> call, retrofit2.Response<HRAccounts> response) {
                //获取币币交易账户
                HRAccounts.DataBean spot = null;
                for(HRAccounts.DataBean  dataBean : response.body().data) {
                    if(dataBean.type.equals("spot")) {
                        spot = dataBean;
                    }
                }
                if(spot != null) {
                    //下单
                    placeOrders(spot);
                }
            }

            @Override
            public void onFailure(Call<HRAccounts> call, Throwable t) {
                Log.e("TEST", t.getMessage().toString());
            }
        });
        //获取指定账户余额
//        huobiPro.getBalance(3593716, new Callback<AccountBalance>() {
//            @Override
//            public void onResponse(Call<AccountBalance> call, retrofit2.Response<AccountBalance> response) {
//                Log.e("TEST", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<AccountBalance> call, Throwable t) {
//
//            }
//        });

        //获取币种
//        huobiPro.getCoins(new Callback<HRCoins>() {
//            @Override
//            public void onResponse(Call<HRCoins> call, Response<HRCoins> response) {
//                Log.e("TEST", new Gson().toJson(response.body()));
//            }
//
//            @Override
//            public void onFailure(Call<HRCoins> call, Throwable t) {
//
//            }
//        });
    }

    private void placeOrders(HRAccounts.DataBean dataBean) {
        PlaceOrders placeOrders = new PlaceOrders();
        placeOrders.accountId = dataBean.accountId + "";
        placeOrders.amount = "38.6";
        placeOrders.price = "0.00080000";
        placeOrders.source = "api";
        placeOrders.symbol = "htbtc";
        placeOrders.type = PlaceOrders.OrderType.SELL_LIMIT;
        HuobiPro huobiPro = new HuobiPro();
        huobiPro.postOrdersPlace(placeOrders, new Callback<PlaceOrdersResponse>() {
            @Override
            public void onResponse(Call<PlaceOrdersResponse> call, Response<PlaceOrdersResponse> response) {
                Log.e("TEST", new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PlaceOrdersResponse> call, Throwable t) {

            }
        });
    }
}
