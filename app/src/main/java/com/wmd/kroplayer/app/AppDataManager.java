package com.wmd.kroplayer.app;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import androidx.core.content.ContextCompat;

import com.orhanobut.logger.Logger;
import com.wmd.kroplayer.bean.VideoInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2216
 * Version: 1.0.0
 * Desc:
 */
public class AppDataManager {

      String[] mediaColumns = {MediaStore.Video.Media._ID,
              MediaStore.Video.Media.DISPLAY_NAME,
              MediaStore.Video.Media.SIZE,
              MediaStore.Video.Media.DURATION,
              MediaStore.Video.Media.DATE_ADDED,
              MediaStore.Video.Media.DATA,
              MediaStore.Video.Thumbnails.DATA};
      private volatile static AppDataManager manager;
      private Context context;

      public static AppDataManager newInstance(Context context) {

            if (manager == null) {
                  synchronized (AppDataManager.class) {
                        if (manager == null) {
                              manager = new AppDataManager(context);
                        }
                  }
            }
            return manager;
      }

      private AppDataManager(Context context) {

            this.context = context;
      }

      /**
       * 获取本地视频的数据
       *
       * @return videoInfoBeanList
       */
      public List<VideoInfoBean> getLocalAllVideo() {

            List<VideoInfoBean> localAllVideos = new ArrayList<>();
            Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    mediaColumns, null, null, MediaStore.Video.VideoColumns.DATE_ADDED + " DESC");
            try {

                  if (cursor == null) {
                        return localAllVideos;
                  }
                  if (cursor.moveToFirst()) {
                        do {
                              VideoInfoBean info = new VideoInfoBean();
                              info.setVideoName(cursor.getString(cursor.getColumnIndex(mediaColumns[1])));
                              info.setVideoSize(cursor.getLong(cursor.getColumnIndex(mediaColumns[2])));
                              info.setVideoDuration(cursor.getInt(cursor.getColumnIndex(mediaColumns[3])));
                              info.setTime(cursor.getString(cursor.getColumnIndex(mediaColumns[4])));
                              info.setPath(cursor.getString(cursor.getColumnIndex(mediaColumns[5])));
                              info.setThumbPath(cursor.getString(cursor.getColumnIndex(mediaColumns[6])));

                              localAllVideos.add(info);
                        } while (cursor.moveToNext());

                  }

            } catch (Exception e) {
                  Logger.e("AppDataManager--->getLocalAllVideo()", e);
            } finally {
                  if (cursor != null)
                        cursor.close();
            }


            return localAllVideos;

      }


}
