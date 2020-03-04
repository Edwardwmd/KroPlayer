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
        OnItemClickListener, View.OnClickListener {

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
      //数据空布局
      private View notDataView;
      private static MainVideoFragment fragment;

      private MainVideoFragment() {
      }

      public static MainVideoFragment newInstance() {

            Bundle bundle = new Bundle();
            if (fragment == null) {
                  synchronized (MainVideoFragment.class) {
                        if (fragment == null) {
                              fragment = new MainVideoFragment();
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
            swipeLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),
                    getResources().getColor(R.color.colorAccent),
                    getResources().getColor(R.color.colorblue));
            swipeLayout.setOnRefreshListener(this);
            rcvVideo.setLayoutManager(layoutManager);
            rcvVideo.setHasFixedSize(true);//如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
            mAdapter.setAnimationFirstOnly(false);
            mAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn);
            rcvVideo.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(this);
            mAdapter.setOnItemLongClickListener(this);
            notDataView.setOnClickListener(this);
      }

      @Override
      public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            //防误触点击
            if (clickTimes >= 1) {
                  return;
            }
            JumpUtils.JumpToVideoPlay((MainActivity) mContext,
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
      public void setLoadingEmptyView() {
            //加载空布局
            mAdapter.setEmptyView(notDataView);
      }

      @Override
      public void onRefresh() {
            mPresenter.pullToRefresh();
      }


      private void showAlertDialog(int position) {
            builder.setMessage(R.string.text_dialog_delete_video_message);
            builder.setCancelable(true);
            builder.setPositiveButton(R.string.text_sure, (dialog, which) -> {
                  int delete = FileUtils.deleteExternalVideoFile(mContext, mAdapter.getVideoInfoBeanList().get(position).getPath());
                  if (delete != -1) {
                        mAdapter.remove(position);
                        mAdapter.notifyItemChanged(position);
                        if (mAdapter.getVideoInfoBeanList().size() == 0) {
                              mPresenter.pullToRefresh();
                        }
                        AppUtils.showSnackbar((MainActivity) mContext, getString(R.string.text_file_delete_successful), false);
                  } else {
                        AppUtils.showSnackbar((MainActivity) mContext, getString(R.string.text_file_delete_faile), false);
                  }

                  dialog.dismiss();
            });
            builder.setNegativeButton(R.string.text_cancle, (dialog, which) -> dialog.dismiss());
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

      @Override
      public void onClick(View v) {
            mPresenter.pullToRefresh();
      }
}
