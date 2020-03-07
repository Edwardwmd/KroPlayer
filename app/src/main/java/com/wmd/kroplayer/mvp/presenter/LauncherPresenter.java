package com.wmd.kroplayer.mvp.presenter;

import android.Manifest;
import android.annotation.SuppressLint;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.wmd.kroplayer.base.BasePresenter;
import com.wmd.kroplayer.di.scope.ActivityScope;
import com.wmd.kroplayer.mvp.contract.LauncherContract;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


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

      @Inject
      public LauncherPresenter(LauncherContract.View mView) {
            super(mView);
      }

      @SuppressLint("CheckResult")
      @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
      void onCreate() {

            mView.getRxPermissions().request(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.INTERNET)
                    .subscribe(aBoolean -> {

                          if (aBoolean) {
                                //申请的权限全部允许
                                jumpToMainByTimer();
                          } else {
                                mView.showMessage("请开启权限!");
                                //申请的权限全部允许
                                jumpToMainByTimer();
                          }
                    });
      }

      private void jumpToMainByTimer() {
            //跳转
            addCompositeDisposable(Observable.timer(1500, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> mView.turnToMain()));

      }

}
