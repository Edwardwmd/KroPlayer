package com.wmd.kroplayer.di.module;


import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wmd.kroplayer.adapter.PullToRefreshAdapter;
import com.wmd.kroplayer.bean.VideoInfoBean;
import com.wmd.kroplayer.di.scope.ActivityScope;
import com.wmd.kroplayer.di.scope.FragmentScope;
import com.wmd.kroplayer.mvp.contract.MainVideoContract;
import com.wmd.kroplayer.mvp.model.MainVideoModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
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
public abstract class MainVideoModule {
      @Binds
      abstract MainVideoContract.Model bindUserModel(MainVideoModel model);

      @FragmentScope
      @Provides
      static RecyclerView.LayoutManager provideLayoutManager(MainVideoContract.View view) {
            return new LinearLayoutManager(view.getActivity());
      }

      @FragmentScope
      @Provides
      static List<VideoInfoBean> provideVideoInfoList() {
            return new ArrayList<>();
      }

      @FragmentScope
      @Provides
      static PullToRefreshAdapter provideVideoInfoAdapter(MainVideoContract.View view, List<VideoInfoBean> list) {
            return new PullToRefreshAdapter(view.getActivity(), list);
      }

      @FragmentScope
      @Provides
      static AlertDialog.Builder provideAlertDialogBuilder(MainVideoContract.View view) {
            return new AlertDialog.Builder(view.getActivity());
      }
}
