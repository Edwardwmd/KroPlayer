package com.wmd.kroplayer.mvp.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wmd.kroplayer.App;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.adapter.PullToRefreshAdapter;
import com.wmd.kroplayer.base.BaseFragment;
import com.wmd.kroplayer.di.component.DaggerMainVideoComponent;
import com.wmd.kroplayer.mvp.contract.MainVideoContract;
import com.wmd.kroplayer.mvp.presenter.MainVideoPresenter;


import javax.inject.Inject;

import butterknife.BindView;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2012
 * Version: 1.0.0
 * Desc:    MainVideoFragment
 */
public class MainVideoFragment extends BaseFragment<MainVideoPresenter> implements MainVideoContract.View, SwipeRefreshLayout.OnRefreshListener {


      @BindView(R.id.rcv_video)
      RecyclerView rcvVideo;
      @BindView(R.id.swipeLayout)
      SwipeRefreshLayout swipeLayout;
      @Inject
      RecyclerView.LayoutManager layoutManager;
      @Inject
      PullToRefreshAdapter mAdapter;

      private boolean isLoadingMore;

      @Override
      public int initLayoutRes() {

            return R.layout.fragment_main_video;
      }

      @Override
      public void initData(View mView, @Nullable Bundle savedInstanceState) {
            swipeLayout.setOnRefreshListener(this);
            rcvVideo.setLayoutManager(layoutManager);
            //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
            rcvVideo.setHasFixedSize(true);
            rcvVideo.setAdapter(mAdapter);

      }

      @Override
      public void showLoading() {
            swipeLayout.setRefreshing(true);
      }

      @Override
      public void hideLoading() {
            swipeLayout.setRefreshing(false);
      }

      @Override
      public void showMessage(String message) {

      }

      @Override
      protected void initFragmentComponent() {

            DaggerMainVideoComponent
                    .builder()
                    .appComponent(App.getAppComponent())
                    .view(this)
                    .build()
                    .inject(this);
      }

      @Override
      public void onDestroy() {
            super.onDestroy();
            if (mAdapter != null)
                  mAdapter.weakRecyclerView.clear();
            mAdapter = null;
            layoutManager = null;


      }

      @Override
      public void startLoadMore() {
            isLoadingMore = true;
      }

      @Override
      public void endLoadMore() {
            isLoadingMore = false;
      }

      @Override
      public void onRefresh() {
            mPresenter.pullToRefresh();
      }
}
