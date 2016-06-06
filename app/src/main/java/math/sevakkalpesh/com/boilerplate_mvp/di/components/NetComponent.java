package math.sevakkalpesh.com.boilerplate_mvp.di.components;


import android.content.SharedPreferences;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Component;
import math.sevakkalpesh.com.boilerplate_mvp.di.modules.AppModule;
import math.sevakkalpesh.com.boilerplate_mvp.di.modules.NetModule;
import retrofit.RestAdapter;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    // downstream components need these exposed
    RestAdapter retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
}