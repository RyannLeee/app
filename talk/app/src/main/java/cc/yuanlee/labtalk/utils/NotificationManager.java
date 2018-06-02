package cc.yuanlee.labtalk.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.hyphenate.util.EasyUtils;


public class NotificationManager {
    private static final String TAG = "NotificationManager";

    private static NotificationManager instance = null;
    private static int ID_NOTIFICATION = 0525; // start notification id

    private android.app.NotificationManager notificationManager = null;
    private Context context;

    public static NotificationManager getInstance(Context context) {
        synchronized (NotificationManager.class) {
            if (instance == null) {
                synchronized (NotificationManager.class) {
                    instance = new NotificationManager(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public void sendNotification(String message) {
        if (!EasyUtils.isAppRunningForeground(context)) {
            try {
                PackageManager packageManager = context.getPackageManager();
                // notification title
                String contentTitle = (String) packageManager
                        .getApplicationLabel(context.getApplicationInfo());
                String packageName = context.getApplicationInfo().packageName;

                Uri defaultSoundUri = RingtoneManager
                        .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                Intent msgIntent = context.getPackageManager()
                        .getLaunchIntentForPackage(packageName);
                PendingIntent pendingIntent = PendingIntent.getActivity(context,
                        ID_NOTIFICATION, msgIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                // create and send notification
                Notification notification = new NotificationCompat.Builder(context)
                        .setSmallIcon(context.getApplicationInfo().icon)
                        .setSound(defaultSoundUri)
                        .setWhen(System.currentTimeMillis())
                        .setAutoCancel(true)
                        .setContentTitle(contentTitle)
                        .setTicker(message)
                        .setContentText(message)
                        .setContentIntent(pendingIntent)
                        .build();

                notificationManager.notify(ID_NOTIFICATION, notification);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private NotificationManager(Context context) {
        this.context = context;
        notificationManager = (android.app.NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
