package com.wmd.kroplayer.bean;

import android.graphics.Bitmap;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/1812
 * Version: 1.0.0
 * Desc:    videoInfoBean
 */
public class VideoInfoBean {
      //视频名称
      private String videoName;
      //视频容量大小
      private long videoSize;
      //视频时长
      private long videoDuration;
      //视频修改时间(xxxx.xx.xx)
      private long time;
      //视频路径
      private String path;
      //视频截图路径
      private String thumbPath;
//      //视频截图
//      private Bitmap bitmap;

      public VideoInfoBean() {

      }

      public String getVideoName() {

            return videoName;
      }

      public void setVideoName(String videoName) {

            this.videoName = videoName;
      }

      public long getVideoSize() {

            return videoSize;
      }

      public void setVideoSize(long videoSize) {

            this.videoSize = videoSize;
      }

      public long getVideoDuration() {

            return videoDuration;
      }

      public void setVideoDuration(long videoDuration) {

            this.videoDuration = videoDuration;
      }

      public long getTime() {

            return time;
      }

      public void setTime(long time) {

            this.time = time;
      }

      public String getPath() {

            return path;
      }

      public void setPath(String path) {

            this.path = path;
      }

      public String getThumbPath() {

            return thumbPath;
      }

      public void setThumbPath(String thumbPath) {

            this.thumbPath = thumbPath;
      }

//      public Bitmap getBitmap() {
//
//            return bitmap;
//      }
//
//      public void setBitmap(Bitmap bitmap) {
//
//            this.bitmap = bitmap;
//      }

      @Override
      public String toString() {

            return "VideoInfoBean{" +
                    "videoName='" + videoName + '\'' +
                    ", videoSize=" + videoSize +
                    ", videoDuration=" + videoDuration +
                    ", time='" + time + '\'' +
                    ", path='" + path + '\'' +
                    ", thumbPath='" + thumbPath +
                    '}';
      }
}
