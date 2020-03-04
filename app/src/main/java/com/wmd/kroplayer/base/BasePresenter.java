package com.wmd.kroplayer.base;

import android.annotation.SuppressLint;

import androidx.core.util.Preconditions;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.orhanobut.logger.Logger;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/1612
 * Version: 1.0.0
 * Desc:    BasePresenter
 */
public class BasePresenter<M extends IModel, V extends BaseView> implements Ipresenter, LifecycleObserver {
      protected M mModel;
      protected V mView;
      protected CompositeDisposable mCompositeDisposable;

      public BasePresenter() {
            onStart();
      }

      @SuppressLint("RestrictedApi")
      public BasePresenter(V mView) {
            Preconditions.checkNotNull(mView, "%s cannot be null" + BaseView.class.getName());
            this.mView = mView;
            onStart();
      }

      @SuppressLint("RestrictedApi")
      public BasePresenter(V mView, M mModel) {
            Preconditions.checkNotNull(mModel, "%s cannot be null" + IModel.class.getName());
            Preconditions.checkNotNull(mView, "%s cannot be null" + BaseView.class.getName());
            this.mView = mView;
            this.mModel = mModel;
            onStart();
      }

      @Override
      public void onStart() {
            if (mView != null && mView instanceof LifecycleOwner) {
                  ((LifecycleOwner) mView).getLifecycle().addObserver(this);
                  Logger.d("BasePresenter-->onStart-->RootView添加成功!");
                  if (mModel != null && mModel instanceof LifecycleObserver) {
                        Logger.d("BasePresenter-->onStart-->mModel添加成功!");
                        ((LifecycleOwner) mView).getLifecycle().addObserver((LifecycleObserver) mModel);
                  }
            }
      }

      @Override
      public void onDestory() {
            if (mView != null)
                  mView = null;
            if (mModel != null)
                  mModel.onDestory();
            mModel = null;
            if (mCompositeDisposable != null)
                  mCompositeDisposable.clear();
            mCompositeDisposable = null;
      }

      public void addCompositeDisposable(Disposable disposable) {
            if (mCompositeDisposable == null) {
                  mCompositeDisposable = new CompositeDisposable();
            }
            mCompositeDisposable.add(disposable);
      }

      @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
      void onDeatory(LifecycleOwner ower) {
            ower.getLifecycle().removeObserver(this);
      }
}
