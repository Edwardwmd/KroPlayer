package com.wmd.kroplayer.bean;

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
      private String time;

      public VideoInfoBean() {

      }

      public String getvideoName() {

            return videoName;
      }

      public void setvideoName(String videoName) {

            this.videoName = videoName;
      }

      public long getvideoSize() {

            return videoSize;
      }

      public void setvideoSize(long videoSize) {

            this.videoSize = videoSize;
      }

      public long getvideoDuration() {

            return videoDuration;
      }

      public void setvideoDuration(long videoDuration) {

            this.videoDuration = videoDuration;
      }

      public String getTime() {

            return time;
      }

      public void setTime(String time) {

            this.time = time;
      }

}
