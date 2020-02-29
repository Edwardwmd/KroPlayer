package com.wmd.kroplayer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.bean.VideoInfoBean;
import com.wmd.kroplayer.utils.FileSizeUtil;
import com.wmd.kroplayer.utils.TimeUtils;

import java.security.MessageDigest;
import java.util.List;

import static com.bumptech.glide.load.resource.bitmap.VideoDecoder.FRAME_OPTION;


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
                  if (videoInfoBean.getVideoDuration() != 0) {
                        loadVideoScreenshot(context, videoInfoBean.getThumbPath(), mVH.getView(R.id.iv_video_thum), 2000000);
                  } else {
                        ((ImageView) mVH.getView(R.id.iv_video_thum)).setImageResource(R.drawable.ic_videoplaceholder);
                  }

                  ((TextView) mVH.getView(R.id.tv_videosize)).setText(FileSizeUtil.FormetFileSize(videoInfoBean.getVideoSize()));
                  ((TextView) mVH.getView(R.id.tv_videodate)).setText(TimeUtils.getFormatedDateTime("yyyy-MM-dd", videoInfoBean.getTime()));
                  ((TextView) mVH.getView(R.id.tv_videoname)).setText(videoInfoBean.getVideoName());
                  ((TextView) mVH.getView(R.id.tv_videoduration)).setText(TimeUtils.millisecondToHours(videoInfoBean.getVideoDuration()));
            }


      }

      public List<VideoInfoBean> getVideoInfoBeanList() {
            return videoInfoBeanList;
      }

      /**
       * @param context         上下文
       * @param localPath       本地视频路径
       * @param imageView       设置图片控件
       * @param frameTimeMicros 获取某一时间帧
       */
      @SuppressLint("CheckResult")
      private void loadVideoScreenshot(final Context context, String localPath, ImageView imageView, long frameTimeMicros) {
            RequestOptions requestOptions = RequestOptions.frameOf(frameTimeMicros);
            requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST);
            requestOptions.transform(new BitmapTransformation() {
                  @Override
                  protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                        return toTransform;
                  }

                  @Override
                  public void updateDiskCacheKey(MessageDigest messageDigest) {
                        try {
                              messageDigest.update((context.getPackageName() + "RotateTransform").getBytes("utf-8"));
                        } catch (Exception e) {
                              e.printStackTrace();
                        }
                  }
            });
            Glide.with(context).load(localPath).placeholder(R.drawable.ic_videoplaceholder_noload).fitCenter().apply(requestOptions).into(imageView);
      }

}
