package com.alpha.apiautobot.bot;

import com.alpha.apiautobot.platform.binance.Binance;
import com.alpha.apiautobot.platform.kucoin.KuCoin;

/**
 * Created by Theo on 2018/5/17.
 */
public class BotManager {

   public void start(){
//       Binance binance= new Binance();
//       binance.initRestful();

       KuCoin kuCoin= new KuCoin();
       kuCoin.initRestful();
   }
}
