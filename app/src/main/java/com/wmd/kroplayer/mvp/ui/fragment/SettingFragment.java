package com.wmd.kroplayer.mvp.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.wmd.kroplayer.R;
import com.wmd.kroplayer.base.BaseFragment;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2012
 * Version: 1.0.0
 * Desc:    SettingFragment
 */
public class SettingFragment extends BaseFragment {
//      private static SettingFragment fragment;
//
//      private SettingFragment() {
//
//      }
//
//      public static  SettingFragment newInstance() {
//
//            Bundle bundle = new Bundle();
//            if (fragment == null) {
//                  fragment = new SettingFragment();
//                  fragment.setArguments(bundle);
//            }
//            return fragment;
//      }

      @Override
      public int initLayoutRes() {

            return R.layout.fragment_setting;
      }

      @Override
      public void initData(View mView, @Nullable Bundle savedInstanceState) {

      }

      @Override
      protected void initFragmentComponent() {

      }
}
