package math.sevakkalpesh.com.boilerplate_mvp.CakeList;

import java.util.List;

import math.sevakkalpesh.com.boilerplate_mvp.model.Cake_model;
import math.sevakkalpesh.com.boilerplate_mvp.mvp.BasePresenter;
import math.sevakkalpesh.com.boilerplate_mvp.mvp.BaseView;

/**
 * Created by kalpesh on 08/06/2016.
 */
public interface CakeListContract {

     interface IView extends BaseView<IPresenter>{

         void showProgress();
         void dismissProgress();
         void showSwipeRefresh();
         void dismissSwipeRefresh();
         void msgDownloadComplete();
         void showError();
         void showCakesAdapter(List<Cake_model> cake_models);
         void notifyDataChanged();
    }

     interface IPresenter extends BasePresenter
    {
        void displayList();
        void onStop();
        void onResume();
    }


}

