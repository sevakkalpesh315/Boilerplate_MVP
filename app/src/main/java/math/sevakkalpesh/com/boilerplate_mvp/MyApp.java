package math.sevakkalpesh.com.boilerplate_mvp;

import android.app.Application;
import android.content.Context;

import math.sevakkalpesh.com.boilerplate_mvp.di.components.APIComponents;
import math.sevakkalpesh.com.boilerplate_mvp.di.components.DaggerAPIComponents;
import math.sevakkalpesh.com.boilerplate_mvp.di.components.DaggerNetComponent;
import math.sevakkalpesh.com.boilerplate_mvp.di.components.NetComponent;
import math.sevakkalpesh.com.boilerplate_mvp.di.modules.APIModule;
import math.sevakkalpesh.com.boilerplate_mvp.di.modules.AppModule;
import math.sevakkalpesh.com.boilerplate_mvp.di.modules.NetModule;
import math.sevakkalpesh.com.boilerplate_mvp.util.constants.Constants;
import math.sevakkalpesh.com.boilerplate_mvp.util.network.StrictModeUtils;

/**
 * Created by kalpesh on 14/06/2016.
 */
public class MyApp extends Application {
    private static Context context;
    private NetComponent mNetComponent;
    private APIComponents mApiComponents;
    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG)
            StrictModeUtils.enableStrictModeForDevRelease();

        MyApp.context = getApplicationContext();
        mNetComponent= DaggerNetComponent.builder()
                .netModule(new NetModule(Constants.BASE_URL))
                .appModule(new AppModule(this))
                .build();

        mApiComponents= DaggerAPIComponents.builder()
                .netComponent(mNetComponent)
                .aPIModule(new APIModule())
                .build();



    }

    public static Context getAppContext() {
        return MyApp.context;
    }
    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public APIComponents getApiComponent() {
        return mApiComponents;
    }

}


