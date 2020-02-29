package com.wmd.kroplayer.mvp.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.wmd.kroplayer.R;
import com.wmd.kroplayer.base.BaseKroGSYVideoActivity;
import com.wmd.kroplayer.mvp.ui.view.KroGsyVideoPlayer;

import butterknife.BindView;

import static com.wmd.kroplayer.utils.ContractUtils.VIDEO_PLAYE_PATH;

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
      private String path;


      @Override
      public StandardGSYVideoPlayer getGSYVideoPlayer() {
            return kroGsyplayer;
      }

      @Override
      public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
            return new GSYVideoOptionBuilder()
                    .setUrl(path)
                    .setOverrideExtension("avi")
                    .setCacheWithPlay(true)
                    .setVideoTitle("")
                    .setIsTouchWiget(true)
                    .setRotateViewAuto(false)
                    .setLockLand(false)
                    .setShowFullAnimation(true)//打开动画
                    .setNeedLockFull(true)
                    .setSeekRatio(0.5f);
      }

      @Override
      public void clickForFullScreen() {

      }

      @Override
      public boolean getDetailOrientationRotateAuto() {
            return false;
      }

      @Override
      protected int getLayoutRes() {
            return R.layout.activity_videoplay;
      }

      @Override
      protected void initData(@Nullable Bundle savedInstanceState) {
            Intent intent = getIntent();
            if (intent != null)
                  path = intent.getStringExtra(VIDEO_PLAYE_PATH);
            initVideoBuilderMode();
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
      protected void onNewIntent(Intent intent) {
            super.onNewIntent(intent);
            if (intent != null && path == null) {
                  path = intent.getStringExtra(VIDEO_PLAYE_PATH);
            }
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
