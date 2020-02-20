package com.wmd.kroplayer.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2010
 * Version: 1.0.0
 * Desc:   IFragment
 */
public interface IFragment {

      int initLayoutRes();

      void initData(View mView, @Nullable Bundle savedInstanceState);


}
