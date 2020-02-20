package com.wmd.kroplayer.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.trello.rxlifecycle3.components.support.RxFragment;
import com.wmd.kroplayer.base.IFragment;
import com.wmd.kroplayer.base.Ipresenter;

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

public abstract class BaseFragment<P extends Ipresenter> extends RxFragment implements IFragment {
      @Inject
      @Nullable
      protected P mPresenter;

      protected Context mContext;

      private Unbinder mUnbinder;

      public BaseFragment() {

      }

      @Override
      public void onAttach(Context context) {

            super.onAttach(context);
            mContext = context;
      }


      @Nullable
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View mView = inflater.inflate(initLayoutRes(), container, false);
            mUnbinder = ButterKnife.bind(this, mView);
            initData(mView, savedInstanceState);
            return mView;
      }


      @Override
      public void onDestroy() {

            super.onDestroy();
            if (mPresenter != null)
                  mPresenter.onDestory();
            mPresenter = null;
            if (mUnbinder != null & mUnbinder != Unbinder.EMPTY)
                  mUnbinder.unbind();
            mUnbinder = null;
            mContext = null;
      }
}
