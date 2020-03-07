package com.wmd.kroplayer.base;

import android.os.Build;
import android.os.Bundle;
import android.view.InflateException;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.internal.CustomAdapt;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/16
 * Version: 1.0.0
 * Desc:    BaseActivity
 */
public abstract class BaseActivity<P extends Ipresenter> extends RxAppCompatActivity implements IActivity, CustomAdapt {
      @Inject
      protected P mPresenter;
      private Unbinder mUnbinder;


      @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {
            // 去掉窗口标题
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            // 隐藏状态栏,全屏显示
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //实现沉浸式状态栏
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
            AutoSizeConfig.getInstance().stop(this);

            if (mPresenter != null) {
                  mPresenter.onDestory();
                  mPresenter = null;
            }
            if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
                  mUnbinder.unbind();
      }


}
