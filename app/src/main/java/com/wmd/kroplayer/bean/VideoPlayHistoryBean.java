package com.wmd.kroplayer.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/3/319
 * Version: 1.0.0
 * Desc:    VideoPlayHistoryBean 实体类
 */
@Entity(nameInDb = "video_play_history")
public class VideoPlayHistoryBean {
      //ID自增长
      @Id(autoincrement = true)
      private long id;
      //视频名称
      private String videoName;
      //当前播放日期
      private String currentDate;
      //当前进度条
      private int currentProgress;
      //视频大小
      private String videoSize;
      //视频URL
      private String url;
      //视频格式
      private String format;
      //视频时长
      private String videoDuration;
      @Generated(hash = 2059217557)
      public VideoPlayHistoryBean(long id, String videoName, String currentDate,
              int currentProgress, String videoSize, String url, String format,
              String videoDuration) {
          this.id = id;
          this.videoName = videoName;
          this.currentDate = currentDate;
          this.currentProgress = currentProgress;
          this.videoSize = videoSize;
          this.url = url;
          this.format = format;
          this.videoDuration = videoDuration;
      }
      @Generated(hash = 178779342)
      public VideoPlayHistoryBean() {
      }
      public long getId() {
          return this.id;
      }
      public void setId(long id) {
          this.id = id;
      }
      public String getVideoName() {
          return this.videoName;
      }
      public void setVideoName(String videoName) {
          this.videoName = videoName;
      }
      public String getCurrentDate() {
          return this.currentDate;
      }
      public void setCurrentDate(String currentDate) {
          this.currentDate = currentDate;
      }
      public int getCurrentProgress() {
          return this.currentProgress;
      }
      public void setCurrentProgress(int currentProgress) {
          this.currentProgress = currentProgress;
      }
      public String getVideoSize() {
          return this.videoSize;
      }
      public void setVideoSize(String videoSize) {
          this.videoSize = videoSize;
      }
      public String getUrl() {
          return this.url;
      }
      public void setUrl(String url) {
          this.url = url;
      }
      public String getFormat() {
          return this.format;
      }
      public void setFormat(String format) {
          this.format = format;
      }
      public String getVideoDuration() {
          return this.videoDuration;
      }
      public void setVideoDuration(String videoDuration) {
          this.videoDuration = videoDuration;
      }
      



}
