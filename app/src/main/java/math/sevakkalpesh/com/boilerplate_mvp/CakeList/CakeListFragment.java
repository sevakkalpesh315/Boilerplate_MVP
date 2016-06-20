package math.sevakkalpesh.com.boilerplate_mvp.CakeList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import math.sevakkalpesh.com.boilerplate_mvp.util.view.Dialogs;
import math.sevakkalpesh.com.boilerplate_mvp.util.view.ToastUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kalpesh on 06/06/2016.
 */
public class CakeListFragment extends BaseFragment implements CakeListContract.IView
{
@Inject
    Cake_API cakeApi;


    @InjectView(R.id.recyclerList)
    RecyclerView mRecyclerView;

    private CakesAdapter mAdapter;


    CakeListContract.IPresenter mPresenter;
    CakeListImplPresenter cakeListImplPresenter;

    public static CakeListFragment newInstance() {
        return new CakeListFragment();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeRecycler(view);
    }

    @Override
    protected int getFragmentLayout() {
        initializeDependencyInjector();
        cakeListImplPresenter=new CakeListImplPresenter(cakeApi,this);

      //  initializeToolbar();
        cakeListImplPresenter.displayList();

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
    }

    public void initializeRecycler(View v){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRecyclerView.addItemDecoration(new RecyclerInsetsDecoration(getContext(), R.dimen.spacing_medium));

    }

    @Override
    public void showProgress() {
       // ToastUtils.showError("Error Downloding Data",getActivity().getApplicationContext());
          Dialogs.showDialog(getActivity(),"Loading data");

    }

    @Override
    public void dismissProgress() {
        Dialogs.dismissDialog();
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
    public void setPresenter(@NotNull CakeListContract.IPresenter presenter) {
        mPresenter = checkNotNull(presenter);

    }
}
