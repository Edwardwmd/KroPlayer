package com.wmd.kroplayer.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.TooltipCompat;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.snackbar.Snackbar;
import com.orhanobut.logger.Logger;
import com.wmd.kroplayer.App;

import java.lang.reflect.Field;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;

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
       * @param videoPath 视频路径
       * @param width     图片宽度
       * @param height    图片高度
       * @param kind      eg:MediaStore.Video.Thumbnails.MICRO_KIND   MINI_KIND: 512 x 384，MICRO_KIND: 96 x 96
       * @return
       */
      public static Bitmap getVideoThumbnail(String videoPath, int width, int height, int kind) {
            // 获取视频的缩略图
            Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
            //extractThumbnail 方法二次处理,以指定的大小提取居中的图片,获取最终我们想要的图片
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
            return bitmap;
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
       *
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
}