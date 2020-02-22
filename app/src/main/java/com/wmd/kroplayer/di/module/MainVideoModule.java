package com.wmd.kroplayer.di.module;

import com.wmd.kroplayer.app.AppDataManager;
import com.wmd.kroplayer.di.scope.FragmentScope;
import com.wmd.kroplayer.mvp.contract.MainVideoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2219
 * Version: 1.0.0
 * Desc:    MainVideoModule
 */
@Module
public class MainVideoModule {
      @FragmentScope
      @Provides
      static AppDataManager provideMainVideoModule(MainVideoContract.View view){
            return AppDataManager.newInstance(view.getActivity());
      }
}
