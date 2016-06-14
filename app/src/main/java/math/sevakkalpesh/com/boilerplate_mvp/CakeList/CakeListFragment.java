package math.sevakkalpesh.com.boilerplate_mvp.CakeList;

import org.jetbrains.annotations.NotNull;

import math.sevakkalpesh.com.boilerplate_mvp.R;
import math.sevakkalpesh.com.boilerplate_mvp.ui.fragment.BaseFragment;
import math.sevakkalpesh.com.boilerplate_mvp.util.view.Dialogs;
import math.sevakkalpesh.com.boilerplate_mvp.util.view.ToastUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kalpesh on 06/06/2016.
 */
public class CakeListFragment extends BaseFragment implements CakeListContract.IView
{

    CakeListContract.IPresenter mPresenter;
    CakeListImplPresenter cakeListImplPresenter;

    public static CakeListFragment newInstance() {
        return new CakeListFragment();
    }


    @Override
    protected int getFragmentLayout() {
        cakeListImplPresenter=new CakeListImplPresenter(this);

        //   initializeDependencyInjector();
      //  initializeToolbar();
        cakeListImplPresenter.displayList();
        initializeRecycler();

        return R.layout.recycler_main;
    }

    @Override
    public void onResume() {
        super.onResume();
        cakeListImplPresenter.start();
    }


    public void initializeRecycler(){

        //moviesListView.setLayoutManager(new LinearLayoutManager(getActivity()
            //    .getApplicationContext()));
        //moviesListAdapter.addItemDecoration(new SimpleSpaceDecorator(getContext(), R.dimen.spacing_medium));
       // moviesListAdapter = new MoviesListRecyclerAdapter(getActivity().getApplicationContext());
       // moviesListView.setAdapter(moviesListAdapter);

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
    public void showCakesAdapter() {

    }

    @Override
    public void setPresenter(@NotNull CakeListContract.IPresenter presenter) {
        mPresenter = checkNotNull(presenter);

    }
}
