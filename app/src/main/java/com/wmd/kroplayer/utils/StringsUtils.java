package com.wmd.kroplayer.utils;

import android.net.Uri;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/20
 * Version: 1.0.0
 * Desc:    StringsUtils
 */
public class StringsUtils {
      private StringsUtils() {

            throw new IllegalStateException("you can't instantiate StringsUtils!");
      }

      public static Uri StringToUri(String path) {
            return Uri.parse(path);
      }

}
