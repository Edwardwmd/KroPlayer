package com.wmd.kroplayer.mvp.presenter;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.wmd.kroplayer.R;
import com.wmd.kroplayer.adapter.PullToRefreshAdapter;
import com.wmd.kroplayer.base.BasePresenter;
import com.wmd.kroplayer.bean.VideoInfoBean;
import com.wmd.kroplayer.di.scope.FragmentScope;
import com.wmd.kroplayer.mvp.contract.MainVideoContract;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
      @Inject
      AlertDialog.Builder builder;
      private int preEndIndex;
      private MainVideoContract.View view;
      private MainVideoContract.Model model;
      private AlertDialog alertDialog;
      private final String[] selectItem = new String[]{"选择", "删除"};
      private AlertDialog deleteAlertDialog;


      @Inject
      public MainVideoPresenter(MainVideoContract.View view, MainVideoContract.Model model) {
            super(view, model);
            this.view = view;
            this.model = model;
      }


      @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
      void onCreate() {
            pullToRefresh();
      }

      @SuppressLint("CheckResult")
      public void pullToRefresh() {
            addCompositeDisposable(model
                    .getVideoInfos(view.getActivity())
                    .delay(3, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(disposable -> {

                          view.showLoading();//显示下拉刷新的进度条
                    }).subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> {

                          view.hideLoading();//隐藏下拉刷新的进度条
                    }).subscribe(videoInfoBeanList -> {
                          if (true) videoInfoBeans.clear();//如果是下拉刷新则清空列表
                          preEndIndex = videoInfoBeans.size();//更新之前列表总长度,用于确定加载更多的起始位置
                          if (videoInfoBeanList != null && videoInfoBeanList.size() > 0) {
                                videoInfoBeans.addAll(videoInfoBeanList);
                          } else {
                                mView.setLoadingEmptyView();
                          }

                          if (true)
                                mAdapter.notifyDataSetChanged();
                          else
                                mAdapter.notifyItemRangeInserted(preEndIndex, videoInfoBeanList.size());
                    }));

      }

      /**
       * 长安点击时的相关逻辑
       *
       * @param position 位置
       */
      public void startMaterialDialog(int position) {
            builder.setCancelable(true);
            builder.setItems(selectItem, (dialog, which) -> {
                  switch (selectItem[which]) {
                        case "选择":
                              mView.showSelectLogic(position);
                              break;
                        case "删除":
                              AlertDialog.Builder deleteAlertDialogBuilder = new AlertDialog.Builder(mView.getActivity());
                              deleteAlertDialogBuilder.setMessage(R.string.text_dialog_delete_video_message);
                              deleteAlertDialogBuilder.setPositiveButton(R.string.text_sure, (deleteDialog, deleteWhich) -> {
                                    mView.showDeleteLogic(position);
                                    deleteDialog.dismiss();
                              });
                              deleteAlertDialogBuilder.setNegativeButton(R.string.text_cancle, (deleteDialog, deleteWhich) -> deleteDialog.dismiss());
                              deleteAlertDialog = deleteAlertDialogBuilder.create();
                              deleteAlertDialog.show();
                              break;
                  }

            });
            alertDialog = builder.create();
            alertDialog.show();
      }

      @Override
      public void onDestory() {
            if (alertDialog != null && alertDialog.isShowing()) {
                  alertDialog.dismiss();
            }
            if (deleteAlertDialog != null && deleteAlertDialog.isShowing()) {
                  deleteAlertDialog.dismiss();
            }
            super.onDestory();

      }
}
