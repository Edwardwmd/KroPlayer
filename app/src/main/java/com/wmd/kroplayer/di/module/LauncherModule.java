package com.wmd.kroplayer.di.module;

import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wmd.kroplayer.di.scope.ActivityScope;
import com.wmd.kroplayer.mvp.contract.LauncherContract;

import dagger.Module;
import dagger.Provides;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/1713
 * Version: 1.0.0
 * Desc:
 */
@Module
public class LauncherModule {

      @ActivityScope
      @Provides
      static RxPermissions provideRxPermissions(LauncherContract.View view) {

            return new RxPermissions((FragmentActivity) view.getActivity());
      }
}
