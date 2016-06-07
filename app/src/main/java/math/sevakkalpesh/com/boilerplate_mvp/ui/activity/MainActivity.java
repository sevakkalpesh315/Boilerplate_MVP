package math.sevakkalpesh.com.boilerplate_mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import math.sevakkalpesh.com.boilerplate_mvp.CakeList.CakeListFragment;

/**
 * Created by kalpesh on 07/06/2016.
 */
public class MainActivity extends FragmentContainerActivity {
    @Override
    protected Fragment createFragment() {
        return CakeListFragment.newInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}
