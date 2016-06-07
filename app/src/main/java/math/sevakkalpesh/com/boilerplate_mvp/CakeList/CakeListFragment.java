package math.sevakkalpesh.com.boilerplate_mvp.CakeList;

import math.sevakkalpesh.com.boilerplate_mvp.R;
import math.sevakkalpesh.com.boilerplate_mvp.ui.fragment.BaseFragment;

/**
 * Created by kalpesh on 06/06/2016.
 */
public class CakeListFragment extends BaseFragment {

    public static CakeListFragment newInstance() {
        return new CakeListFragment();
    }


    @Override
    protected int getFragmentLayout() {
     //   initializeDependencyInjector();
      //  initializeToolbar();
        initializeRecycler();
        return R.layout.recycler_main;
    }

    public void initializeToolbar(){

    }

  //  private void initializeList(RecyclerView list, RecyclerView.Adapter adapter) {
    //    list.setLayoutManager(new LinearLayoutManager(getContext()));
      //  list.addItemDecoration(new SimpleSpaceDecorator(getContext(), R.dimen.spacing_medium));
        //list.setAdapter(adapter);
   // }

    public void initializeRecycler(){

        //moviesListView.setLayoutManager(new LinearLayoutManager(getActivity()
            //    .getApplicationContext()));
        //moviesListAdapter.addItemDecoration(new SimpleSpaceDecorator(getContext(), R.dimen.spacing_medium));
       // moviesListAdapter = new MoviesListRecyclerAdapter(getActivity().getApplicationContext());
       // moviesListView.setAdapter(moviesListAdapter);

    }

}
