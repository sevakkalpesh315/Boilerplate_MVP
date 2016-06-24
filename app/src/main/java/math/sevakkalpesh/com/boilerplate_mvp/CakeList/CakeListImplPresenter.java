package math.sevakkalpesh.com.boilerplate_mvp.CakeList;

import android.support.annotation.NonNull;

import java.util.List;

import math.sevakkalpesh.com.boilerplate_mvp.model.Cake_model;
import math.sevakkalpesh.com.boilerplate_mvp.model.observables.Cake_API;
import math.sevakkalpesh.com.boilerplate_mvp.util.network.RxUtils;
import math.sevakkalpesh.com.boilerplate_mvp.util.network.SimpleObserver;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kalpesh on 08/06/2016.
 */
public class CakeListImplPresenter implements CakeListContract.IPresenter {

    CakeListContract.IView iView;
    Cake_model cake_model;
    Cake_API cakeApi;
    /**
     * Subscription that represents a group of Subscriptions that are unsubscribed together.
     */
    private CompositeSubscription _subscriptions = new CompositeSubscription();

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
        iView.showSwipeRefresh();
        _subscriptions.add(cakeApi.getCakes()
                .take(5)
                .distinct()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<List<Cake_model>>(){
                    @Override
                    public void onNext(List<Cake_model> cake_models) {
                        super.onNext(cake_models);
                        iView.showCakesAdapter(cake_models);
                        iView.notifyDataChanged();
                        iView.dismissProgress();
                        iView.dismissSwipeRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                iView.dismissProgress();
                        iView.dismissSwipeRefresh();
                    }
                }));
    }

    @Override
    public void onStop() {
        RxUtils.unsubscribeIfNotNull(_subscriptions);
    }

    @Override
    public void onResume() {
        _subscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(_subscriptions);

    }

    @Override
    public void start() {
        iView.setPresenter(this);
    }
}
