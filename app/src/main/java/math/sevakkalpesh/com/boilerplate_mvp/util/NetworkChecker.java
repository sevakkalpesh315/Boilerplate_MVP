package math.sevakkalpesh.com.boilerplate_mvp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkChecker {
    private static final String TAG = "NetworkCheckerTAG_";
//To call the class use this:(NetworkChecker.isConnected(context[0]))
    public static boolean isConnected(Context context) {
        // TODO: 5/20/16 Check if on WIFI or MOBILE
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null;
    }
}
