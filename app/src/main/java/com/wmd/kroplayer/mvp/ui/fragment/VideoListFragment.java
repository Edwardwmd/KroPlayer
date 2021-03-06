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
 * Desc:    VideoListFragment
 */
public class VideoListFragment extends BaseFragment {
      private volatile static VideoListFragment fragment;

      public static VideoListFragment newInstance() {

            Bundle bundle = new Bundle();
            if (fragment == null) {
                  synchronized (VideoListFragment.class) {
                        if (fragment == null) {
                              fragment = new VideoListFragment();
                              fragment.setArguments(bundle);
                        }
                  }
            }
            return fragment;
      }

      @Override
      public int initLayoutRes() {

            return R.layout.fragment_video_list;
      }

      @Override
      public void initData(View mView, @Nullable Bundle savedInstanceState) {

      }

      @Override
      protected void initFragmentComponent() {

      }
}
