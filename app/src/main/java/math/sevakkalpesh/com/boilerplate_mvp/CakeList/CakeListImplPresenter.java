package math.sevakkalpesh.com.boilerplate_mvp.CakeList;

import android.content.Context;
import android.support.annotation.NonNull;

import math.sevakkalpesh.com.boilerplate_mvp.model.Cake_model;

/**
 * Created by kalpesh on 08/06/2016.
 */
public class CakeListImplPresenter implements CakeListContract.IPresenter {

    CakeListContract.IView iView;
    Cake_model cake_model;
    Context context;


    public CakeListImplPresenter(@NonNull CakeListContract.IView mUserView)

    {
        this.iView = mUserView;


    }
    public CakeListImplPresenter(CakeListContract.IView iView,Cake_model cake_model)
    {
        this.iView=iView;
        this.cake_model=cake_model;

    }

    @Override
    public void displayList() {

        iView.showProgress();


    }

    @Override
    public void start() {
        iView.setPresenter(this);
    }
}
