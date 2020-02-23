package com.wmd.kroplayer.mvp.presenter;

import android.annotation.SuppressLint;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.wmd.kroplayer.adapter.PullToRefreshAdapter;
import com.wmd.kroplayer.base.BasePresenter;
import com.wmd.kroplayer.bean.VideoInfoBean;
import com.wmd.kroplayer.di.scope.FragmentScope;
import com.wmd.kroplayer.mvp.contract.MainVideoContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2210
 * Version: 1.0.0
 * Desc:    MainVideoPresenter
 */
@FragmentScope
public class MainVideoPresenter extends BasePresenter<MainVideoContract.Model, MainVideoContract.View> {
      private boolean isFirst = true;
      @Inject
      List<VideoInfoBean> videoInfoBeans;
      @Inject
      PullToRefreshAdapter mAdapter;
      private int preEndIndex;

      @Inject
      public MainVideoPresenter(MainVideoContract.View view, MainVideoContract.Model model) {
            super(view, model);
      }


      @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
      void onCreate() {
            pullToRefresh();
      }

      @SuppressLint("CheckResult")
      public void pullToRefresh() {
            mModel
                    .getVideoInfos(mView.getActivity())
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(disposable -> {
                          if (true) {
                                mView.hideLoading();//隐藏下拉刷新的进度条
                          } else {
                                mView.startLoadMore();
                          }
                    }).subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> {
                          if (true)
                                mView.hideLoading();//隐藏下拉刷新的进度条
                          else
                                mView.endLoadMore();//隐藏上拉加载更多的进度条
                    })
                    .subscribe(videoInfoBeanList -> {
                          if (true) videoInfoBeans.clear();//如果是下拉刷新则清空列表
                          preEndIndex = videoInfoBeans.size();//更新之前列表总长度,用于确定加载更多的起始位置
                          videoInfoBeans.addAll(videoInfoBeanList);
                          if (true)
                                mAdapter.notifyDataSetChanged();
                          else
                                mAdapter.notifyItemRangeInserted(preEndIndex, videoInfoBeanList.size());
                    });

      }


}
