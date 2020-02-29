package com.wmd.kroplayer.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2411
 * Version: 1.0.0
 * Desc:
 */
public class BitmapUtils {
      private BitmapUtils() {

            throw new IllegalStateException("you can't instantiate BitmapUtils!");
      }

      /**
       * 获取本地视频缩略图
       *
       * @param Localpath 本地视频路径
       * @return
       */
      public static Bitmap getVideoBitmap(String Localpath) {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            Bitmap bitmap = null;
            try {
                  retriever.setDataSource(Localpath);
                  bitmap = createScaleBitmap(retriever.getFrameAtTime(), 100, 100);
//                  bitmap = retriever.getFrameAtTime();
                  return bitmap;
            } catch (Exception e) {
                  return null;
            } finally {
                  retriever.release();
//                  if (bitmap != null && !bitmap.isRecycled()) {
//                        bitmap.recycle();
//                        bitmap = null;
//                        Logger.e("图片回收--->");
//                  }
//                  System.gc();
            }

      }

      /**
       * 通过传入的Bitmap进行图片压缩和回收
       *
       * @param tempBitmap
       * @param desiredWidth
       * @param desiredHeight
       * @return
       */
      private static Bitmap createScaleBitmap(Bitmap tempBitmap, int desiredWidth, int desiredHeight) {
            // If necessary, scale down to the maximal acceptable size.
            if (tempBitmap != null && (tempBitmap.getWidth() > desiredWidth || tempBitmap.getHeight() > desiredHeight)) {
                  // 如果是放大图片，filter决定是否平滑，如果是缩小图片，filter无影响
                  Bitmap bitmap = Bitmap.createScaledBitmap(tempBitmap, desiredWidth, desiredHeight, true);
                  tempBitmap.recycle(); // 释放Bitmap的native像素数组
                  return bitmap;
            } else {
                  return tempBitmap; // 如果没有缩放，那么不回收
            }
      }



      /**
       * 分线程设置视频缩略图
       *
       * @param videoUrl  视频路径
       * @param videoThum
       */
      @SuppressLint("CheckResult")
      public static void showVideoThum(String videoUrl, ImageView videoThum) {
            Observable.create((ObservableOnSubscribe<Bitmap>) emitter -> {
                  Logger.e("视频截图加载成功!!!!-->");
                  emitter.onNext(getVideoBitmap(videoUrl));
                  emitter.onComplete();
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(bitmap -> {
                          Logger.e("视频截图设置成功!!!!-->");
                          videoThum.setImageBitmap(bitmap);
                    });
      }


}
