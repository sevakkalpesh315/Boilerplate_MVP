package math.sevakkalpesh.com.boilerplate_mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import butterknife.ButterKnife;

/**
 * @author jlmd
 */
public class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        injectViews();
    }

    private void injectDependencies()
    {
      //  ((AppApplication) getApplicationContext()).inject(this);
    }
    /**
     * Replace every field annotated with ButterKnife annotations like @InjectView with the proper
     * value.
     */
    private void injectViews() {
        ButterKnife.inject(this);
    }
}
