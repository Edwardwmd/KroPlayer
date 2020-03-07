package com.wmd.kroplayer.mvp.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.wmd.kroplayer.App;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.adapter.PullToRefreshAdapter;
import com.wmd.kroplayer.base.BaseFragment;
import com.wmd.kroplayer.bean.VideoInfoBean;
import com.wmd.kroplayer.di.component.DaggerMainVideoComponent;
import com.wmd.kroplayer.mvp.contract.MainVideoContract;
import com.wmd.kroplayer.mvp.presenter.MainVideoPresenter;
import com.wmd.kroplayer.mvp.ui.activity.MainActivity;
import com.wmd.kroplayer.utils.AppUtils;
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
      @BindView(R.id.img_select_all)
      ImageView imgSelectAll;
      @BindView(R.id.tv_finish)
      TextView tvFinish;
      @BindView(R.id.tv_delete)
      TextView tvDelete;
      @BindView(R.id.delete_select_layout)
      ConstraintLayout deleteSelectLayout;
      @BindView(R.id.tv_select_num)
      TextView tvSelectNum;

      private int index = 0;

      //防止再次长安点击弹出dialog
      private int longClickTimes = 0;
      //数据空布局
      private View notDataView;
      private boolean isSelectAll = false;
      private boolean isOnLongClick = false;

      private static MainVideoFragment fragment;
      private AlertDialog deleteMutilDialog;

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
            tvFinish.setOnClickListener(this);
            imgSelectAll.setOnClickListener(this);
            tvDelete.setOnClickListener(this);
      }

      @Override
      public void onClick(View v) {
            switch (v.getId()) {
                  case R.id.ll_empty_view:
                        mAdapter.isLongClick(false);
                        mPresenter.pullToRefresh();
                        break;
                  case R.id.img_select_all:
                        selectAllMain();
                        break;
                  case R.id.tv_finish:
                        finishSelection();
                        break;
                  case R.id.tv_delete:
                        deleteVideo();
                        break;
            }
      }

      @SuppressLint("SetTextI18n")
      @Override
      public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            VideoInfoBean videoInfoBean = mAdapter.getVideoInfoBeanList().get(position);
            if (isOnLongClick) {
                  boolean isSelect = videoInfoBean.isSelect();
                  if (!isSelect) {
                        index++;
                        videoInfoBean.setSelect(true);
                        if (index == mAdapter.getVideoInfoBeanList().size()) {
                              isSelectAll = true;
                              imgSelectAll.setImageResource(R.drawable.ic_select_all_check);
                        }

                  } else {
                        videoInfoBean.setSelect(false);
                        index--;
                        isSelectAll = false;
                        imgSelectAll.setImageResource(R.drawable.ic_select_all_nomal);
                  }
                  tvSelectNum.setText(getString(R.string.text_selected_item) + index);
                  mAdapter.notifyItemChanged(position);
            } else {
                  JumpUtils.JumpToVideoPlay((MainActivity) mContext,
                          view.findViewById(R.id.iv_video_thum),
                          videoInfoBean,
                          StringsUtils.getString(R.string.text_transition_share_image));
            }

      }

      @Override
      public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
            if (longClickTimes >= 1) {
                  return false;
            } else {
                  mPresenter.startMaterialDialog(position);
                  longClickTimes++;
                  return true;
            }
      }

      @Override
      public void onRefresh() {
            mPresenter.pullToRefresh();
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

      @SuppressLint("SetTextI18n")
      @Override
      public void showSelectLogic(int position) {
            if (deleteSelectLayout.getVisibility() == View.GONE)
                  deleteSelectLayout.setVisibility(View.VISIBLE);
            index = 1;
            isOnLongClick = true;
            mAdapter.isLongClick(true);
            swipeLayout.setEnabled(false);
            mAdapter.getVideoInfoBeanList().get(position).setSelect(true);
            tvSelectNum.setText(getString(R.string.text_selected_item) + index);

      }

      @Override
      public void deleteLogic(boolean isDelete, int position) {
            if (isDelete) {
                  mAdapter.remove(position);
                  mAdapter.notifyItemChanged(position);//局部更新Position
                  if (mAdapter.getVideoInfoBeanList().size() == 0) {
                        mPresenter.pullToRefresh();
                  }
                  longClickTimes = 0;
                  AppUtils.showSnackbar((MainActivity) mContext, getString(R.string.text_file_delete_successful), false);
            } else {
                  AppUtils.showSnackbar((MainActivity) mContext, getString(R.string.text_file_delete_faile), false);
            }
      }

      @Override
      public void deleteLogic(boolean isDelete, VideoInfoBean videoInfoBean) {
            if (isDelete) {
                  mAdapter.remove(videoInfoBean);
                  if (mAdapter.getVideoInfoBeanList().size() == 0) {
                        deleteSelectLayout.setVisibility(View.GONE);
                        swipeLayout.setEnabled(true);
                        mPresenter.pullToRefresh();
                  }
                  AppUtils.showSnackbar((MainActivity) mContext, getString(R.string.text_file_delete_successful), false);
            } else {
                  AppUtils.showSnackbar((MainActivity) mContext, getString(R.string.text_file_delete_faile), false);
            }
      }


      /**
       * 全选和反选逻辑
       */
      @SuppressLint("SetTextI18n")
      private void selectAllMain() {
            if (mAdapter == null) return;
            if (!isSelectAll) {
                  for (int i = 0, j = mAdapter.getVideoInfoBeanList().size(); i < j; i++) {
                        mAdapter.getVideoInfoBeanList().get(i).setSelect(true);
                  }
                  index = mAdapter.getVideoInfoBeanList().size();
                  imgSelectAll.setImageResource(R.drawable.ic_select_all_check);
                  isSelectAll = true;
            } else {
                  for (int i = 0, j = mAdapter.getVideoInfoBeanList().size(); i < j; i++) {
                        mAdapter.getVideoInfoBeanList().get(i).setSelect(false);
                  }
                  index = 0;
                  imgSelectAll.setImageResource(R.drawable.ic_select_all_nomal);
                  isSelectAll = false;
            }
            mAdapter.notifyDataSetChanged();
            tvSelectNum.setText(getString(R.string.text_selected_item) + index);
      }

      /**
       * 删除多项数据逻辑
       */
      @SuppressLint("SetTextI18n")
      private void deleteVideo() {
            if (index == 0) {
                  return;
            }
            deleteMutilDialog = new AlertDialog.Builder(mContext).create();
            deleteMutilDialog.show();
            if (deleteMutilDialog.getWindow() == null) return;
            deleteMutilDialog.getWindow().setContentView(R.layout.pop_user);//设置弹出框加载的布局
            TextView msg = deleteMutilDialog.findViewById(R.id.tv_msg);
            Button cancle = deleteMutilDialog.findViewById(R.id.btn_cancle);
            Button sure = deleteMutilDialog.findViewById(R.id.btn_sure);
            if (msg == null || cancle == null || sure == null) return;
            if (index == 1) {
                  msg.setText(R.string.text_delete_unrecoverable);
            } else {
                  msg.setText(getString(R.string.text_delete_unrecoverable_pice) + index + getString(R.string.text_delete_item));
            }
            cancle.setOnClickListener(v -> deleteMutilDialog.dismiss());
            sure.setOnClickListener(v -> {
                          for (int i = mAdapter.getVideoInfoBeanList().size(), j = 0; i > j; i--) {
                                VideoInfoBean videoInfoBean = mAdapter.getVideoInfoBeanList().get(i - 1);
                                if (videoInfoBean.isSelect()) {
                                      mPresenter.deleteVideo(videoInfoBean);
                                      index--;
                                }
                          }
                          finishSelection();
                          deleteMutilDialog.dismiss();
                    }
            );
      }

      /**
       * 完成删除|参数复原逻辑
       */
      private void finishSelection() {
            for (int i = mAdapter.getVideoInfoBeanList().size(), j = 0; i > j; i--) {
                  mAdapter.getVideoInfoBeanList().get(i - 1).setSelect(false);
                  mAdapter.notifyDataSetChanged();
            }
            index = 0;
            longClickTimes = 0;
            isOnLongClick = false;
            mAdapter.isLongClick(false);
            deleteSelectLayout.setVisibility(View.GONE);
            swipeLayout.setEnabled(true);
            isSelectAll = false;
            imgSelectAll.setImageResource(R.drawable.ic_select_all_nomal);
      }


      @Override
      public void onDestroy() {
            if (deleteMutilDialog != null && deleteMutilDialog.isShowing()) {
                  deleteMutilDialog.dismiss();
            }
            super.onDestroy();
            if (mAdapter != null)
                  mAdapter.weakRecyclerView.clear();
            mAdapter = null;
            layoutManager = null;
      }
}
