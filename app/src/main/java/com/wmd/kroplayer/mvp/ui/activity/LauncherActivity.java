package com.wmd.kroplayer.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wmd.kroplayer.App;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.base.BaseActivity;
import com.wmd.kroplayer.di.compontent.DaggerLauncherComponent;
import com.wmd.kroplayer.mvp.contract.LauncherContract;
import com.wmd.kroplayer.mvp.presenter.LauncherPresenter;

import butterknife.BindView;

public class LauncherActivity extends BaseActivity<LauncherPresenter> implements LauncherContract.View {


      @BindView(R.id.iv_logo)
      ImageView ivLogo;
      @BindView(R.id.ll_logo)
      LinearLayout llLogo;

      @Override
      public int initView(@Nullable Bundle savedInstanceState) {

            return R.layout.activity_launcher;

      }

      @Override
      public void initData(@Nullable Bundle savedInstanceState) {
//            setupWindowAnimations();
      }


      @Override
      public Activity getActivity() {

            return this;
      }

      @Override
      public void turnToMain() {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
      }

      @Override
      public RxPermissions getRxPermissions() {

            return new RxPermissions(this);
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

      }

}
