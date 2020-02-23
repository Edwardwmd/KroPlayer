package com.wmd.kroplayer.mvp.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wmd.kroplayer.App;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.base.BaseActivity;

import com.wmd.kroplayer.di.component.DaggerMainComponent;
import com.wmd.kroplayer.mvp.contract.MainContract;
import com.wmd.kroplayer.mvp.presenter.MainPresenter;
import com.wmd.kroplayer.utils.AppUtils;


import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {


      @BindView(R.id.tab_bottom_nav)
      BottomNavigationView tabBottomNav;

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

            AppBarConfiguration appBarConfiguration = new AppBarConfiguration
                    .Builder(R.id.tab_mainvideo, R.id.tab_videolist, R.id.tab_setting)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.fl_content);
            NavigationUI.navigateUp(navController, appBarConfiguration);
            NavigationUI.setupWithNavController(tabBottomNav, navController);

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
//
//      @Override
//      protected void onDestroy() {
//
//            super.onDestroy();
//            manager = null;
//      }
}
