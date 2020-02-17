package com.wmd.kroplayer.mvp.contract;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wmd.kroplayer.base.BaseView;
import com.wmd.kroplayer.base.IModel;
import com.wmd.kroplayer.base.Ipresenter;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/1712
 * Version: 1.0.0
 * Desc:
 */
public interface LauncherContract {
      interface Model extends IModel {

      }

      interface View extends BaseView {
            Activity getActivity();

            //跳转页面
            void turnToMain();

            //申请权限
            RxPermissions getRxPermissions();

      }

}
