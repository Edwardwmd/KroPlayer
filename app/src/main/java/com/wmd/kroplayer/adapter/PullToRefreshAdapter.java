package com.wmd.kroplayer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.bean.VideoInfoBean;

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
//      private List<VideoInfoBean> videoInfoBeanList;

      public PullToRefreshAdapter(List<VideoInfoBean> videoInfoBeanList) {

            super(R.layout.item_mainvideo, videoInfoBeanList);
//            this.videoInfoBeanList = videoInfoBeanList;
            setAnimationEnable(true);
            setAnimationWithDefault(AnimationType.ScaleIn);
      }

      @Override
      protected void convert(@NonNull BaseViewHolder mVH, VideoInfoBean videoInfoBean) {

            if (videoInfoBean != null) {
//            ((ImageView)mVH.getView(R.id.iv_video_thum)).setImageResource();
                  ((TextView) mVH.getView(R.id.tv_videosize)).setText(String.valueOf(videoInfoBean.getVideoSize()));
                  ((TextView) mVH.getView(R.id.tv_videodate)).setText(videoInfoBean.getTime());
                  ((TextView) mVH.getView(R.id.tv_videoname)).setText(videoInfoBean.getVideoName());
                  ((TextView) mVH.getView(R.id.tv_videoduration)).setText(String.valueOf(videoInfoBean.getVideoDuration()));

            } else {

            }


      }

}
