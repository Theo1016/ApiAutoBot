package com.alpha.apiautobot.utils;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.alpha.apiautobot.ApiAutoBotApplication;

import java.lang.ref.WeakReference;

public class NotificationUtil {
    private static NotificationUtil sInstance;

    private final WeakReference<Context> mContext;

    public static NotificationUtil getInstance(Context context) {
        if (sInstance == null) {
            synchronized (NotificationUtil.class) {
                if(sInstance == null) {
                    sInstance = new NotificationUtil(context);
                }
            }
        }

        return sInstance;
    }

    private NotificationUtil(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    public NotificationCompat.Builder createNotificationChannel(String name, String text, String CHANNEL_ID) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) mContext.get().getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(text);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            manager.createNotificationChannel(channel);
        }
        return new NotificationCompat.Builder(mContext.get(), CHANNEL_ID)
                .setContentTitle(name)
                .setContentText(text)
                .setWhen(System.currentTimeMillis())
                .setPriority(NotificationCompat.PRIORITY_MAX)
//                .setSmallIcon(R.drawable.kucoin_icon)
                .setVibrate(new long[]{0, 1000, 1000, 1000})
//                .setLargeIcon(BitmapFactory.decodeResource(KucoinApp.getAppContext().getResources(), R.drawable.kucoin_icon))
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
    }

    public static void notification(String title, String text) {
        Notification notification = NotificationUtil.getInstance(ApiAutoBotApplication.getContextObject())
                .createNotificationChannel(title, text,"api_auto_bot")
                .build();
        NotificationManager manager = (NotificationManager)ApiAutoBotApplication.getContextObject()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(text.hashCode(), notification);
    }
}
