package com.wmd.kroplayer.mvp.ui.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.TooltipCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wmd.kroplayer.App;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.base.BaseActivity;
import com.wmd.kroplayer.di.compontent.DaggerMainComponent;
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

      @RequiresApi(api = Build.VERSION_CODES.O)
      @Override
      public void initData(@Nullable Bundle savedInstanceState) {

            stepUpFragment();
            enableBottomBar();

            AppUtils.showToast( findViewById(tabBottomNav.getMenu().getItem(0).getItemId()).getTooltipText());
      }

      private void stepUpFragment() {

            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.tab_mainvideo, R.id.tab_videolist, R.id.tab_setting)
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
      private void enableBottomBar(){
            for (int i = 0; i < tabBottomNav.getMenu().size(); i++) {
                  MenuItem item = tabBottomNav.getMenu().getItem(i);
                  TooltipCompat.setTooltipText(findViewById(item.getItemId()), null);
            }
      }
}
