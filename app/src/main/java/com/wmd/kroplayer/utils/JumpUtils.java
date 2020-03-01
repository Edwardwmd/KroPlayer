package com.wmd.kroplayer.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import com.wmd.kroplayer.R;
import com.wmd.kroplayer.bean.VideoInfoBean;
import com.wmd.kroplayer.mvp.ui.activity.MainActivity;
import com.wmd.kroplayer.mvp.ui.activity.VideoPlayActivity;

import static com.wmd.kroplayer.utils.ContractUtils.VIDEO_PLAYE_NAME;
import static com.wmd.kroplayer.utils.ContractUtils.VIDEO_PLAYE_PATH;
import static com.wmd.kroplayer.utils.ContractUtils.VIDEO_PLAYE_THUM;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/22
 * Version: 1.0.0
 * Desc:    JumpUtils
 */
public class JumpUtils {
      /**
       * 跳转主页面
       *
       * @param activity
       */
      public static void LauncherToMain(Activity activity) {

            Intent intent = new Intent(activity, MainActivity.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                  ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                          activity);
                  ActivityCompat.startActivity(activity, intent, activityOptions.toBundle());
                  activity.finish();
            } else {
                  activity.startActivity(intent);
                  activity.finish();
                  activity.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
      }

      /**
       * 跳转VideoPlayActivity
       *
       * @param activity
       * @param view
       * @param videoInfoBean
       * @param sharedElementName
       */
      public static void JumpToVideoPlay(Activity activity, View view, VideoInfoBean videoInfoBean, String sharedElementName) {
            Intent intent = new Intent(activity, VideoPlayActivity.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                  Bundle bundle = new Bundle();
                  bundle.putString(VIDEO_PLAYE_PATH, videoInfoBean.getPath());
                  bundle.putString(VIDEO_PLAYE_THUM, videoInfoBean.getThumbPath());
                  bundle.putString(VIDEO_PLAYE_NAME, videoInfoBean.getVideoName());
                  intent.putExtras(bundle);
                  ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                          activity, view, sharedElementName);
                  activity.startActivity(intent, activityOptions.toBundle());

            } else {
                  activity.startActivity(intent);
                  activity.finish();
                  activity.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
      }
}
