package math.sevakkalpesh.com.boilerplate_mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import math.sevakkalpesh.com.boilerplate_mvp.MyApp;
import math.sevakkalpesh.com.boilerplate_mvp.R;

/**
 * @author jlmd
 */
public abstract class FragmentContainerActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        setupToolbar();

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        injectDependencies();
        bindViews();

    }
    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null){
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        }
    }
    private void injectDependencies()
    {
        ((MyApp) getApplication()).getApiComponent().inject(FragmentContainerActivity.this);

        //  ((AppApplication) getApplicationContext()).inject(this);
    }


    /**
     * Replace every field annotated with ButterKnife annotations like @InjectView with the proper
     * value.
     */
    private void bindViews() {
        ButterKnife.inject(this);
    }

    private void unbindView(){
        //ButterKnife.unbind(this);
    }
}
