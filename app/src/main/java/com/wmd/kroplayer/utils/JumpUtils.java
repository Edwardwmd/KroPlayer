package com.wmd.kroplayer.utils;

import android.app.Activity;
import android.content.Intent;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import com.wmd.kroplayer.R;
import com.wmd.kroplayer.mvp.ui.activity.MainActivity;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2213
 * Version: 1.0.0
 * Desc:    JumpUtils
 */
public class JumpUtils {

      public static void LauncherToMain(Activity activity){

            Intent intent=new Intent(activity, MainActivity.class);
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

}
