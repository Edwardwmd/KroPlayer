package com.wmd.kroplayer.mvp.presenter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.wmd.kroplayer.base.BasePresenter;
import com.wmd.kroplayer.di.scope.ActivityScope;
import com.wmd.kroplayer.mvp.contract.MainContract;
import com.wmd.kroplayer.mvp.ui.activity.MainActivity;
import com.wmd.kroplayer.mvp.ui.fragment.MainVideoFragment;
import com.wmd.kroplayer.mvp.ui.fragment.SettingFragment;
import com.wmd.kroplayer.mvp.ui.fragment.VideoListFragment;

import java.util.List;

import javax.inject.Inject;

import static com.wmd.kroplayer.utils.ContractUtils.HOME_MAIN_VIDEO;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/1812
 * Version: 1.0.0
 * Desc:     MainPresenter
 */
@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
      @Inject
      MainVideoFragment mainVideoFragment;
      @Inject
      VideoListFragment videoListFragment;
      @Inject
      SettingFragment settingFragment;
      @Inject
      List<Fragment> fragmentList;
      private int lastIndex;

      @Inject
      public MainPresenter(MainContract.View mView) {
            super(mView);
      }

      @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
      void onCreate() {
            //添加fragment
            addFragmentList();
            //选择默认的主Fragment
            switchFragment(HOME_MAIN_VIDEO);
      }

      public void switchFragment(int position) {
            FragmentTransaction ft = ((MainActivity) mView.getActivity()).getSupportFragmentManager().beginTransaction();
            Fragment currentFragment = fragmentList.get(position);
            Fragment lastFragment = fragmentList.get(lastIndex);
            lastIndex = position;
            ft.hide(lastFragment);
            if (!currentFragment.isAdded()) {
                  ((MainActivity) mView.getActivity())
                          .getSupportFragmentManager()
                          .beginTransaction()
                          .remove(currentFragment).commit();
                  ft.add(mView.addContainerViewRes(), currentFragment);
            }
            ft.show(currentFragment);
            ft.commitAllowingStateLoss();
      }


      private void addFragmentList() {
            fragmentList.add(mainVideoFragment);
            fragmentList.add(videoListFragment);
            fragmentList.add(settingFragment);
      }



      @Override
      public void onDestory() {
            super.onDestory();
            if (fragmentList != null) {
                  if (fragmentList.size() > 0)
                        fragmentList.clear();
                  fragmentList = null;
            }
            mainVideoFragment = null;
            videoListFragment = null;
            settingFragment = null;
      }
}
