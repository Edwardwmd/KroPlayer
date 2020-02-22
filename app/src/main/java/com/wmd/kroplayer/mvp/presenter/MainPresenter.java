package com.wmd.kroplayer.mvp.presenter;

import com.wmd.kroplayer.base.BasePresenter;
import com.wmd.kroplayer.di.scope.ActivityScope;
import com.wmd.kroplayer.mvp.contract.MainContract;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/1812
 * Version: 1.0.0
 * Desc:     MainPresenter
 */
@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
      @Inject
      public MainPresenter(MainContract.View mView) {
            super(mView);
      }

}
