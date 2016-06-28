package math.sevakkalpesh.com.boilerplate_mvp.di.modules;


import dagger.Module;
import dagger.Provides;
import math.sevakkalpesh.com.boilerplate_mvp.di.scopes.UserScope;
import math.sevakkalpesh.com.boilerplate_mvp.model.observables.Cake_API;
import retrofit2.Retrofit;

@Module
public class APIModule {

    @Provides
    @UserScope
    public Cake_API providesIContactsInterface(Retrofit retrofit) {
        return retrofit.create(Cake_API.class);
    }
}
