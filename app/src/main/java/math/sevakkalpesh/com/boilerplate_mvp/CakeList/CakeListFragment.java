package math.sevakkalpesh.com.boilerplate_mvp.CakeList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import math.sevakkalpesh.com.boilerplate_mvp.MyApp;
import math.sevakkalpesh.com.boilerplate_mvp.R;
import math.sevakkalpesh.com.boilerplate_mvp.model.Cake_model;
import math.sevakkalpesh.com.boilerplate_mvp.model.observables.Cake_API;
import math.sevakkalpesh.com.boilerplate_mvp.ui.fragment.BaseFragment;
import math.sevakkalpesh.com.boilerplate_mvp.util.recyclerview.RecyclerInsetsDecoration;
import math.sevakkalpesh.com.boilerplate_mvp.util.view.ToastUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kalpesh on 06/06/2016.
 */
public class CakeListFragment extends BaseFragment implements CakeListContract.IView, SwipeRefreshLayout.OnRefreshListener
{
@Inject
    Cake_API cakeApi;

    public SwipeRefreshLayout mSwipeRefreshLayout;

    @InjectView(R.id.recyclerList)
    RecyclerView mRecyclerView;


    private CakesAdapter mAdapter;


    CakeListContract.IPresenter mPresenter;
    CakeListImplPresenter cakeListImplPresenter;

    public static CakeListFragment newInstance() {
        return new CakeListFragment();
    }

    @Override
    public void onStop() {
        super.onStop();
        cakeListImplPresenter.onStop();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeRecycler(view);
        initializeSwipeToRefresh(view);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        cakeListImplPresenter=new CakeListImplPresenter(cakeApi,this);
/**
 * Showing Swipe Refresh animation on activity create
 * As animation won't start on onCreate, post runnable is used
 */
        mSwipeRefreshLayout.post(new Runnable() {
                                     @Override
                                     public void run() {
                                         mSwipeRefreshLayout.setRefreshing(true);
                                         cakeListImplPresenter.displayList();
                                     }
                                 }
        );


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected int getFragmentLayout() {
        initializeDependencyInjector();

      //  initializeToolbar();


        return R.layout.recycler_main;
    }

    private void initializeDependencyInjector() {
        ((MyApp) getActivity().getApplication())
                .getApiComponent()
                .inject(CakeListFragment.this);
    }

    @Override
    public void onResume() {
        super.onResume();
        cakeListImplPresenter.start();
        cakeListImplPresenter.onResume();

    }

    public void initializeRecycler(View v){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRecyclerView.addItemDecoration(new RecyclerInsetsDecoration(getContext(), R.dimen.spacing_medium));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);
    }

    public void initializeSwipeToRefresh(View v ){
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.activity_main_swipe_refresh_layout);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,
                        getResources().getDisplayMetrics()));


    }

    @Override
    public void showSwipeRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void dismissSwipeRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);

    }
    @Override
    public void showProgress() {
       // ToastUtils.showError("Error Downloding Data",getActivity().getApplicationContext());
       //   Dialogs.showDialog(getActivity(),"Loading data");

    }

    @Override
    public void dismissProgress() {
       // Dialogs.dismissDialog();
    }

    @Override
    public void msgDownloadComplete() {
        ToastUtils.showShortMessage(" Downloding Data complete",getActivity().getApplicationContext());
    }

    @Override
    public void showError() {
        ToastUtils.showError("Error Downloding Data",getActivity().getApplicationContext());
    }

    @Override
    public void showCakesAdapter(List<Cake_model> cake_models) {
       mAdapter = new CakesAdapter(cake_models, R.layout.card_row, getActivity().getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void notifyDataChanged() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(@NotNull CakeListContract.IPresenter presenter) {
        mPresenter = checkNotNull(presenter);

    }

    @Override
    public void onRefresh() {

        cakeListImplPresenter.displayList();

    }
}
