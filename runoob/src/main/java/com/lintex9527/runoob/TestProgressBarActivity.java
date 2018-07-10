package com.lintex9527.runoob;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * 教程：
 * http://www.runoob.com/w3cnote/android-tutorial-progressbar.html
 *
 * 使用动画来替代圆形进度条
 * 方案一：
 * 使用一套连续图片，形成帧动画，但需要进度条的时候让动画可见，不需要的时候让动画不可见。
 * 而这个动画一般使用 AnimationDrawable 来实现。
 *
 */
public class TestProgressBarActivity extends AppCompatActivity {

    private ImageView iv_progressbar;
    private AnimationDrawable ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_progress_bar);

        iv_progressbar = (ImageView) findViewById(R.id.iv_progressbar);
        ad = (AnimationDrawable) iv_progressbar.getDrawable();
        iv_progressbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                ad.start();
            }
        }, 100);
    }
}
