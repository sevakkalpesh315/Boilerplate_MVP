package math.sevakkalpesh.com.boilerplate_mvp.di.components;

import dagger.Component;
import kalpesh.mac.com.rxdagger_cakes.MainActivity;
import kalpesh.mac.com.rxdagger_cakes.di.modules.APIModule;
import kalpesh.mac.com.rxdagger_cakes.di.scopes.UserScope;


/**
 * Created by kalpesh on 20/01/2016.
 */

    @UserScope
    @Component(dependencies =NetComponent.class, modules = APIModule.class)
    public interface APIComponents {

    void inject(MainActivity activity);

}
