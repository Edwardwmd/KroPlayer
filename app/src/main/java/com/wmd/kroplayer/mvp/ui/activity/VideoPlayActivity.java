package com.wmd.kroplayer.mvp.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.base.BaseKroGSYVideoActivity;
import com.wmd.kroplayer.bean.VideoInfoBean;
import com.wmd.kroplayer.bean.VideoPlayHistoryBean;
import com.wmd.kroplayer.mvp.ui.view.KroGsyVideoPlayer;
import com.wmd.kroplayer.utils.AppUtils;
import com.wmd.kroplayer.utils.FileSizeUtil;
import com.wmd.kroplayer.utils.FileUtils;
import com.wmd.kroplayer.utils.TimeUtils;

import butterknife.BindView;

import static com.wmd.kroplayer.utils.ContractUtils.LOCAL_VIDEO_INFO;


/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2121
 * Version: 1.0.0
 * Desc:    VideoPlayActivity
 */
public class VideoPlayActivity extends BaseKroGSYVideoActivity<StandardGSYVideoPlayer> {

      @BindView(R.id.kro_gsyplayer)
      KroGsyVideoPlayer kroGsyplayer;
      @BindView(R.id.tv_play_video_name)
      TextView tvPlayVideoName;
      @BindView(R.id.tv_video_size)
      TextView tvVideoSize;
      @BindView(R.id.tv_create_video_date)
      TextView tvCreateVideoDate;
      @BindView(R.id.tv_video_formality)
      TextView tvVideoFormality;
      @BindView(R.id.tv_video_highwidth)
      TextView tvVideoHighwidth;
      @BindView(R.id.cv_video_info)
      CardView cvVideoInfo;
      private ImageView thum;
      private VideoInfoBean videoInfoBean;

      private VideoPlayHistoryBean videoPlayHistoryBean;



      @Override
      public StandardGSYVideoPlayer getGSYVideoPlayer() {
            return kroGsyplayer;
      }

      @Override
      public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {

            return new GSYVideoOptionBuilder()
                    .setThumbImageView(thum)
                    .setUrl(videoInfoBean.getPath())
                    .setCacheWithPlay(true)
                    .setVideoTitle(videoInfoBean.getVideoName())
                    .setIsTouchWiget(true)
                    .setRotateViewAuto(false)
                    .setLockLand(true)
                    .setShowFullAnimation(true)
                    .setNeedLockFull(true)
                    .setDismissControlTime(4000)
                    .setSeekRatio(10.0f);
      }

      @Override
      public void clickForFullScreen() {

      }

      @Override
      public boolean getDetailOrientationRotateAuto() {
            return false;
      }


      @Override
      public int initView(@Nullable Bundle savedInstanceState) {
            return R.layout.activity_videoplay;
      }

      @Override
      public void initData(@Nullable Bundle savedInstanceState) {
            loadView();
            loadCover();
            initVideoBuilderMode();
      }

      private void loadView() {
            Intent intent = getIntent();
            if (intent != null) {
                  videoInfoBean = intent.getParcelableExtra(LOCAL_VIDEO_INFO);
            }

            tvPlayVideoName.setText(videoInfoBean.getVideoName());
            tvVideoHighwidth.setText(videoInfoBean.getVideoWidth()+"X"+videoInfoBean.getVideoHigh());
            tvVideoSize.setText(FileSizeUtil.FormetFileSize(videoInfoBean.getVideoSize()));
            tvCreateVideoDate.setText(TimeUtils.getFormatedDateTime("yyyy-MM-dd", videoInfoBean.getTime()));
            tvVideoFormality.setText(FileUtils.checkVideoType(videoInfoBean.getPath()));
            videoPlayHistoryBean=new VideoPlayHistoryBean();
      }

      private void loadCover() {
            thum = new ImageView(this);
            //SDK>=21(Android 5.0)时实现元素共享转场
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                  thum.setTransitionName(getString(R.string.text_transition_share_image));
            }
            thum.setScaleType(ImageView.ScaleType.CENTER_CROP);
            AppUtils.loadVideoScreenshot(this, videoInfoBean.getThumbPath(), thum, 350000000);
      }


      @Override
      public void initVideo() {
            super.initVideo();
            //重载后实现点击，不横屏
            if (getGSYVideoPlayer().getFullscreenButton() != null) {
                  getGSYVideoPlayer().getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                              //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                              getGSYVideoPlayer().startWindowFullscreen(VideoPlayActivity.this, true, true);
                        }
                  });
            }
      }

      @Override
      public void onClickStop(String url, Object... objects) {
            super.onClickStop(url, objects);
            //保存播放进度
      }

      @Override
      public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            orientationUtils.setEnable(false);
      }

      //重载后不做任何事情，实现竖屏全屏
      @Override
      public void onQuitFullscreen(String url, Object... objects) {
            super.onQuitFullscreen(url, objects);
      }

      @Override
      protected void onDestroy() {
            super.onDestroy();
            if (kroGsyplayer != null) {
                  kroGsyplayer.release();
                  kroGsyplayer = null;
            }

      }


      @Override
      public boolean isBaseOnWidth() {
            return false;
      }

      @Override
      public float getSizeInDp() {
            return 720;
      }
}
