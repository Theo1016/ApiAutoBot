package com.alpha.apiautobot.platform.huobipro;

import com.alpha.apiautobot.domain.response.huobipro.MarketDetail;

import java.util.List;

public interface CoinIncreaseView {
    public void refreshView(List<List<MarketDetail>> coinDetails);
}
