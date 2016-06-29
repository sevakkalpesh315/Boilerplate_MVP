package math.sevakkalpesh.com.boilerplate_mvp.util.network;

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


   public static String getErrorMessage(Throwable e) {
        RetrofitException retrofitError;
        if (e instanceof RetrofitException) {
            retrofitError = ((RetrofitException) e);
            if (retrofitError.getKind() == RetrofitException.Kind.NETWORK) {
                return "Network is down!";
            }
            if (retrofitError.getKind() == RetrofitException.Kind.HTTP) {
                return "A non-200 HTTP status code was received from the server e.g. 502, 503, etc...";
            }

            if (retrofitError.getKind() == RetrofitException.Kind.UNEXPECTED) {
                return "An internal error occurred while attempting to execute a request.";
            }
        }
        return "other";
    }

}
