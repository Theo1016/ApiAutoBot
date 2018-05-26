package com.alpha.apiautobot;

import android.app.Application;
import android.content.Context;
import com.alpha.apiautobot.domain.dao.kucoin.DaoMaster;
import com.alpha.apiautobot.domain.dao.kucoin.DaoSession;
import org.greenrobot.greendao.database.Database;

/**
 * Created by Theo on 2018/5/26.
 */
public class ApiAutoBotApplication extends Application {
    private static Context context;
    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        //获取Context
        super.onCreate();
        context = getApplicationContext();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "ApiAutoBot-DB");
        Database db = helper.getWritableDb();
        if (daoSession == null) {
            daoSession = new DaoMaster(db).newSession();
        }
    }

    //返回
    public static Context getContextObject(){
        return context;
    }
}
