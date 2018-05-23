package com.alpha.apiautobot.platform.binance.presenter;

import com.alpha.apiautobot.base.Config;
import com.alpha.apiautobot.base.rest.ApiClient;
import com.alpha.apiautobot.base.rest.BinanceInterceptor;
import com.alpha.apiautobot.domain.request.NewOrder;
import com.alpha.apiautobot.domain.response.ExchangeInfo;
import com.alpha.apiautobot.domain.response.ServerTime;
import com.alpha.apiautobot.platform.binance.BinanceApiConstants;
import com.alpha.apiautobot.utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Theo on 2018/5/19.
 */
public class BinancePresenter implements BinanceContract.Presenter {
    BinanceContract.View mPingContract;

    public BinancePresenter(BinanceContract.View p) {
        ApiClient.CreateApiService(BinanceApiConstants.API_BASE_URL,
                ApiClient.genericClient(new BinanceInterceptor(Config.BINANCE_API_KEY, Config.BINANCE_SECRET)));
        this.mPingContract = p;
    }

    @Override
    public void ping() {
        Call<Void> call = ApiClient.service.ping();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mPingContract.pingCallback();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void serverTime() {
        Call<ServerTime> call = ApiClient.service.serverTime();
        call.enqueue(new Callback<ServerTime>() {
            @Override
            public void onResponse(Call<ServerTime> call, Response<ServerTime> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mPingContract.serverTimeCallback();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerTime> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void exchangeInfo() {
        Call<ExchangeInfo> call = ApiClient.service.exchangeInfo();
        call.enqueue(new Callback<ExchangeInfo>() {
            @Override
            public void onResponse(Call<ExchangeInfo> call, Response<ExchangeInfo> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mPingContract.serverTimeCallback();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ExchangeInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void depth(String symobl, int limit) {
        Call<String> call = ApiClient.service.depth(symobl, limit);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mPingContract.serverTimeCallback();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void trades(String symobl, int limit) {
        Call<String> call = ApiClient.service.trades(symobl, limit);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mPingContract.serverTimeCallback();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void historicalTrades(String symobl, int limit, long fromId) {
        Call<String> call = ApiClient.service.historicalTrades(symobl, limit, fromId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mPingContract.serverTimeCallback();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void orderTest(NewOrder order) {
        Call<String> call = ApiClient.service.orderTest(order.getSymbol(), order.getSide(), order.getType(),
                order.getTimeInForce(), order.getQuantity(), order.getPrice(), order.getNewClientOrderId(), order.getStopPrice(),
                order.getIcebergQty(), order.getNewOrderRespType(), order.getRecvWindow(), order.getTimestamp());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mPingContract.serverTimeCallback();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
