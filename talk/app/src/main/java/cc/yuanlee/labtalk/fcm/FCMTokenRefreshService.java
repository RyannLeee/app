package cc.yuanlee.labtalk.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.hyphenate.chat.EMClient;

public class FCMTokenRefreshService extends FirebaseInstanceIdService {
    private static final String TAG = "FCMTokenRefreshService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.i(TAG, "onTokenRefresh: " + token);
        // Important, send the fcm token to the server
        EMClient.getInstance().sendFCMTokenToServer(token);
    }
}
