package com.wmd.kroplayer.mvp.ui;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.base.BaseActivity;
import com.wmd.kroplayer.mvp.contract.MainContract;
import com.wmd.kroplayer.mvp.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

      @BindView(R.id.fl_content)
      FrameLayout flContent;
      @BindView(R.id.tab_bottom_nav)
      BottomNavigationView tabBottomNav;

      @Override
      protected void initActivityCompontent() {

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
      public int initView(@Nullable Bundle savedInstanceState) {

            return R.layout.activity_main;
      }

      @Override
      public void initData(@Nullable Bundle savedInstanceState) {

      }
}
