package com.wmd.kroplayer.mvp.contract;

import android.app.Activity;
import android.content.Context;

import com.wmd.kroplayer.base.BaseView;
import com.wmd.kroplayer.base.IModel;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2210
 * Version: 1.0.0
 * Desc:    MainVideoContract
 */
public interface MainVideoContract {
      interface Model extends IModel {
      }

      interface View extends BaseView {
            Activity getActivity();
      }

      interface Presenter {
      }
}
