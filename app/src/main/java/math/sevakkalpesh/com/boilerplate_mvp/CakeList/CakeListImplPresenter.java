package math.sevakkalpesh.com.boilerplate_mvp.CakeList;

import android.support.annotation.NonNull;

import java.util.List;

import math.sevakkalpesh.com.boilerplate_mvp.model.Cake_model;
import math.sevakkalpesh.com.boilerplate_mvp.model.observables.Cake_API;
import math.sevakkalpesh.com.boilerplate_mvp.util.network.SimpleObserver;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kalpesh on 08/06/2016.
 */
public class CakeListImplPresenter implements CakeListContract.IPresenter {

    CakeListContract.IView iView;
    Cake_model cake_model;
    Cake_API cakeApi;

    public CakeListImplPresenter(Cake_API cake_api, CakeListContract.IView mUserView )

    {
        this.iView = mUserView;
        this.cakeApi=cake_api;


    }

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

        cakeApi.getCakes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<List<Cake_model>>(){
                    @Override
                    public void onNext(List<Cake_model> cake_models) {
                        super.onNext(cake_models);
                        iView.showCakesAdapter(cake_models);
                        iView.dismissProgress();

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                iView.dismissProgress();
                    }
                });
    }

    @Override
    public void start() {
        iView.setPresenter(this);
    }
}
