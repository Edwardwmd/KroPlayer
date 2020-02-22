package com.wmd.kroplayer.di.component;

import com.wmd.kroplayer.di.module.MainVideoModule;
import com.wmd.kroplayer.di.scope.FragmentScope;
import com.wmd.kroplayer.mvp.contract.MainContract;
import com.wmd.kroplayer.mvp.contract.MainVideoContract;
import com.wmd.kroplayer.mvp.ui.fragment.MainVideoFragment;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2219
 * Version: 1.0.0
 * Desc:
 */
@FragmentScope
@Component(modules = MainVideoModule.class, dependencies = AppComponent.class)
public interface MainVideoComponent {
      void inject(MainVideoFragment fragment);

      @Component.Builder
      interface Builder {
            @BindsInstance
            MainVideoComponent.Builder view(MainVideoContract.View view);

            MainVideoComponent.Builder appComponent(AppComponent appComponent);

            MainVideoComponent build();
      }
}
