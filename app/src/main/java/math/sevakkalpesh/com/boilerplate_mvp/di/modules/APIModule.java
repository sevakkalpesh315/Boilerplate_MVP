package math.sevakkalpesh.com.boilerplate_mvp.di.modules;


import dagger.Module;
import dagger.Provides;
import math.sevakkalpesh.com.boilerplate_mvp.di.scopes.UserScope;
import math.sevakkalpesh.com.boilerplate_mvp.model.observables.Cake_API;
import retrofit.RestAdapter;

@Module
public class APIModule {

    @Provides
    @UserScope
    public Cake_API providesIContactsInterface(RestAdapter retrofit) {
        return retrofit.create(Cake_API.class);
    }
}
