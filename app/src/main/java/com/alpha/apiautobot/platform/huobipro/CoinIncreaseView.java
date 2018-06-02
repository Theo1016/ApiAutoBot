package com.alpha.apiautobot.platform.huobipro;

import com.alpha.apiautobot.domain.response.huobipro.MarketDepth;
import com.alpha.apiautobot.domain.response.huobipro.MarketDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public interface CoinIncreaseView {
    public void refreshView(List<List<MarketDetail>> coinDetails);
    public void refreshDepth(List<Map<Long,CopyOnWriteArrayList<MarketDepth>>> depths);
}
