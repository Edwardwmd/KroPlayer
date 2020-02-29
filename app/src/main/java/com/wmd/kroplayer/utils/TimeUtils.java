package com.wmd.kroplayer.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2318
 * Version: 1.0.0
 * Desc:
 */
public class TimeUtils {
      private TimeUtils() {

            throw new IllegalStateException("you can't instantiate TimeUtils!");
      }

      public static String getFormatedDateTime(String pattern, long dateTime) {
            SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
            return sDateFormat.format(new Date(dateTime + 0));
      }

      public static String millisecondToHours(long millisecond) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
            return dateFormat.format(new Date(millisecond));
      }
}
