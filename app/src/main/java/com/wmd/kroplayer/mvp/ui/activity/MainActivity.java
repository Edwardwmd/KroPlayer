package com.wmd.kroplayer.mvp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wmd.kroplayer.App;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.base.BaseActivity;
import com.wmd.kroplayer.di.component.DaggerMainComponent;
import com.wmd.kroplayer.mvp.contract.MainContract;
import com.wmd.kroplayer.mvp.presenter.MainPresenter;
import com.wmd.kroplayer.utils.AppUtils;

import butterknife.BindView;

import static com.wmd.kroplayer.utils.ContractUtils.HOME_MAIN_VIDEO;
import static com.wmd.kroplayer.utils.ContractUtils.SECOND_VIDEO_PLAYERFILE;
import static com.wmd.kroplayer.utils.ContractUtils.THIRD_SETTING;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener {

      @BindView(R.id.tab_bottom_nav)
      BottomNavigationView tabBottomNav;
      @BindView(R.id.tab_toolbar)
      MaterialToolbar tabToolbar;
      private long mPressedTime = 0;

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
            //BottomNavigationView item选择事件
            tabBottomNav.setOnNavigationItemSelectedListener(this);
      }

      @Override
      public Activity getActivity() {
            return this;
      }

      @Override
      public int addContainerViewRes() {
            return R.id.fl_container;
      }

      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                  case R.id.tab_mainvideo:
                        mPresenter.switchFragment(HOME_MAIN_VIDEO);
                        break;
                  case R.id.tab_videolist:
                        mPresenter.switchFragment(SECOND_VIDEO_PLAYERFILE);
                        break;
                  case R.id.tab_setting:
                        mPresenter.switchFragment(THIRD_SETTING);
                        break;
            }
            return true;
      }

      @Override
      public void onBackPressed() {
            long mCurrentTimes = System.currentTimeMillis();
            if (mCurrentTimes - mPressedTime >= 3000) {
                  AppUtils.showSnackbar(this, getString(R.string.text_click_once_close), false);
                  mPressedTime = mCurrentTimes;
            } else {
                  finish();
                  System.exit(0);
            }

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

      public MaterialToolbar getTabToolbar() {
            return tabToolbar;
      }

}
