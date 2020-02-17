package com.wmd.kroplayer.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wmd.kroplayer.di.compontent.AppComponent;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/1619
 * Version: 1.0.0
 * Desc:
 */
public interface IActivity {
      int initView(@Nullable Bundle savedInstanceState);

      void initData(@Nullable Bundle savedInstanceState);

//      void setupActivityComponent(@NonNull AppComponent appComponent);

}
