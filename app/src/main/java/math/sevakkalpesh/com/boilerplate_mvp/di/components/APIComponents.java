package math.sevakkalpesh.com.boilerplate_mvp.di.components;

import android.support.v4.app.Fragment;

import dagger.Component;
import math.sevakkalpesh.com.boilerplate_mvp.CakeList.CakeListImplPresenter;
import math.sevakkalpesh.com.boilerplate_mvp.di.modules.APIModule;
import math.sevakkalpesh.com.boilerplate_mvp.di.scopes.UserScope;
import math.sevakkalpesh.com.boilerplate_mvp.ui.activity.FragmentContainerActivity;


/**
 * Created by kalpesh on 20/01/2016.
 */

    @UserScope
    @Component(dependencies =NetComponent.class, modules = APIModule.class)
    public interface APIComponents {

    void inject(FragmentContainerActivity activity);
    void inject(Fragment fragment);
    void inject(CakeListImplPresenter cakeListImplPresenter);


}
