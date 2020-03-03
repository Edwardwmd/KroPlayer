package com.wmd.kroplayer;

import android.app.Application;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


import com.bumptech.glide.Glide;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.shuyu.gsyvideoplayer.player.IjkPlayerManager;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.wmd.kroplayer.di.component.AppComponent;
import com.wmd.kroplayer.di.component.DaggerAppComponent;


/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/1519
 * Version: 1.0.0
 * Desc:    App
 */
public class App extends MultiDexApplication {
      public static App instance;
      public static AppComponent mAppComponent;

      @Override
      public void onCreate() {
            super.onCreate();
            instance = this;
            if (mAppComponent == null) {
                  mAppComponent = DaggerAppComponent
                          .builder()
                          .application(instance)//提供application
                          .build();
            }
            mAppComponent.inject(instance);
            //初始化Logger
            Logger.addLogAdapter(new AndroidLogAdapter());
            //打包index
            MultiDex.install(this);

            PlayerFactory.setPlayManager(IjkPlayerManager.class);//IjkPlay
            GSYVideoType.setRenderType(GSYVideoType.TEXTURE);

      }

      @Override
      public void onTerminate() {
            super.onTerminate();
            if (mAppComponent != null)
                  mAppComponent = null;
            instance = null;
            Glide.get(this).clearDiskCache();
            Glide.get(this).clearMemory();
      }

      public static AppComponent getAppComponent() {
            return mAppComponent;
      }
}
