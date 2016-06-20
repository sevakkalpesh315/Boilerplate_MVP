package math.sevakkalpesh.com.boilerplate_mvp.CakeList;

import math.sevakkalpesh.com.boilerplate_mvp.model.Cake_model;
import math.sevakkalpesh.com.boilerplate_mvp.mvp.BasePresenter;
import math.sevakkalpesh.com.boilerplate_mvp.mvp.BaseView;

import java.util.List;

/**
 * Created by kalpesh on 08/06/2016.
 */
public interface CakeListContract {

     interface IView extends BaseView<IPresenter>{

         void showProgress();
         void dismissProgress();
         void msgDownloadComplete();
         void showError();
         void showCakesAdapter(List<Cake_model> cake_models);
    }

     interface IPresenter extends BasePresenter
    {
        void displayList();

    }


}

