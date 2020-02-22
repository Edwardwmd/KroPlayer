package com.wmd.kroplayer.di.component;

import com.wmd.kroplayer.mvp.ui.activity.LauncherActivity;
import com.wmd.kroplayer.di.module.LauncherModule;
import com.wmd.kroplayer.di.scope.ActivityScope;
import com.wmd.kroplayer.mvp.contract.LauncherContract;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/1713
 * Version: 1.0.0
 * Desc:
 */
@ActivityScope
@Component(modules = LauncherModule.class, dependencies = AppComponent.class)
public interface LauncherComponent {
      void inject(LauncherActivity activity);

      @Component.Builder
      interface Builder{
            @BindsInstance
            LauncherComponent.Builder view(LauncherContract.View view);
            LauncherComponent.Builder appComponent(AppComponent appComponent);
            LauncherComponent build();
      }



}
