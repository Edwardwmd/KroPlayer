package com.wmd.kroplayer.mvp.presenter;

import com.wmd.kroplayer.base.BasePresenter;
import com.wmd.kroplayer.di.scope.FragmentScope;
import com.wmd.kroplayer.mvp.contract.MainVideoContract;

import javax.inject.Inject;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2210
 * Version: 1.0.0
 * Desc:    MainVideoPresenter
 */
@FragmentScope
public class MainVideoPresenter extends BasePresenter<MainVideoContract.Model, MainVideoContract.View> {
      @Inject
      public MainVideoPresenter(MainVideoContract.View view) {
            super(view);
      }

}
