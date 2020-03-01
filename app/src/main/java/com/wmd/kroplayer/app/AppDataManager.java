package com.wmd.kroplayer.app;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
 * Desc:    AppDataManager
 */
public class AppDataManager {

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


}
