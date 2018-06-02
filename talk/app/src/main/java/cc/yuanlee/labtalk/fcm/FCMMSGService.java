package cc.yuanlee.labtalk.fcm;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import cc.yuanlee.labtalk.utils.NotificationManager;

public class FCMMSGService extends FirebaseMessagingService {
    private static final String TAG = "FCMMSGService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            String message = remoteMessage.getData().get("alert");
            Log.i(TAG, "onMessageReceived: " + message);
            NotificationManager.getInstance(this).sendNotification(message);
        }
    }
}
