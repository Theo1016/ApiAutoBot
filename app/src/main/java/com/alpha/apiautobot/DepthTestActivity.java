package com.alpha.apiautobot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alpha.apiautobot.domain.response.huobipro.MarketDepth;
import com.alpha.apiautobot.domain.response.huobipro.MarketDetail;
import com.alpha.apiautobot.platform.huobipro.CoinDepthAdapter;
import com.alpha.apiautobot.platform.huobipro.CoinIncreaseView;
import com.alpha.apiautobot.platform.huobipro.HuobiPresenter;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <pre>
 *     author : hedong
 *     e-mail : 739814501@qq.com
 *     time   : 2018/05/31
 *     desc   : xxxx描述
 *     version: 1.0
 * </pre>
 */
public class DepthTestActivity extends FragmentActivity implements CoinIncreaseView{
    private RecyclerView recyclerView;
    private HuobiPresenter mPresenter;
    private CoinDepthAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depth_test);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPresenter = new HuobiPresenter(this);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mPresenter.requestCoinDepth();
            }
        }, 1000, 5000);

    }

    @Override
    public void refreshView(List<List<MarketDetail>> coinDetails) {

    }

    @Override
    public void refreshDepth(final List<Map<Long, CopyOnWriteArrayList<MarketDepth>>> depths) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mAdapter == null) {
                    mAdapter = new CoinDepthAdapter(depths);
                    recyclerView.setAdapter(mAdapter);
                }else {
                    mAdapter.updateData(depths);
                }
            }
        });
    }
}
