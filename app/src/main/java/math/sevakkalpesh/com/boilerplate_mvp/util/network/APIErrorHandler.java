package math.sevakkalpesh.com.boilerplate_mvp.util.network;

import android.accounts.NetworkErrorException;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

/**
 * Created by kalpesh on 23/12/2015.
 */
public class APIErrorHandler implements ErrorHandler {
    @Override
    public Throwable handleError(RetrofitError cause) {


        if (cause.getKind() == RetrofitError.Kind.NETWORK) {
            if (cause.getCause() instanceof SocketTimeoutException) {
                return new NetworkErrorException();
            } else {
                return new TimeoutException();
            }
        }

        return cause;
    }
}
