package com.wmd.kroplayer.di.component;

import com.wmd.kroplayer.di.module.VideoPlayModule;
import com.wmd.kroplayer.mvp.contract.MainVideoContract;
import com.wmd.kroplayer.mvp.ui.activity.VideoPlayActivity;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/3/221
 * Version: 1.0.0
 * Desc:
 */
@Component(modules = VideoPlayModule.class,dependencies = AppComponent.class)
public interface VideoPlayComponent {
      void inject(VideoPlayActivity videoPlayActivity);


      }

