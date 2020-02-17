package com.wmd.kroplayer.mvp.presenter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.orhanobut.logger.Logger;
import com.wmd.kroplayer.base.BasePresenter;
import com.wmd.kroplayer.di.scope.ActivityScope;
import com.wmd.kroplayer.mvp.contract.LauncherContract;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/1712
 * Version: 1.0.0
 * Desc:    LauncherPresenter
 */
@ActivityScope
public class LauncherPresenter extends BasePresenter<LauncherContract.Model, LauncherContract.View> {
      private LauncherContract.View mView;
      private boolean isFirst = true;

      @Inject
      public LauncherPresenter(LauncherContract.View mView) {

            super(mView);
            this.mView = mView;
      }

      @SuppressLint("CheckResult")
      @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
      void onCreate() {

            if (mView != null) {
                  mView.getRxPermissions().request(Manifest.permission.READ_EXTERNAL_STORAGE,
                          Manifest.permission.WRITE_EXTERNAL_STORAGE,
                          Manifest.permission.READ_PHONE_STATE,
                          Manifest.permission.INTERNET)
                          .subscribe(new Consumer<Boolean>() {
                                @Override
                                public void accept(Boolean aBoolean) throws Exception {

                                      if (aBoolean) {
                                            //申请的权限全部允许
                                            if (isFirst) {
                                                  Toast.makeText(mView.getActivity(), "权限允许成功!", Toast.LENGTH_SHORT).show();
                                                  mView.turnToMain();
                                                  isFirst = false;
                                            } else {
                                                  mView.turnToMain();
                                            }

                                      } else {
                                            //只要有一个权限被拒绝，就会执行
                                            Toast.makeText(mView.getActivity(), "未授权,请移步授权!", Toast.LENGTH_SHORT).show();
                                      }
                                }
                          });
            }


      }

      @Override
      public void onDestory() {

            mView = null;
            Logger.d("LauncherPresenter--->onDestory---销毁成功!");
      }
}
