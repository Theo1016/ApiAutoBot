package com.alpha.apiautobot;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.alpha.apiautobot.domain.request.huobipro.PlaceOrders;
import com.alpha.apiautobot.domain.request.okex.OrderHistoryRequest;
import com.alpha.apiautobot.domain.response.huobipro.AccountBalance;
import com.alpha.apiautobot.domain.response.huobipro.HRAccounts;
import com.alpha.apiautobot.domain.response.huobipro.HRCoins;
import com.alpha.apiautobot.domain.response.huobipro.HRSymbols;
import com.alpha.apiautobot.domain.response.huobipro.MarketDepth;
import com.alpha.apiautobot.domain.response.huobipro.MarketDetail;
import com.alpha.apiautobot.domain.response.huobipro.PlaceOrdersResponse;
import com.alpha.apiautobot.domain.response.okex.OrderHistoryResponse;
import com.alpha.apiautobot.domain.response.okex.Ticker;
import com.alpha.apiautobot.domain.response.okex.UserInfo;
import com.alpha.apiautobot.platform.huobipro.CoinIncreaseAdapter;
import com.alpha.apiautobot.platform.huobipro.CoinIncreaseView;
import com.alpha.apiautobot.platform.huobipro.HuobiPresenter;
import com.alpha.apiautobot.platform.huobipro.HuobiPro;
import com.alpha.apiautobot.platform.okex.OKExClient;
import com.alpha.apiautobot.utils.ApiSignature;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity implements CoinIncreaseView{

    private RecyclerView recyclerView;
    private HuobiPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        HuobiPro huobiPro = new HuobiPro();
//        huobiPro.connection();
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
//        huobiPro.getAccount(new Callback<HRAccounts>() {
//            @Override
//            public void onResponse(Call<HRAccounts> call, retrofit2.Response<HRAccounts> response) {
//                //获取币币交易账户
//                HRAccounts.DataBean spot = null;
//                for(HRAccounts.DataBean  dataBean : response.body().data) {
//                    if(dataBean.type.equals("spot")) {
//                        spot = dataBean;
//                    }
//                }
//                if(spot != null) {
//                    //下单
//                    placeOrders(spot);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<HRAccounts> call, Throwable t) {
//                Log.e("TEST", t.getMessage().toString());
//            }
//        });
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

//        OKExClient okExClient = new OKExClient();
//        okExClient.apiService.getUserInfo().enqueue(new Callback<UserInfo>() {
//            @Override
//            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
//                if(response.isSuccessful()) {
//                    Log.e("TEST", "get User info:" + new Gson().toJson(response.body().toString()));
//                }else {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserInfo> call, Throwable t) {
//                Log.e("TEST", t.getMessage());
//            }
//        });
//
//        okExClient.apiService.getTicker("btc_usdt")
//                .enqueue(new Callback<String >() {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        Log.e("TEST:getTicker", response.body());
//                    }
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//
//                    }
//                });
//        OrderHistoryRequest requestBody = new OrderHistoryRequest("btc_usdt",
//                1, 1, 10);
//        okExClient.apiService.getOrderHistory(requestBody)
//                .enqueue(new Callback<OrderHistoryResponse>() {
//                    @Override
//                    public void onResponse(Call<OrderHistoryResponse> call, Response<OrderHistoryResponse> response) {
//                        Log.e("TEST", "history order:" + new Gson().toJson(response.body()));
//                    }
//
//                    @Override
//                    public void onFailure(Call<OrderHistoryResponse> call, Throwable t) {
//
//                    }
//                });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPresenter = new HuobiPresenter(this);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mPresenter.requestCoinDetails();
            }
        }, 1000, 5000);
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

    private CoinIncreaseAdapter mAdapter;

    @Override
    public void refreshView(final List<List<MarketDetail>> coinDetails) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mAdapter == null) {
                    mAdapter = new CoinIncreaseAdapter(coinDetails);
                    recyclerView.setAdapter(mAdapter);
                }else {
                    mAdapter.update(coinDetails);
                }
            }
        });
    }

    @Override
    public void refreshDepth(List<Map<Long, CopyOnWriteArrayList<MarketDepth>>> depths) {

    }
}
