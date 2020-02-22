package com.wmd.kroplayer.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.wmd.kroplayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2121
 * Version: 1.0.0
 * Desc:
 */
public class VideoPlayActivity extends RxAppCompatActivity {
      Unbinder unbinder;
      @BindView(R.id.tvfl)
      TextView tvfl;

      @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_videoplay);
            unbinder = ButterKnife.bind(this);
      }

      @Override
      protected void onDestroy() {

            super.onDestroy();
            unbinder.unbind();

      }

      @OnClick(R.id.tvfl)
      public void onViewClicked() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
      }
}
