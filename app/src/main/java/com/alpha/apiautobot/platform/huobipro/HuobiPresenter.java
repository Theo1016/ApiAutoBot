package com.alpha.apiautobot.platform.huobipro;

import com.alpha.apiautobot.domain.response.huobipro.MarketDetail;

import java.util.ArrayList;
import java.util.List;

public class HuobiPresenter {
    private HuobiPro huobiPro;
    private CoinIncreaseView mView;

    public HuobiPresenter(CoinIncreaseView view) {
        this.mView = view;
        huobiPro = new HuobiPro();
        huobiPro.connection();
    }

    public void requestCoinDetails() {
        if(mView != null) {
            List<List<MarketDetail>> list = new ArrayList<>();
            list.addAll(huobiPro.getCoinDetails());
            mView.refreshView(list);
        }
    }
}
