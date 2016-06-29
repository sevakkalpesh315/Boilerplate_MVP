package math.sevakkalpesh.com.boilerplate_mvp.CakeList;

import android.support.annotation.NonNull;
import android.util.Log;

import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork;

import java.util.List;

import math.sevakkalpesh.com.boilerplate_mvp.MyApp;
import math.sevakkalpesh.com.boilerplate_mvp.model.Cake_model;
import math.sevakkalpesh.com.boilerplate_mvp.model.observables.Cake_API;
import math.sevakkalpesh.com.boilerplate_mvp.util.network.NetworkChecker;
import math.sevakkalpesh.com.boilerplate_mvp.util.network.RxUtils;
import math.sevakkalpesh.com.boilerplate_mvp.util.network.SimpleObserver;
import math.sevakkalpesh.com.boilerplate_mvp.util.view.ToastUtils;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kalpesh on 08/06/2016.
 */
public class CakeListImplPresenter implements CakeListContract.IPresenter {

    CakeListContract.IView iView;
    Cake_model cake_model;
    Cake_API cakeApi;
    private ReactiveNetwork reactiveNetwork;
    private Subscription networkConnectivitySubscription;
    private Subscription internetConnectivitySubscription;

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

//https://github.com/pwittchen/ReactiveNetwork
        //Check Network Connectivity
        //1.1 Start
        internetConnectivitySubscription = reactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean isConnectedToInternet) {
                        if (isConnectedToInternet) {

                            //1.2 Call the Data Service
                            _subscriptions.add(cakeApi.getCakes()

                                    .subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new SimpleObserver<List<Cake_model>>() {
                                        @Override
                                        public void onNext(List<Cake_model> cake_models) {
                                            super.onNext(cake_models);
                                            iView.showCakesAdapter(cake_models);
                                            iView.notifyDataChanged();
                                            iView.dismissProgress();
                                            iView.dismissSwipeRefresh();
                                            //Put data into local data
                                        }


                                        @Override
                                        public void onError(Throwable throwable) {
                                            super.onError(throwable);
                                            iView.dismissProgress();
                                            iView.dismissSwipeRefresh();


                                            ToastUtils.showError(NetworkChecker.getErrorMessage(throwable), MyApp.getAppContext());

                                            //http://bytes.babbel.com/en/articles/2016-03-16-retrofit2-rxjava-error-handling.html
/*

                        if(throwable instanceof IOException) {
                           //A network or conversion error happened
                            ToastUtils.showError(" IOException", MyApp.getAppContext());

                        }
                        if(error.getKind()== RetrofitException.Kind.NETWORK) {
                            //A network or conversion error happened

                            ToastUtils.showError("Network is down!", MyApp.getAppContext());

                        }
                        if(error.getKind()== RetrofitException.Kind.UNEXPECTED) {
                            //A network or conversion error happened
                            ToastUtils.showError("An internal error occurred while attempting to execute a request.", MyApp.getAppContext());

                        }

*/
                                        }

                                    }));
                        }
                        else{
                            //1.3 Load data from local DB

                            //Load data from local db
                            ToastUtils.showError("Get Data from Local DB", MyApp.getAppContext());

                        }
                    }
                }
                );
    }




    @Override
    public void onStop() {
        RxUtils.unsubscribeIfNotNull(_subscriptions);
        safelyUnsubscribe(networkConnectivitySubscription, internetConnectivitySubscription);
    }

    @Override
    public void onResume() {
        _subscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(_subscriptions);
        reactiveNetwork = new ReactiveNetwork();

        networkConnectivitySubscription =
                reactiveNetwork.observeNetworkConnectivity(MyApp.getAppContext())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<ConnectivityStatus>() {
                            @Override public void call(final ConnectivityStatus status) {
                                Log.d("", status.toString());
                              //  tvConnectivityStatus.setText(status.description);
                            }
                        });


    }

    @Override
    public void start() {
        iView.setPresenter(this);
    }

    private void safelyUnsubscribe(Subscription... subscriptions) {
        for (Subscription subscription : subscriptions) {
            if (subscription != null && !subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }
}
