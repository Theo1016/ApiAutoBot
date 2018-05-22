package com.alpha.apiautobot.platform.binance.presenter;

import com.alpha.apiautobot.base.rest.ApiClient;
import com.alpha.apiautobot.base.rest.BinanceInterceptor;
import com.alpha.apiautobot.utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Theo on 2018/5/19.
 */
public class BinancePresenter implements BinanceContract.Presenter{
    BinanceContract.View mPingContract;

    public BinancePresenter(String url, BinanceContract.View p) {
        ApiClient.CreateApiService(url,ApiClient.genericClient(new BinanceInterceptor()));
        this.mPingContract=p;
    }

    @Override
    public void ping() {
        Call<String> call = ApiClient.service.ping();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
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
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void serverTime() {
        Call<String> call = ApiClient.service.serverTime();
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
    public void exchangeInfo() {
        Call<String> call = ApiClient.service.exchangeInfo();
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
    public void depth(String symobl, int limit) {
        Call<String> call = ApiClient.service.depth(symobl,limit);
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
        Call<String> call = ApiClient.service.trades(symobl,limit);
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
        Call<String> call = ApiClient.service.historicalTrades(symobl,limit,fromId);
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
    public void orderTest(String symobl, Enum side, Enum type, Enum timeInForce,
                          double quantity, double price, String newClientOrderId, double stopPrice,
                          double icebergQty, Enum newOrderRespType, long recvWindow, long timestamp) {
        String totalParams="symobl="+symobl+"&side="+side+"&type="+type+"&timeInForce="+timeInForce
                +"&quantity="+quantity+"&price="+price+"&newClientOrderId="+newClientOrderId+"&stopPrice="+stopPrice
                +"&icebergQty="+icebergQty+"&newOrderRespType="+newOrderRespType+"&recvWindow="+recvWindow+"&timestamp="+timestamp;
        String signature=Util.HMAC(totalParams,"nnDGgPXfxOx3UHbJLdMoRw5imIQ3Fr3Ye5XOBNyuF6uFvyWitFKHqNjxlQsKT8qH");
        Call<String> call = ApiClient.service.orderTest(symobl,side,type,timeInForce,quantity,price,
                newClientOrderId,stopPrice,icebergQty, newOrderRespType,recvWindow,timestamp,signature);
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
