package com.alpha.apiautobot.platform.binance.presenter;

import com.alpha.apiautobot.base.Config;
import com.alpha.apiautobot.base.rest.binance.BinanceApiClient;
import com.alpha.apiautobot.base.rest.binance.BinanceInterceptor;
import com.alpha.apiautobot.domain.request.NewOrder;
import com.alpha.apiautobot.domain.response.binance.rest.ExchangeInfo;
import com.alpha.apiautobot.domain.response.binance.rest.ServerTime;
import com.alpha.apiautobot.platform.binance.BinanceApiConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Theo on 2018/5/19.
 */
public class BinancePresenter implements BinanceContract.Presenter {
    BinanceContract.View mBinanceContractView;

    public BinancePresenter(BinanceContract.View p) {
        BinanceApiClient.CreateApiService(BinanceApiConstants.API_BASE_URL,
                BinanceApiClient.genericClient(new BinanceInterceptor(Config.BINANCE_API_KEY, Config.BINANCE_SECRET)));
        this.mBinanceContractView = p;
    }

    @Override
    public void ping() {
        Call<Void> call = BinanceApiClient.service.ping();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mBinanceContractView.pingCallback();
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
        Call<ServerTime> call = BinanceApiClient.service.serverTime();
        call.enqueue(new Callback<ServerTime>() {
            @Override
            public void onResponse(Call<ServerTime> call, Response<ServerTime> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mBinanceContractView.serverTimeCallback();
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
        Call<ExchangeInfo> call = BinanceApiClient.service.exchangeInfo();
        call.enqueue(new Callback<ExchangeInfo>() {
            @Override
            public void onResponse(Call<ExchangeInfo> call, Response<ExchangeInfo> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mBinanceContractView.exchangeInfoCallback(response.body());
                        mBinanceContractView.serverTimeCallback();
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
        Call<String> call = BinanceApiClient.service.depth(symobl, limit);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mBinanceContractView.serverTimeCallback();
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
        Call<String> call = BinanceApiClient.service.trades(symobl, limit);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mBinanceContractView.serverTimeCallback();
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
        Call<String> call = BinanceApiClient.service.historicalTrades(symobl, limit, fromId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mBinanceContractView.serverTimeCallback();
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
        Call<String> call = BinanceApiClient.service.orderTest(order.getSymbol(), order.getSide(), order.getType(),
                order.getTimeInForce(), order.getQuantity(), order.getPrice(), order.getNewClientOrderId(), order.getStopPrice(),
                order.getIcebergQty(), order.getNewOrderRespType(), order.getRecvWindow(), order.getTimestamp());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mBinanceContractView.serverTimeCallback();
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
    public void supportNewList() {
        Call<String> call = BinanceApiClient.service.supportNewList();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mBinanceContractView.supportNewListCallback(response.body());
                        mBinanceContractView.serverTimeCallback();
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
