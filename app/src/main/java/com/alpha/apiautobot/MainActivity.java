package com.alpha.apiautobot;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import butterknife.BindView;
import com.alpha.apiautobot.bot.BotManager;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.binance_key_et)
    EditText binance_key_et;

    @BindView(R.id.binance_secret_et)
    EditText binance_secret_et;

    @BindView(R.id.bitfinex_key_et)
    EditText bitfinex_key_et;

    @BindView(R.id.bitfinex_secret_et)
    EditText bitfinex_secret_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BotManager botManager = new BotManager();
        botManager.start();
    }
}
