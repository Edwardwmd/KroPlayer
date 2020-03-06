package com.wmd.kroplayer.mvp.contract;

import android.app.Activity;
import android.content.Context;

import com.wmd.kroplayer.base.BaseView;
import com.wmd.kroplayer.base.IModel;
import com.wmd.kroplayer.bean.VideoInfoBean;

import java.util.List;

import io.reactivex.Observable;

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
            Observable<List<VideoInfoBean>> getVideoInfos(Context context);
      }

      interface View extends BaseView {

            Activity getActivity();

            void setLoadingEmptyView();
            //显示选择逻辑
            void showSelectLogic(int position);
            //显示删除逻辑
            void showDeleteLogic(int position);
      }

      interface Presenter {
      }
}
