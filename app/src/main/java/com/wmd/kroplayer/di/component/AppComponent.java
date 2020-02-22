package com.wmd.kroplayer.di.component;


import com.wmd.kroplayer.App;
import com.wmd.kroplayer.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/1620
 * Version: 1.0.0
 * Desc:
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

      void inject(App app);

      @Component.Builder
      interface Builder {
            @BindsInstance
            Builder application(App app);

            AppComponent build();
      }
}
