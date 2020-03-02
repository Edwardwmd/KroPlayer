package com.wmd.kroplayer.base;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.InflateException;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.logger.Logger;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.trello.rxlifecycle3.components.RxActivity;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.wmd.kroplayer.utils.AppUtils;
import com.wmd.kroplayer.utils.JumpUtils;


import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/27
 * Version: 1.0.0
 * Desc:    BaseKroGSYVideoActivity
 */
public abstract class BaseKroGSYVideoActivity<T extends GSYBaseVideoPlayer> extends RxAppCompatActivity implements VideoAllCallBack, IActivity {

      protected boolean isPlay;

      protected boolean isPause;

      protected Unbinder unbinder;

      protected OrientationUtils orientationUtils;

      @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {
            // 去掉窗口标题
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            // 隐藏状态栏,全屏显示
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //实现沉浸式状态栏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                  getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                          WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            super.onCreate(savedInstanceState);
            try {
                  int layoutRes = initView(savedInstanceState);
                  if (layoutRes != 0) {
                        setContentView(layoutRes);
                        unbinder = ButterKnife.bind(this);

                  }

            } catch (Exception e) {
                  if (e instanceof InflateException) throw e;
                  e.printStackTrace();
            }
            initData(savedInstanceState);

      }

      /**
       * 选择普通模式
       */
      public void initVideo() {
            //外部辅助的旋转，帮助全屏
            orientationUtils = new OrientationUtils(this, getGSYVideoPlayer());
            //初始化不打开外部的旋转
            orientationUtils.setEnable(false);
            if (getGSYVideoPlayer().getFullscreenButton() != null) {
                  getGSYVideoPlayer().getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                              showFull();
                              clickForFullScreen();
                        }
                  });
            }
      }

      /**
       * 选择builder模式
       */
      public void initVideoBuilderMode() {
            initVideo();
            getGSYVideoOptionBuilder().
                    setVideoAllCallBack(this)
                    .build(getGSYVideoPlayer());
      }

      public void showFull() {
            if (orientationUtils.getIsLand() != 1) {
                  //直接横屏
                  orientationUtils.resolveByClick();
            }
            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            getGSYVideoPlayer().startWindowFullscreen(this, hideActionBarWhenFull(), hideStatusBarWhenFull());

      }

      @Override
      public void onBackPressed() {
            if (orientationUtils != null) {
                  orientationUtils.backToProtVideo();
                  Logger.e("orientationUtils----->onBackPressed");
            }
            if (GSYVideoManager.backFromWindowFull(this)) {
                  Logger.e("全屏开!!!!!!!!!!!!");
                  return;
            }
            super.onBackPressed();
      }


      @Override
      protected void onPause() {
            super.onPause();
            getGSYVideoPlayer().getCurrentPlayer().onVideoPause();
            if (orientationUtils != null) {
                  orientationUtils.setIsPause(true);
            }
            isPause = true;
      }

      @Override
      protected void onResume() {
            super.onResume();
            getGSYVideoPlayer().getCurrentPlayer().onVideoResume();
            if (orientationUtils != null) {
                  orientationUtils.setIsPause(false);
            }
            isPause = false;
      }

      @Override
      protected void onDestroy() {
            super.onDestroy();
            if (isPlay) {
                  getGSYVideoPlayer().getCurrentPlayer().release();
            }
            if (orientationUtils != null)
                  orientationUtils.releaseListener();
            if (unbinder != null && unbinder != Unbinder.EMPTY)
                  unbinder.unbind();
            unbinder = null;
      }

      @Override
      public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            //如果旋转了就全屏
            if (isPlay && !isPause)
                  getGSYVideoPlayer().onConfigurationChanged(this, newConfig, orientationUtils, hideActionBarWhenFull(), hideStatusBarWhenFull());
      }


      @Override
      public void onStartPrepared(String url, Object... objects) {

      }

      @Override
      public void onPrepared(String url, Object... objects) {

            if (orientationUtils == null) {
                  throw new NullPointerException("initVideo() or initVideoBuilderMode() first");
            }
            //开始播放了才能旋转和全屏
            orientationUtils.setEnable(getDetailOrientationRotateAuto() && !isAutoFullWithSize());
            isPlay = true;
      }

      @Override
      public void onClickStartIcon(String url, Object... objects) {

      }

      @Override
      public void onClickStartError(String url, Object... objects) {

      }

      @Override
      public void onClickStop(String url, Object... objects) {

      }

      @Override
      public void onClickStopFullscreen(String url, Object... objects) {

      }

      @Override
      public void onClickResume(String url, Object... objects) {

      }

      @Override
      public void onClickResumeFullscreen(String url, Object... objects) {

      }

      @Override
      public void onClickSeekbar(String url, Object... objects) {

      }

      @Override
      public void onClickSeekbarFullscreen(String url, Object... objects) {

      }

      @Override
      public void onAutoComplete(String url, Object... objects) {
            if (getGSYVideoPlayer().isIfCurrentIsFullscreen()) {
                  //播放完成后退出全屏
                  getGSYVideoPlayer().onBackFullscreen();
                  getGSYVideoPlayer().setIfCurrentIsFullscreen(false);
            }
      }

      @Override
      public void onEnterFullscreen(String url, Object... objects) {

      }

      @Override
      public void onQuitFullscreen(String url, Object... objects) {
            if (orientationUtils != null) {
                  orientationUtils.backToProtVideo();
            }
      }

      @Override
      public void onQuitSmallWidget(String url, Object... objects) {

      }

      @Override
      public void onEnterSmallWidget(String url, Object... objects) {

      }

      @Override
      public void onTouchScreenSeekVolume(String url, Object... objects) {

      }

      @Override
      public void onTouchScreenSeekPosition(String url, Object... objects) {

      }

      @Override
      public void onTouchScreenSeekLight(String url, Object... objects) {

      }

      @Override
      public void onPlayError(String url, Object... objects) {

      }

      @Override
      public void onClickStartThumb(String url, Object... objects) {

      }

      @Override
      public void onClickBlank(String url, Object... objects) {

      }

      @Override
      public void onClickBlankFullscreen(String url, Object... objects) {

      }

      public boolean hideActionBarWhenFull() {
            return true;
      }

      public boolean hideStatusBarWhenFull() {
            return true;
      }


      /**
       * 播放控件
       */
      public abstract T getGSYVideoPlayer();

      /**
       * 配置播放器
       */
      public abstract GSYVideoOptionBuilder getGSYVideoOptionBuilder();

      /**
       * 点击了全屏
       */
      public abstract void clickForFullScreen();

      /**
       * 是否启动旋转横屏，true表示启动
       */
      public abstract boolean getDetailOrientationRotateAuto();

      /**
       * 是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏，注意，这时候默认旋转无效
       */
      public boolean isAutoFullWithSize() {
            return false;
      }

}
