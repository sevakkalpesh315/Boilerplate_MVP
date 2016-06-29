package math.sevakkalpesh.com.boilerplate_mvp.di.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import math.sevakkalpesh.com.boilerplate_mvp.MyApp;
import math.sevakkalpesh.com.boilerplate_mvp.util.network.NetworkChecker;
import math.sevakkalpesh.com.boilerplate_mvp.util.network.RxErrorHandlingCallAdapterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class NetModule {

    String mBaseUrl;
    private OkHttpClient okHttpClient;

    public NetModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

 /*   @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }


    @Provides  // Dagger will only look for methods annotated with @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCache(cache);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.interceptors().add(interceptor);
        return okHttpClient;
    }

    @Provides
    @Singleton
    RestAdapter provideRetrofit(OkHttpClient okhttp) {


      /*  RestAdapter retrofit = new RestAdapter.Builder()
                //.addConverterFactory(GsonConverterFactory.create(gson))
                .setEndpoint(mBaseUrl)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(okhttp))
                .build();
        return retrofit;

    }
    */
//

//https://krtkush.github.io/2016/06/01/caching-using-okhttp-part-1.html
    /**
     * Interceptor to cache data and maintain it for a minute.
     *
     * If the same network request is sent within a minute,
     * the response is retrieved from cache.
     */
    private static class ResponseCacheInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + 60)
                    .build();
        }
    }

    /**
     * Interceptor to cache data and maintain it for four weeks.
     *
     * If the device is offline, stale (at most four weeks old)
     * response is fetched from the cache.
     */
    private static class OfflineResponseCacheInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkChecker.isConnected(MyApp
                    .getAppContext())) {
                request = request.newBuilder()
                        .header("Cache-Control",
                                "public, only-if-cached, max-stale=" + 2419200)
                        .build();
            }
            return chain.proceed(request);
        }
    }

    public class CachingControlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            // Add Cache Control only for GET methods
            if (request.method().equals("GET")) {
                if (NetworkChecker.isConnected(MyApp
                        .getAppContext())) {
                    // 1 day
                    request = request.newBuilder()
                            .header("Cache-Control", "only-if-cached")
                            .build();
                } else {
                    // 4 weeks stale
                    request = request.newBuilder()
                            .header("Cache-Control", "public, max-stale=2419200")
                            .build();
                }
            }

            Response originalResponse = chain.proceed(request);
            return originalResponse;
        }
    }
    /**
     * Method to build and return an OkHttpClient so we can set/get
     * headers quickly and efficiently.
     * @return
     */


    @Provides
    @Singleton
    public OkHttpClient buildClient(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(new ResponseCacheInterceptor());


        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                // Do anything with response here
                //if we ant to grab a specific cookie or something..
                return response;
            }
        });

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //this is where we will add whatever we want to our request headers.
                Request request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
                return chain.proceed(request);
            }
        });

        return  builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        okHttpClient = buildClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
            //    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .build();
        return retrofit;


    }

}
