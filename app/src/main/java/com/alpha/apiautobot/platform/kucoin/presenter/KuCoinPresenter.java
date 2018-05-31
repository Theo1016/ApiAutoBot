package com.alpha.apiautobot.platform.kucoin.presenter;

import com.alpha.apiautobot.base.Config;
import com.alpha.apiautobot.base.rest.kucoin.KuCoinApiClient;
import com.alpha.apiautobot.base.rest.kucoin.KuCoinInterceptor;
import com.alpha.apiautobot.domain.response.kucoin.MarketModel;
import com.alpha.apiautobot.domain.response.kucoin.TransactionOrderModel;
import com.alpha.apiautobot.platform.kucoin.KuCoinApiConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Theo on 2018/5/19.
 */
public class KuCoinPresenter implements KuCoinContract.Presenter {
    KuCoinContract.View mKuCoinContractView;

    public KuCoinPresenter(KuCoinContract.View p) {
        this.mKuCoinContractView = p;
    }

    @Override
    public void changeCurrency(String currency) {
        String payload="currency="+currency;
        KuCoinApiClient.CreateApiService(KuCoinApiConstants.API_BASE_URL,
                KuCoinApiClient.genericClient(new KuCoinInterceptor(Config.KUCOIN_API_KEY, Config.KUCOIN_SECRET,"/v1/user/change-currency",payload)));
        Call<String> call = KuCoinApiClient.service.changeCurrency(currency);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mKuCoinContractView.callback();
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
    public void getTick(String symbol) {
        String payload="symbol="+symbol;
        KuCoinApiClient.CreateApiService(KuCoinApiConstants.API_BASE_URL,
                KuCoinApiClient.genericClient(new KuCoinInterceptor(Config.KUCOIN_API_KEY, Config.KUCOIN_SECRET,"/v1/open/tick",payload)));
        Call<String> call = KuCoinApiClient.service.getTick(symbol);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mKuCoinContractView.callback();
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
    public void listActiveOrders(String symbol, String type) {
        String payload="symbol="+symbol+"&type="+type;
        KuCoinApiClient.CreateApiService(KuCoinApiConstants.API_BASE_URL,
                KuCoinApiClient.genericClient(new KuCoinInterceptor(Config.KUCOIN_API_KEY, Config.KUCOIN_SECRET,"/v1/order/active",payload)));
        Call<String> call = KuCoinApiClient.service.listActiveOrders(symbol,type);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mKuCoinContractView.callback();
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
    public void listTradingMarkets() {
        String payload="";
        KuCoinApiClient.CreateApiService(KuCoinApiConstants.API_BASE_URL,
                KuCoinApiClient.genericClient(new KuCoinInterceptor(Config.KUCOIN_API_KEY, Config.KUCOIN_SECRET,"/v1/open/markets",payload)));
        Call<String> call = KuCoinApiClient.service.listTradingMarkets();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mKuCoinContractView.callback();
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
    public void listTradingSymbolsMarkets(String market) {
        String payload="market="+market;
        KuCoinApiClient.CreateApiService(KuCoinApiConstants.API_BASE_URL,
                KuCoinApiClient.genericClient(new KuCoinInterceptor(Config.KUCOIN_API_KEY, Config.KUCOIN_SECRET,"/v1/market/open/symbols",payload)));
        Call<MarketModel> call = KuCoinApiClient.service.listTradingSymbolsMarkets(market);
        call.enqueue(new Callback<MarketModel>() {
            @Override
            public void onResponse(Call<MarketModel> call, Response<MarketModel> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        mKuCoinContractView.callback(response.body());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MarketModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void orderBook(String symbol, String group, String limit) {
        String payload="symbol="+symbol+"&group="+group+"&limit="+limit;
        KuCoinApiClient.CreateApiService(KuCoinApiConstants.API_BASE_URL,
                KuCoinApiClient.genericClient(new KuCoinInterceptor(Config.KUCOIN_API_KEY, Config.KUCOIN_SECRET,"/v1/open/orders",payload)));
        Call<TransactionOrderModel> call = KuCoinApiClient.service.requestOrders(symbol,group,String.valueOf(limit));
        call.enqueue(new Callback<TransactionOrderModel>() {
            @Override
            public void onResponse(Call<TransactionOrderModel> call, Response<TransactionOrderModel> response) {
                okhttp3.Response res = response.raw();
                if (res.isSuccessful()) {
                    try {
                        if(response.body().getSuccess()){
                            mKuCoinContractView.callback(call.request().url().query()+"",response.body());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                }
            }

            @Override
            public void onFailure(Call<TransactionOrderModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
