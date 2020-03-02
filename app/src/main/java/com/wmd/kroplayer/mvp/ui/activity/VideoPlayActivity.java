package com.wmd.kroplayer.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.wmd.kroplayer.App;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.base.BaseKroGSYVideoActivity;
import com.wmd.kroplayer.di.scope.ActivityScope;
import com.wmd.kroplayer.mvp.ui.view.KroGsyVideoPlayer;
import com.wmd.kroplayer.utils.AppUtils;

import java.security.MessageDigest;

import butterknife.BindView;

import static com.bumptech.glide.load.resource.bitmap.VideoDecoder.FRAME_OPTION;
import static com.wmd.kroplayer.utils.ContractUtils.VIDEO_PLAYE_NAME;
import static com.wmd.kroplayer.utils.ContractUtils.VIDEO_PLAYE_PATH;
import static com.wmd.kroplayer.utils.ContractUtils.VIDEO_PLAYE_THUM;


/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2121
 * Version: 1.0.0
 * Desc:    VideoPlayActivity
 */
@ActivityScope
public class VideoPlayActivity extends BaseKroGSYVideoActivity<StandardGSYVideoPlayer> {

      @BindView(R.id.kro_gsyplayer)
      KroGsyVideoPlayer kroGsyplayer;
      private String path;
      private String thumPath;
      private String videoName;
      private ImageView thum;


      @Override
      public StandardGSYVideoPlayer getGSYVideoPlayer() {
            return kroGsyplayer;
      }

      @Override
      public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {

            return new GSYVideoOptionBuilder()
                    .setThumbImageView(thum)
                    .setUrl(path)
                    .setCacheWithPlay(true)
                    .setVideoTitle(videoName)
                    .setIsTouchWiget(true)
                    .setRotateViewAuto(false)
                    .setLockLand(true)
                    .setShowFullAnimation(true)
                    .setNeedLockFull(true)
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
            Intent intent = getIntent();
            if (intent != null) {
                  Bundle extras = intent.getExtras();
                  path = extras.getString(VIDEO_PLAYE_PATH);
                  thumPath = extras.getString(VIDEO_PLAYE_THUM);
                  videoName = extras.getString(VIDEO_PLAYE_NAME);
            }
            loadCover();
            initVideoBuilderMode();
      }

      private void loadCover() {
            thum = new ImageView(this);
            //SDK>=21(Android 5.0)时实现元素共享转场
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                  thum.setTransitionName(String.valueOf(R.string.text_transition_share_image));
            }
            thum.setScaleType(ImageView.ScaleType.CENTER_CROP);
            AppUtils.loadVideoScreenshot(this, thumPath, thum, 350000000);
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
}
