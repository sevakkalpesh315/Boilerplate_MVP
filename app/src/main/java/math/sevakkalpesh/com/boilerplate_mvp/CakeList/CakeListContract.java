package math.sevakkalpesh.com.boilerplate_mvp.CakeList;

import math.sevakkalpesh.com.boilerplate_mvp.mvp.BasePresenter;
import math.sevakkalpesh.com.boilerplate_mvp.mvp.BaseView;

/**
 * Created by kalpesh on 08/06/2016.
 */
public interface CakeListContract {

     interface IView extends BaseView<IPresenter>{

         void showProgress();
         void dismissProgress();
         void msgDownloadComplete();
         void showError();
         void showCakesAdapter();
    }

     interface IPresenter extends BasePresenter
    {
        void displayList();

    }


}

