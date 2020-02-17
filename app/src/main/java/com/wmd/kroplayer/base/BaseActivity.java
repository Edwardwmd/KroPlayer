package com.wmd.kroplayer.base;

import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleRegistry;


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
}
