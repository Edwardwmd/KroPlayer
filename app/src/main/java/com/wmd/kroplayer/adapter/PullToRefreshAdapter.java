package com.wmd.kroplayer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.bean.VideoInfoBean;
import com.wmd.kroplayer.utils.AppUtils;
import com.wmd.kroplayer.utils.FileSizeUtil;
import com.wmd.kroplayer.utils.TimeUtils;

import java.util.List;


/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2220
 * Version: 1.0.0
 * Desc:    PullToRefreshAdapter
 */
public class PullToRefreshAdapter extends BaseQuickAdapter<VideoInfoBean, BaseViewHolder> {
      private List<VideoInfoBean> videoInfoBeanList;
      private Context context;

      private boolean isLongClick = false;
      private ImageView materialCheckBox;

      public PullToRefreshAdapter(Context context, List<VideoInfoBean> videoInfoBeanList) {
            super(R.layout.item_mainvideo, videoInfoBeanList);
            this.videoInfoBeanList = videoInfoBeanList;
            this.context = context;
      }

      @Override
      protected void convert(@NonNull BaseViewHolder mVH, VideoInfoBean videoInfoBean) {
            materialCheckBox = mVH.getView(R.id.img_item_checkbox);
            if (isLongClick) {
                  materialCheckBox.setVisibility(View.VISIBLE);
            } else {
                  materialCheckBox.setVisibility(View.GONE);
            }
            if (videoInfoBean != null) {
                  if (videoInfoBean.isSelect()) {
                        changeBackgroundColorCheck(mVH);
                        materialCheckBox.setImageResource(R.drawable.ic_checkbox_round_checked);
                  } else {
                        changeBackgroundColorNomal(mVH);
                        materialCheckBox.setImageResource(R.drawable.ic_checkbox_round_nomal);
                  }

                  AppUtils.loadVideoScreenshot(context, videoInfoBean.getThumbPath(), mVH.getView(R.id.iv_video_thum), 350000000);
                  ((TextView) mVH.getView(R.id.tv_videosize)).setText(FileSizeUtil.FormetFileSize(videoInfoBean.getVideoSize()));
                  ((TextView) mVH.getView(R.id.tv_videodate)).setText(TimeUtils.getFormatedDateTime("yyyy-MM-dd", videoInfoBean.getTime()));
                  ((TextView) mVH.getView(R.id.tv_videoname)).setText(videoInfoBean.getVideoName());
                  ((TextView) mVH.getView(R.id.tv_videoduration)).setText(TimeUtils.millisecondToHours(videoInfoBean.getVideoDuration()));
            }


      }

      public List<VideoInfoBean> getVideoInfoBeanList() {
            return videoInfoBeanList;
      }

      public void isLongClick(boolean isLongClick) {
            this.isLongClick = isLongClick;
            notifyDataSetChanged();
      }

      public boolean isLongClick() {
            return isLongClick;
      }

      private void changeBackgroundColorCheck(@NonNull BaseViewHolder mVH) {
            mVH.getView(R.id.cl_continar).setBackgroundColor(Color.parseColor("#C1CDCD"));
            ((TextView) mVH.getView(R.id.tv_videosize)).setTextColor(Color.parseColor("#FFFFFF"));
            ((TextView) mVH.getView(R.id.tv_videodate)).setTextColor(Color.parseColor("#FFFFFF"));
            ((TextView) mVH.getView(R.id.tv_videoname)).setTextColor(Color.parseColor("#FFFFFF"));
            ((TextView) mVH.getView(R.id.tv_videoduration)).setTextColor(Color.parseColor("#FFFFFF"));
            ((TextView) mVH.getView(R.id.tv_durationtitle)).setTextColor(Color.parseColor("#FFFFFF"));
            (mVH.getView(R.id.vi_verticalline)).setBackgroundColor(Color.parseColor("#FFFFFF"));
      }

      private void changeBackgroundColorNomal(@NonNull BaseViewHolder mVH) {
            mVH.getView(R.id.cl_continar).setBackgroundColor(Color.parseColor("#FFFFFF"));
            ((TextView) mVH.getView(R.id.tv_videosize)).setTextColor(Color.parseColor("#6E6E6E"));
            ((TextView) mVH.getView(R.id.tv_videodate)).setTextColor(Color.parseColor("#6E6E6E"));
            ((TextView) mVH.getView(R.id.tv_videoname)).setTextColor(Color.parseColor("#6E6E6E"));
            ((TextView) mVH.getView(R.id.tv_videoduration)).setTextColor(Color.parseColor("#6E6E6E"));
            ((TextView) mVH.getView(R.id.tv_durationtitle)).setTextColor(Color.parseColor("#6E6E6E"));
            (mVH.getView(R.id.vi_verticalline)).setBackgroundColor(Color.parseColor("#6E6E6E"));
      }



}
