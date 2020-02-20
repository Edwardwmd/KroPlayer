package com.wmd.kroplayer.mvp.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wmd.kroplayer.R;
import com.wmd.kroplayer.base.BaseActivity;
import com.wmd.kroplayer.base.BaseFragment;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2012
 * Version: 1.0.0
 * Desc:
 */
public class MainVideoFragment extends BaseFragment {

      public static MainVideoFragment newInstance() {

            MainVideoFragment mainVideoFragment = null;
            Bundle bundle = new Bundle();
            synchronized (MainVideoFragment.class) {
                  if (mainVideoFragment == null) {
                        mainVideoFragment = new MainVideoFragment();
                        mainVideoFragment.setArguments(bundle);
                  }
            }
            return mainVideoFragment;
      }

      @Override
      public int initLayoutRes() {

            return R.layout.fragment_main_video;
      }

      @Override
      public void initData(View mView, @Nullable Bundle savedInstanceState) {

      }
}
