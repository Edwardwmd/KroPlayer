package com.wmd.kroplayer.base;

import android.os.Build;
import android.os.Bundle;

import android.view.InflateException;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/1619
 * Version: 1.0.0
 * Desc:    BaseActivity
 */
public abstract class BaseActivity<P extends Ipresenter> extends RxAppCompatActivity implements IActivity {
      @Inject
      protected P mPresenter;
      private Unbinder mUnbinder;


      @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                  getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                          WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            super.onCreate(savedInstanceState);
            try {
                  int layoutRes = initView(savedInstanceState);
                  if (layoutRes != 0) {
                        setContentView(layoutRes);
                        mUnbinder = ButterKnife.bind(this);

                  }

            } catch (Exception e) {
                  if (e instanceof InflateException) throw e;
                  e.printStackTrace();
            }
            initActivityCompontent();
            initData(savedInstanceState);
      }

      protected abstract void initActivityCompontent();

      @Override
      protected void onDestroy() {

            super.onDestroy();
            if (mPresenter != null) {
                  mPresenter.onDestory();
                  mPresenter = null;
            }
            if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
                  mUnbinder.unbind();
      }

      protected void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(frameId, fragment);
            transaction.commit();
      }

}
