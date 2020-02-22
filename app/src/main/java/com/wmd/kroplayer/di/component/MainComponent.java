package com.wmd.kroplayer.di.component;

import com.wmd.kroplayer.di.module.MainModule;
import com.wmd.kroplayer.di.scope.ActivityScope;
import com.wmd.kroplayer.mvp.contract.MainContract;
import com.wmd.kroplayer.mvp.ui.activity.MainActivity;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2014
 * Version: 1.0.0
 * Desc:
 */
@ActivityScope
@Component(modules = MainModule.class,dependencies = AppComponent.class)
public interface MainComponent {
      void inject(MainActivity mainActivity);

      @Component.Builder
      interface Builder{
            @BindsInstance
            MainComponent.Builder view(MainContract.View view);
            MainComponent.Builder appComponent(AppComponent appComponent);
            MainComponent build();
      }
}
