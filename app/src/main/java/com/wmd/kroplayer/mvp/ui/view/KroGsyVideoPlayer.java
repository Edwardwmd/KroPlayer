package com.wmd.kroplayer.mvp.ui.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.wmd.kroplayer.R;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2811
 * Version: 1.0.0
 * Desc:    ProGsyVideoPlayer
 */
public class KroGsyVideoPlayer extends StandardGSYVideoPlayer {

      //设置比例
      protected TextView mMoreScale;
      //设置翻转
      protected ImageView mChangeRotate;
      //设置镜像
      protected ImageView mChangeTransform;
      //设置倍速播放Text
      protected TextView mPlaySpeedTims;
      //设置增加倍速
      protected TextView mPlayTimesUp;
      //设置递减倍速
      protected TextView mPlayTimesDown;

      protected ConstraintLayout mPlaytimesLayout;
      //记住切换数据源类型
      private int mType = 0;

      private int mTransformSize = 0;
      //数据源
      private int mSourcePosition = 0;


      public KroGsyVideoPlayer(Context context, Boolean fullFlag) {
            super(context, fullFlag);
      }

      public KroGsyVideoPlayer(Context context) {
            super(context);
      }

      public KroGsyVideoPlayer(Context context, AttributeSet attrs) {
            super(context, attrs);
      }

      @Override
      protected void init(Context context) {
            super.init(context);
            initView();
      }

      private void initView() {
            mMoreScale = (TextView) findViewById(R.id.moreScale);
            mChangeRotate = (ImageView) findViewById(R.id.change_rotate);
            mChangeTransform = (ImageView) findViewById(R.id.change_transform);
            mPlaySpeedTims = (TextView) findViewById(R.id.tv_play_speed_times);
            mPlayTimesUp = (TextView) findViewById(R.id.tv_play_times_up);
            mPlayTimesDown = (TextView) findViewById(R.id.tv_play_times_down);
            mPlaytimesLayout = (ConstraintLayout) findViewById(R.id.play_times_layout);


            initplaySpeedTimes();
            mMoreScale.setOnClickListener(v -> {
                  if (!mHadPlay) {
                        return;
                  }
                  if (mType == 0) {
                        mType = 1;
                  } else if (mType == 1) {
                        mType = 2;
                  } else if (mType == 2) {
                        mType = 3;
                  } else if (mType == 3) {
                        mType = 4;
                  } else if (mType == 4) {
                        mType = 0;
                  }
                  resolveTypeUI();
            });

            mChangeRotate.setOnClickListener(v -> {
                  if (!mHadPlay) {
                        return;
                  }
                  if ((mTextureView.getRotation() - mRotate) == 270) {
                        mTextureView.setRotation(mRotate);
                        mTextureView.requestLayout();
                  } else {
                        mTextureView.setRotation(mTextureView.getRotation() + 90);
                        mTextureView.requestLayout();
                  }
            });

            mChangeTransform.setOnClickListener(v -> {
                  if (!mHadPlay) {
                        return;
                  }
                  if (mTransformSize == 0) {
                        mTransformSize = 1;
                  } else if (mTransformSize == 1) {
                        mTransformSize = 2;
                  } else if (mTransformSize == 2) {
                        mTransformSize = 0;
                  }
                  resolveTransform();
            });

            mPlayTimesUp.setOnClickListener(v -> {
                  if (!mHadPlay) {
                        return;
                  }
                  if (getSpeed() == 0.5f) {
                        setSpeed(0.75f);
                        mPlaySpeedTims.setText(R.string.text_play_speed_two);
                  } else if (getSpeed() == 0.75f) {
                        setSpeed(1.0f);
                        mPlaySpeedTims.setText(R.string.text_play_speed_three);
                  } else if (getSpeed() == 1.0f) {
                        setSpeed(1.25f);
                        mPlaySpeedTims.setText(R.string.text_play_speed_four);
                  } else if (getSpeed() == 1.25f) {
                        setSpeed(1.5f);
                        mPlaySpeedTims.setText(R.string.text_play_speed_five);
                  } else if (getSpeed() == 1.5f) {
                        setSpeed(1.75f);
                        mPlaySpeedTims.setText(R.string.text_play_speed_six);
                  } else if (getSpeed() == 1.75f) {
                        setSpeed(2.0f);
                        mPlaySpeedTims.setText(R.string.text_play_speed_seven);
                  }
            });

            mPlayTimesDown.setOnClickListener(v -> {
                  if (!mHadPlay) {
                        return;
                  }

                  if (getSpeed() == 2.0f) {
                        setSpeed(1.75f);
                        mPlaySpeedTims.setText(R.string.text_play_speed_six);
                  } else if (getSpeed() == 1.75f) {
                        setSpeed(1.5f);
                        mPlaySpeedTims.setText(R.string.text_play_speed_five);
                  } else if (getSpeed() == 1.5f) {
                        setSpeed(1.25f);
                        mPlaySpeedTims.setText(R.string.text_play_speed_four);
                  } else if (getSpeed() == 1.25f) {
                        setSpeed(1.0f);
                        mPlaySpeedTims.setText(R.string.text_play_speed_three);
                  } else if (getSpeed() == 1.0f) {
                        setSpeed(0.75f);
                        mPlaySpeedTims.setText(R.string.text_play_speed_two);
                  } else if (getSpeed() == 0.75f) {
                        setSpeed(0.5f);
                        mPlaySpeedTims.setText(R.string.text_play_speed_one);
                  }
            });

      }


      @Override
      public int getLayoutId() {
            return R.layout.playerpro_gsy_video;
      }

      /**
       * 显示比例
       * 注意，GSYVideoType.setShowType是全局静态生效，除非重启APP。
       */
      private void resolveTypeUI() {
            if (!mHadPlay) {
                  return;
            }
            if (mType == 1) {
                  mMoreScale.setText("16:9");
                  GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_16_9);
            } else if (mType == 2) {
                  mMoreScale.setText("4:3");
                  GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_4_3);
            } else if (mType == 3) {
                  mMoreScale.setText("全屏");
                  GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_FULL);
            } else if (mType == 4) {
                  mMoreScale.setText("拉伸全屏");
                  GSYVideoType.setShowType(GSYVideoType.SCREEN_MATCH_FULL);
            } else if (mType == 0) {
                  mMoreScale.setText("默认比例");
                  GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT);
            }
            changeTextureViewShowType();
            if (mTextureView != null)
                  mTextureView.requestLayout();
      }

      /**
       * 处理镜像旋转
       * 注意，暂停时
       */
      protected void resolveTransform() {
            switch (mTransformSize) {
                  case 1: {
                        Matrix transform = new Matrix();
                        transform.setScale(-1, 1, mTextureView.getWidth() / 2, 0);
                        mTextureView.setTransform(transform);
                        mChangeTransform.setImageResource(R.drawable.ic_video_statue_left_right);
                        mTextureView.invalidate();
                  }
                  break;
                  case 2: {
                        Matrix transform = new Matrix();
                        transform.setScale(1, -1, 0, mTextureView.getHeight() / 2);
                        mTextureView.setTransform(transform);
                        mChangeTransform.setImageResource(R.drawable.ic_video_statue_ltop_bottom);
                        mTextureView.invalidate();
                  }
                  break;
                  case 0: {
                        Matrix transform = new Matrix();
                        transform.setScale(1, 1, mTextureView.getWidth() / 2, 0);
                        mTextureView.setTransform(transform);
                        mChangeTransform.setImageResource(R.drawable.ic_video_statue);
                        mTextureView.invalidate();
                  }
                  break;
            }
      }

      /**
       * 全屏时将对应处理参数逻辑赋给全屏播放器
       *
       * @param context
       * @param actionBar
       * @param statusBar
       * @return
       */
      @Override
      public GSYBaseVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
            KroGsyVideoPlayer proGsyVideoPlayer = (KroGsyVideoPlayer) super.startWindowFullscreen(context, actionBar, statusBar);
            proGsyVideoPlayer.mSourcePosition = mSourcePosition;
            proGsyVideoPlayer.mType = mType;
            proGsyVideoPlayer.mTransformSize = mTransformSize;

            //sampleVideo.resolveTransform();
            proGsyVideoPlayer.resolveTypeUI();
            //sampleVideo.resolveRotateUI();
            //这个播放器的demo配置切换到全屏播放器
            //这只是单纯的作为全屏播放显示，如果需要做大小屏幕切换，请记得在这里耶设置上视频全屏的需要的自定义配置
            //比如已旋转角度之类的等等
            //可参考super中的实现
            return proGsyVideoPlayer;
      }

      @Override
      public void onSurfaceSizeChanged(Surface surface, int width, int height) {
            super.onSurfaceSizeChanged(surface, width, height);
            resolveTransform();
      }

      /**
       * 处理显示逻辑
       */
      @Override
      public void onSurfaceAvailable(Surface surface) {
            super.onSurfaceAvailable(surface);
            resolveRotateUI();
            resolveTransform();
      }

      /**
       * 旋转逻辑
       */
      private void resolveRotateUI() {
            if (!mHadPlay) {
                  return;
            }
            mTextureView.setRotation(mRotate);
            mTextureView.requestLayout();
      }

      /**
       * 推出全屏时将对应处理参数逻辑返回给非播放器
       *
       * @param oldF
       * @param vp
       * @param gsyVideoPlayer
       */
      @Override
      protected void resolveNormalVideoShow(View oldF, ViewGroup vp, GSYVideoPlayer gsyVideoPlayer) {
            super.resolveNormalVideoShow(oldF, vp, gsyVideoPlayer);
            if (gsyVideoPlayer != null) {
                  KroGsyVideoPlayer kroGsyVideoPlayer = (KroGsyVideoPlayer) gsyVideoPlayer;
                  mSourcePosition = kroGsyVideoPlayer.mSourcePosition;
                  mType = kroGsyVideoPlayer.mType;
                  mTransformSize = kroGsyVideoPlayer.mTransformSize;
                  resolveTypeUI();
            }
      }

      @Override
      protected void hideAllWidget() {
            super.hideAllWidget();
            setViewShowState(mPlaytimesLayout, INVISIBLE);
      }

      @Override
      protected void changeUiToNormal() {
            super.changeUiToNormal();
            setViewShowState(mThumbImageViewLayout, VISIBLE);
            layoutShowAndHide();
      }

      @Override
      protected void changeUiToPlayingShow() {
            super.changeUiToPlayingShow();
            layoutShowAndHide();
      }

      @Override
      protected void changeUiToPreparingShow() {
            super.changeUiToPreparingShow();
            layoutShowAndHide();
      }

      @Override
      protected void changeUiToPrepareingClear() {
            super.changeUiToPrepareingClear();
            setViewShowState(mPlaytimesLayout, INVISIBLE);
      }

      @Override
      protected void changeUiToPauseShow() {
            super.changeUiToPauseShow();
            layoutShowAndHide();
      }

      @Override
      protected void changeUiToPlayingBufferingShow() {
            super.changeUiToPlayingBufferingShow();
            layoutShowAndHide();

      }

      @Override
      protected void changeUiToPlayingBufferingClear() {
            super.changeUiToPlayingBufferingClear();
            setViewShowState(mPlaytimesLayout, INVISIBLE);
      }

      @Override
      protected void changeUiToCompleteShow() {
            super.changeUiToCompleteShow();
            layoutShowAndHide();

      }

      @Override
      protected void changeUiToCompleteClear() {
            super.changeUiToCompleteClear();
            setViewShowState(mPlaytimesLayout, INVISIBLE);
      }

      @Override
      protected void changeUiToError() {
            super.changeUiToError();
            setViewShowState(mPlaytimesLayout, INVISIBLE);
      }

      @Override
      protected void changeUiToClear() {
            super.changeUiToClear();
            setViewShowState(mPlaytimesLayout, INVISIBLE);
      }

      /**
       * 非全屏状态下设置控件隐藏或显示
       */
      private void layoutShowAndHide() {

            if (isIfCurrentIsFullscreen()) {
                  setViewShowState(mPlaytimesLayout, VISIBLE);
                  setViewShowState(mTitleTextView, VISIBLE);
                  setViewShowState(mBackButton, VISIBLE);
                  setViewShowState(mChangeRotate, VISIBLE);
                  setViewShowState(mChangeTransform, VISIBLE);
                  setViewShowState(mMoreScale, VISIBLE);

            } else {
                  setViewShowState(mPlaytimesLayout, INVISIBLE);
                  setViewShowState(mTitleTextView, INVISIBLE);
                  setViewShowState(mBackButton, INVISIBLE);
                  setViewShowState(mChangeRotate, INVISIBLE);
                  setViewShowState(mChangeTransform, INVISIBLE);
                  setViewShowState(mMoreScale, INVISIBLE);
            }
      }

      /**
       * 初始化倍速播放
       */
      private void initplaySpeedTimes() {
            setSpeed(1.0f);
            mPlaySpeedTims.setText("1.0x");
      }

      public TextView getmMoreScale() {
            return mMoreScale;
      }

      public void setmMoreScale(TextView mMoreScale) {
            this.mMoreScale = mMoreScale;
      }

      public ImageView getmChangeRotate() {
            return mChangeRotate;
      }

      public void setmChangeRotate(ImageView mChangeRotate) {
            this.mChangeRotate = mChangeRotate;
      }

      public ImageView getmChangeTransform() {
            return mChangeTransform;
      }

      public void setmChangeTransform(ImageView mChangeTransform) {
            this.mChangeTransform = mChangeTransform;
      }

      public TextView getmPlaySpeedTims() {
            return mPlaySpeedTims;
      }

      public void setmPlaySpeedTims(TextView mPlaySpeedTims) {
            this.mPlaySpeedTims = mPlaySpeedTims;
      }

      public TextView getmPlayTimesUp() {
            return mPlayTimesUp;
      }

      public void setmPlayTimesUp(TextView mPlayTimesUp) {
            this.mPlayTimesUp = mPlayTimesUp;
      }

      public TextView getmPlayTimesDown() {
            return mPlayTimesDown;
      }

      public void setmPlayTimesDown(TextView mPlayTimesDown) {
            this.mPlayTimesDown = mPlayTimesDown;
      }

      public ConstraintLayout getmPlaytimesLayout() {
            return mPlaytimesLayout;
      }

      public void setmPlaytimesLayout(ConstraintLayout mPlaytimesLayout) {
            this.mPlaytimesLayout = mPlaytimesLayout;
      }

}
