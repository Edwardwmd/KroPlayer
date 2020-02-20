package com.wmd.kroplayer.mvp.contract;

import android.app.Activity;

import com.wmd.kroplayer.base.BaseView;
import com.wmd.kroplayer.base.IModel;
import com.wmd.kroplayer.mvp.ui.activity.MainActivity;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/1812
 * Version: 1.0.0
 * Desc:
 */
public interface MainContract {
      interface Model extends IModel {

      }

      interface View extends BaseView {
            //扫描本地视频文件
            Activity getActivity();

      }

      interface Presenter {
      }
}
