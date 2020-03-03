package com.wmd.kroplayer.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.wmd.kroplayer.App;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.adapter.PullToRefreshAdapter;
import com.wmd.kroplayer.base.BaseFragment;
import com.wmd.kroplayer.di.component.DaggerMainVideoComponent;
import com.wmd.kroplayer.mvp.contract.MainVideoContract;
import com.wmd.kroplayer.mvp.presenter.MainVideoPresenter;
import com.wmd.kroplayer.mvp.ui.activity.MainActivity;
import com.wmd.kroplayer.utils.AppUtils;
import com.wmd.kroplayer.utils.FileUtils;
import com.wmd.kroplayer.utils.JumpUtils;
import com.wmd.kroplayer.utils.StringsUtils;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/20
 * Version: 1.0.0
 * Desc:    MainVideoFragment
 */
public class MainVideoFragment extends BaseFragment<MainVideoPresenter> implements MainVideoContract.View,
        SwipeRefreshLayout.OnRefreshListener,
        OnItemLongClickListener,
        OnItemClickListener {

      @BindView(R.id.rcv_video)
      RecyclerView rcvVideo;
      @BindView(R.id.swipeLayout)
      SwipeRefreshLayout swipeLayout;
      @Inject
      RecyclerView.LayoutManager layoutManager;
      @Inject
      PullToRefreshAdapter mAdapter;
      @Inject
      AlertDialog.Builder builder;
      private Context context;
      //防止多次点击
      private int clickTimes = 0;

      private boolean isLoadingMore;

      private static MainVideoFragment fragment;
      private View notDataView;

      private MainVideoFragment(Context context) {
            this.context = context;
      }

      public static MainVideoFragment newInstance(Context context) {

            Bundle bundle = new Bundle();
            if (fragment == null) {
                  synchronized (MainVideoFragment.class) {
                        if (fragment == null) {
                              fragment = new MainVideoFragment(context);
                              fragment.setArguments(bundle);
                        }
                  }
            }
            return fragment;
      }


      @Override
      public int initLayoutRes() {
            return R.layout.fragment_main_video;
      }

      @Override
      public void initData(View mView, @Nullable Bundle savedInstanceState) {
            notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rcvVideo.getParent(), false);
            swipeLayout.setOnRefreshListener(this);
            rcvVideo.setLayoutManager(layoutManager);
            //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
            rcvVideo.setHasFixedSize(true);
            mAdapter.setAnimationFirstOnly(false);
            mAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn);
            rcvVideo.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(this);
            mAdapter.setOnItemLongClickListener(this);
            notDataView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        mPresenter.pullToRefresh();
                  }
            });
      }

      @Override
      public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            //防误触点击
            if (clickTimes >= 1) {
                  return;
            }
            JumpUtils.JumpToVideoPlay((MainActivity) context,
                    view.findViewById(R.id.iv_video_thum),
                    mAdapter.getVideoInfoBeanList().get(position),
                    StringsUtils.getString(R.string.text_transition_share_image));
            clickTimes += 1;

      }

      @Override
      public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
            showAlertDialog(position);
            return true;
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
      public void startLoadMore() {
            isLoadingMore = true;
      }

      @Override
      public void endLoadMore() {
            isLoadingMore = false;
      }

      @Override
      public void setLoadingEmptyView() {
            //加载空布局
            mAdapter.setEmptyView(notDataView);
      }

      @Override
      public void onRefresh() {
            mPresenter.pullToRefresh();
      }


      private void showAlertDialog(int position) {
            builder.setMessage("你确定要删除此文件吗 |^?^|");
            builder.setCancelable(true);
            builder.setPositiveButton("确定", (dialog, which) -> {

                  int delete = FileUtils.deleteExternalVideoFile(context, mAdapter.getVideoInfoBeanList().get(position).getPath());
                  if (delete != -1) {
                        if (position == 0) {
                              mAdapter.setEmptyView(notDataView);
                        }
                        mAdapter.remove(position);
                        mAdapter.notifyItemChanged(position);
                        AppUtils.showSnackbar(getActivity(), "该文件删除成功|^!^|", false);
                  } else {
                        AppUtils.showSnackbar(getActivity(), "该文件删除失败|^!^|", false);
                  }

                  dialog.dismiss();
            });
            builder.setNegativeButton("取消", (dialog, which) -> dialog.dismiss());
            builder.create().show();
      }

      @Override
      public void onStop() {
            super.onStop();
            clickTimes = 0;
      }

      @Override
      public void onDestroy() {
            super.onDestroy();
            if (mAdapter != null)
                  mAdapter.weakRecyclerView.clear();
            mAdapter = null;
            layoutManager = null;
            builder = null;
            context = null;
      }

}
