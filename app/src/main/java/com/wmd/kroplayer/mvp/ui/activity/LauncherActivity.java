package com.wmd.kroplayer.mvp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wmd.kroplayer.App;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.base.BaseActivity;
import com.wmd.kroplayer.di.component.DaggerLauncherComponent;
import com.wmd.kroplayer.mvp.contract.LauncherContract;
import com.wmd.kroplayer.mvp.presenter.LauncherPresenter;
import com.wmd.kroplayer.utils.AppUtils;
import com.wmd.kroplayer.utils.JumpUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class LauncherActivity extends BaseActivity<LauncherPresenter> implements LauncherContract.View {


      @BindView(R.id.iv_logo)
      ImageView ivLogo;
      @BindView(R.id.ll_logo)
      LinearLayout llLogo;
      @Inject
      RxPermissions rxPermissions;

      @Override
      public int initView(@Nullable Bundle savedInstanceState) {

            return R.layout.activity_launcher;

      }

      @Override
      public void initData(@Nullable Bundle savedInstanceState) {

      }


      @Override
      public Activity getActivity() {

            return this;
      }

      @Override
      public void turnToMain() {

            JumpUtils.LauncherToMain(this);
      }

      @Override
      public RxPermissions getRxPermissions() {

            return rxPermissions;
      }

      @Override
      protected void initActivityCompontent() {

            DaggerLauncherComponent
                    .builder()
                    .appComponent(App.getAppComponent())
                    .view(this)
                    .build()
                    .inject(this);
      }

      @Override
      public void showLoading() {

      }

      @Override
      public void hideLoading() {

      }


      @Override
      public void showMessage(String message) {

            AppUtils.showSnackbar(this, message, false);
      }

      @Override
      protected void onDestroy() {

            super.onDestroy();
            rxPermissions = null;

      }
}
