package com.wmd.kroplayer.bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/18
 * Version: 1.0.0
 * Desc:    videoInfoBean
 */
public class VideoInfoBean implements Parcelable {

      private long id;
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
      //视频高
      private long videoHigh;
      //视频宽
      private long videoWidth;

      public VideoInfoBean() {
      }

      public VideoInfoBean(String videoName,
                           long videoSize,
                           long videoDuration,
                           long time,
                           String path,
                           String thumbPath,
                           long videoHigh,
                           long videoWidth
      ) {
            this.videoName = videoName;
            this.videoSize = videoSize;
            this.videoDuration = videoDuration;
            this.time = time;
            this.path = path;
            this.thumbPath = thumbPath;
            this.videoHigh = videoHigh;
            this.videoWidth = videoWidth;
      }

      protected VideoInfoBean(Parcel in) {
            videoName = in.readString();
            videoSize = in.readLong();
            videoDuration = in.readLong();
            time = in.readLong();
            path = in.readString();
            thumbPath = in.readString();
            videoHigh = in.readLong();
            videoWidth = in.readLong();
      }

      public static final Creator<VideoInfoBean> CREATOR = new Creator<VideoInfoBean>() {
            @Override
            public VideoInfoBean createFromParcel(Parcel in) {
                  return new VideoInfoBean(in.readString(),
                          in.readLong(),
                          in.readLong(),
                          in.readLong(),
                          in.readString(),
                          in.readString(),
                          in.readLong(),
                          in.readLong());
            }

            @Override
            public VideoInfoBean[] newArray(int size) {
                  return new VideoInfoBean[size];
            }
      };

      @Override
      public int describeContents() {
            return 0;
      }

      @Override
      public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(videoName);
            dest.writeLong(videoSize);
            dest.writeLong(videoDuration);
            dest.writeLong(time);
            dest.writeString(path);
            dest.writeString(thumbPath);
            dest.writeLong(videoHigh);
            dest.writeLong(videoWidth);
      }

      public long getVideoHigh() {
            return videoHigh;
      }

      public void setVideoHigh(long videoHigh) {
            this.videoHigh = videoHigh;
      }

      public long getVideoWidth() {
            return videoWidth;
      }

      public void setVideoWidth(long videoWidth) {
            this.videoWidth = videoWidth;
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
}
