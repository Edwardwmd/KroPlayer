package com.wmd.kroplayer;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.wmd.kroplayer.mvp.ui.fragment.MainVideoFragment;
import com.wmd.kroplayer.mvp.ui.fragment.SettingFragment;
import com.wmd.kroplayer.mvp.ui.fragment.VideoListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2020
 * Version: 1.0.0
 * Desc:
 */
public class NotToUse {
      private List<Fragment> fragmentList;
      private int lastIndex;

      private void addFragmentList() {

            if (fragmentList == null) {
                  fragmentList = new ArrayList<>();
                  fragmentList.add(MainVideoFragment.newInstance());
                  fragmentList.add(VideoListFragment.newInstance());
                  fragmentList.add(SettingFragment.newInstance());
            }

      }

//      @Override
//      public boolean onNavigationItemSelected(@NonNull MenuItem item) {






//            switch (item.getItemId()) {
//                  case R.id.tab_mainvideo:
//                        switchFragment(0);
//                        break;
//                  case R.id.tab_videolist:
//                        switchFragment(1);
//                        break;
//                  case R.id.tab_setting:
//                        switchFragment(2);
//                        break;
//
//            }
//
//            return true;
//      }

      private void switchFragment(int position) {
//
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            Fragment currentFragment = fragmentList.get(position);
//            Fragment lastFragment = fragmentList.get(lastIndex);
//            lastIndex = position;
//            ft.hide(lastFragment);
//            if (!currentFragment.isAdded()) {
//                  getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
//                  ft.add(R.id.fl_content, currentFragment);
//            }
//            ft.show(currentFragment);
//            ft.commitAllowingStateLoss();
      }
}
