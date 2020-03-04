package com.wmd.kroplayer.di.module;

import androidx.fragment.app.Fragment;

import com.wmd.kroplayer.di.scope.ActivityScope;
import com.wmd.kroplayer.mvp.contract.MainContract;
import com.wmd.kroplayer.mvp.ui.activity.MainActivity;
import com.wmd.kroplayer.mvp.ui.fragment.MainVideoFragment;
import com.wmd.kroplayer.mvp.ui.fragment.SettingFragment;
import com.wmd.kroplayer.mvp.ui.fragment.VideoListFragment;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2014
 * Version: 1.0.0
 * Desc:    MainModule
 */
@Module
public class MainModule {

      @ActivityScope
      @Provides
      static MainVideoFragment provideMainVideoFragment() {
            return MainVideoFragment.newInstance();
      }

      @ActivityScope
      @Provides
      static VideoListFragment provideVideoListFragment() {
            return VideoListFragment.newInstance();
      }

      @ActivityScope
      @Provides
      static SettingFragment provideSettingFragment() {
            return SettingFragment.newInstance();
      }

      @ActivityScope
      @Provides
      static List<Fragment> provideFragmentList(){
            return new ArrayList<>();
      }

}
