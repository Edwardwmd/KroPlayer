package com.wmd.kroplayer.mvp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wmd.kroplayer.App;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.base.BaseActivity;
import com.wmd.kroplayer.di.component.DaggerMainComponent;
import com.wmd.kroplayer.mvp.contract.MainContract;
import com.wmd.kroplayer.mvp.presenter.MainPresenter;
import com.wmd.kroplayer.mvp.ui.fragment.MainVideoFragment;
import com.wmd.kroplayer.mvp.ui.fragment.SettingFragment;
import com.wmd.kroplayer.mvp.ui.fragment.VideoListFragment;
import com.wmd.kroplayer.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.wmd.kroplayer.utils.ContractUtils.HOME_MAIN_VIDEO;
import static com.wmd.kroplayer.utils.ContractUtils.SECOND_VIDEO_PLAYERFILE;
import static com.wmd.kroplayer.utils.ContractUtils.THIRD_SETTING;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener {


      @BindView(R.id.tab_bottom_nav)
      BottomNavigationView tabBottomNav;
      private List<Fragment> fragmentList;
      private int lastIndex;

      @Override
      protected void initActivityCompontent() {

            DaggerMainComponent
                    .builder()
                    .appComponent(App.getAppComponent())
                    .view(this)
                    .build()
                    .inject(this);
      }

      @Override
      public int initView(@Nullable Bundle savedInstanceState) {

            return R.layout.activity_main;
      }

      @Override
      public void initData(@Nullable Bundle savedInstanceState) {
            //解决BottomNavigationView大于3个item时的位移
            AppUtils.disableShiftMode(tabBottomNav);
            //绑定并初始化BottomNavigationView和Frangment
            stepUpFragment();

      }

      private void stepUpFragment() {
            addFragmentList();
            switchFragment(HOME_MAIN_VIDEO);
            tabBottomNav.setOnNavigationItemSelectedListener(this);
      }

      @Override
      public Activity getActivity() {

            return this;
      }

      @Override
      public void showLoading() {

      }

      @Override
      public void hideLoading() {

      }

      @Override
      public void showMessage(String message) {

      }

      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                  case R.id.tab_mainvideo:
                        switchFragment(HOME_MAIN_VIDEO);
                        break;
                  case R.id.tab_videolist:
                        switchFragment(SECOND_VIDEO_PLAYERFILE);
                        break;
                  case R.id.tab_setting:
                        switchFragment(THIRD_SETTING);
                        break;
            }

            return true;
      }

      private void switchFragment(int position) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment currentFragment = fragmentList.get(position);
            Fragment lastFragment = fragmentList.get(lastIndex);
            lastIndex = position;
            ft.hide(lastFragment);
            if (!currentFragment.isAdded()) {
                  getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
                  ft.add(R.id.fl_content, currentFragment);
            }
            ft.show(currentFragment);
            ft.commitAllowingStateLoss();
      }

      private void addFragmentList() {

            if (fragmentList == null) {
                  fragmentList = new ArrayList<>();
                  fragmentList.add(MainVideoFragment.newInstance(this));
                  fragmentList.add(VideoListFragment.newInstance());
                  fragmentList.add(SettingFragment.newInstance());
            }

      }

}
