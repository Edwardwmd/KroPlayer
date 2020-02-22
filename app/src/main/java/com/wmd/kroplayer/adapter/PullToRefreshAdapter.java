package com.wmd.kroplayer.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2220
 * Version: 1.0.0
 * Desc:
 */
public class PullToRefreshAdapter extends BaseQuickAdapter {
      
      public PullToRefreshAdapter(int layoutResId, List data) {

            super(layoutResId, data);
      }

      @Override
      protected void convert(BaseViewHolder baseViewHolder, Object o) {

      }

      @Override
      public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

      }
}
