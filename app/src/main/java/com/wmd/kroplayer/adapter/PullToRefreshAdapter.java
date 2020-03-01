package com.wmd.kroplayer.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.bean.VideoInfoBean;
import com.wmd.kroplayer.mvp.ui.activity.MainActivity;
import com.wmd.kroplayer.mvp.ui.activity.VideoPlayActivity;
import com.wmd.kroplayer.mvp.ui.fragment.MainVideoFragment;
import com.wmd.kroplayer.utils.AppUtils;
import com.wmd.kroplayer.utils.FileSizeUtil;
import com.wmd.kroplayer.utils.TimeUtils;

import java.security.MessageDigest;
import java.util.List;

import static com.bumptech.glide.load.resource.bitmap.VideoDecoder.FRAME_OPTION;
import static com.wmd.kroplayer.utils.ContractUtils.VIDEO_PLAYE_NAME;
import static com.wmd.kroplayer.utils.ContractUtils.VIDEO_PLAYE_PATH;
import static com.wmd.kroplayer.utils.ContractUtils.VIDEO_PLAYE_THUM;


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

      public PullToRefreshAdapter(Context context, List<VideoInfoBean> videoInfoBeanList) {
            super(R.layout.item_mainvideo, videoInfoBeanList);
            this.videoInfoBeanList = videoInfoBeanList;
            this.context = context;
      }

      @Override
      protected void convert(@NonNull BaseViewHolder mVH, VideoInfoBean videoInfoBean) {

            if (videoInfoBean != null) {
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
}
