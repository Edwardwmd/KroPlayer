package com.wmd.kroplayer.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.snackbar.Snackbar;
import com.orhanobut.logger.Logger;
import com.wmd.kroplayer.App;
import com.wmd.kroplayer.R;

import java.security.MessageDigest;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.bumptech.glide.load.resource.bitmap.VideoDecoder.FRAME_OPTION;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/1811
 * Version: 1.0.0
 * Desc:    AppUtils工具类
 */
public class AppUtils {
      private AppUtils() {

            throw new IllegalStateException("you can't instantiate AppUtils!");
      }

      /**
       * 解决BottomNavigationView大于3个item时的位移
       *
       * @param view BottomNavigationView
       */
      @SuppressLint("RestrictedApi")
      public static void disableShiftMode(BottomNavigationView view) {

            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                  menuView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
                  for (int i = 0; i < menuView.getChildCount(); i++) {
                        BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                        item.setShifting(false);
                  }
            } catch (Exception e) {
                  Logger.e("AppUstiks------->disableShiftMode", "Unable to get shift mode field", e);
            }
      }

      /**
       * 弹出Toast
       *
       * @param message 消息
       */
      public static void showToast(CharSequence message) {

            Toast.makeText(App.instance, message, Toast.LENGTH_SHORT).show();
      }

      /**
       * @param activity
       * @param message
       * @param isLong
       */
      public static void showSnackbar(Activity activity, String message, boolean isLong) {

            Completable.fromAction(() -> {
                  View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
                  Snackbar.make(view, message, isLong ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT).show();

            }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();

      }

      /**
       * 截取视频画面某一帧图片
       *
       * @param context         上下文
       * @param localPath       本地视频路径
       * @param imageView       设置图片控件
       * @param frameTimeMicros 获取某一时间帧 /微秒
       */
      @SuppressLint("CheckResult")
      public static void loadVideoScreenshot(final Context context, String localPath, ImageView imageView, long frameTimeMicros) {
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
            Glide
                    .with(context)
                    .load(localPath)
                    .error(R.drawable.ic_videoplaceholder)
                    .placeholder(R.drawable.ic_videoplaceholder_noload)
                    .fitCenter()
                    .apply(requestOptions)
                    .into(imageView);
      }


}