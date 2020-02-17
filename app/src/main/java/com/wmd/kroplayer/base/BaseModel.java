package com.wmd.kroplayer.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/1620
 * Version: 1.0.0
 * Desc:
 */
public class BaseModel implements IModel, LifecycleObserver {
      @Override
      public void onDestory() {
      }

      @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
      void onDestroy(LifecycleOwner owner) {
            owner.getLifecycle().removeObserver(this);
      }
}
