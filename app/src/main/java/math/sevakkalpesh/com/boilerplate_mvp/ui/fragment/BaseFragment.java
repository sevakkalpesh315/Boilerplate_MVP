/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package math.sevakkalpesh.com.boilerplate_mvp.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Base fragment created to be extended by every fragment in this application. This class provides
 * dependency injection configuration, ButterKnife Android library configuration and some methods
 * common to every fragment.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
public abstract class BaseFragment extends Fragment {

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    injectDependencies();
  }

  /**
   * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
   * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
   * inflate in this method when extends BaseFragment.
   */
  protected abstract int getFragmentLayout();

  /**
   * Replace every field annotated using @Inject annotation with the provided dependency specified
   * inside a Dagger module value.
   */
  private void injectDependencies() {
   // ((AppApplication) getApplicationContext()).inject(this);
  }

  /**
   * Replace every field annotated with ButterKnife annotations like @InjectView with the proper
   * value.
   *
   * @param view to extract each widget injected in the fragment.
   */
  private void bindViews(final View view) {
    ButterKnife.bind(this, view);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(getFragmentLayout(), container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    bindViews(view);
    initView(view, savedInstanceState);
  }

  /**
   * Use this method to initialize view components.
   */
  public void initView(View view, Bundle savedInstanceState) {
  }

  @Override
  public void onDestroyView() {
    unbindViews();
    super.onDestroyView();
  }

  private void unbindViews() {
    ButterKnife.unbind(this);
  }



}
