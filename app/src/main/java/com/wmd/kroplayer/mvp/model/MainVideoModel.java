package com.wmd.kroplayer.mvp.model;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.orhanobut.logger.Logger;
import com.wmd.kroplayer.base.BaseModel;
import com.wmd.kroplayer.bean.VideoInfoBean;
import com.wmd.kroplayer.di.scope.FragmentScope;
import com.wmd.kroplayer.mvp.contract.MainVideoContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2210
 * Version: 1.0.0
 * Desc:    MainVideoModel
 */
@FragmentScope
public class MainVideoModel extends BaseModel implements MainVideoContract.Model {
      @Inject
      public MainVideoModel() {
      }

      private String[] mediaColumns = {MediaStore.Video.Media._ID,
              MediaStore.Video.Media.DISPLAY_NAME,
              MediaStore.Video.Media.SIZE,
              MediaStore.Video.Media.DURATION,
              MediaStore.Video.Media.DATE_TAKEN,
              MediaStore.Video.Media.DATA,
              MediaStore.Video.Thumbnails.DATA,
              MediaStore.Video.Media.HEIGHT,
              MediaStore.Video.Media.WIDTH};

      /**
       * 获取本地所有视频信息
       *
       * @param context 上下文
       * @return Observable<List < VideoInfoBean>>
       */
      @Override
      public Observable<List<VideoInfoBean>> getVideoInfos(Context context) {
            return Observable.create((ObservableOnSubscribe<List<VideoInfoBean>>) emitter -> {
                  List<VideoInfoBean> videoInfoBeans = new ArrayList<>();
                  Cursor cursor = null;
                  try {
                        cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                                mediaColumns, null, null, MediaStore.Video.VideoColumns.DATE_ADDED + " DESC");
                        if (cursor.moveToFirst()) {
                              do {
                                    VideoInfoBean info = new VideoInfoBean();
                                    info.setVideoName(cursor.getString(cursor.getColumnIndex(mediaColumns[1])));
                                    info.setVideoSize(cursor.getLong(cursor.getColumnIndex(mediaColumns[2])));
                                    info.setVideoDuration(cursor.getLong(cursor.getColumnIndex(mediaColumns[3])));
                                    info.setTime(cursor.getLong(cursor.getColumnIndex(mediaColumns[4])));
                                    info.setPath(cursor.getString(cursor.getColumnIndex(mediaColumns[5])));
                                    info.setThumbPath(cursor.getString(cursor.getColumnIndex(mediaColumns[6])));
                                    info.setVideoHigh(cursor.getLong(cursor.getColumnIndex(mediaColumns[7])));
                                    info.setVideoWidth(cursor.getLong(cursor.getColumnIndex(mediaColumns[8])));
                                    videoInfoBeans.add(info);

                              } while (cursor.moveToNext());
                              Logger.e("所有URL------>{ "+videoInfoBeans.toString() +" }");
                        }
                        emitter.onNext(videoInfoBeans);
                        emitter.onComplete();
                  } catch (Exception e) {
                        Logger.e("AppDataManager--->getLocalAllVideo()", e.toString());
                  } finally {
                        if (cursor != null)
                              cursor.close();
                  }


            }).subscribeOn(Schedulers.io());

      }


}
