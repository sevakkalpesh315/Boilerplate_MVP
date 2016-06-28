package math.sevakkalpesh.com.boilerplate_mvp.di.components;


import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import math.sevakkalpesh.com.boilerplate_mvp.di.modules.AppModule;
import math.sevakkalpesh.com.boilerplate_mvp.di.modules.NetModule;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    // downstream components need these exposed
    Retrofit retrofit();
    OkHttpClient buildClient();
    SharedPreferences sharedPreferences();
}