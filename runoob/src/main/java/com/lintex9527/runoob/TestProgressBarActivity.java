package com.lintex9527.runoob;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

import com.lintex9527.androidtools.CircleProgressBar;

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
    private ToggleButton toggleProgress;

    private CircleProgressBar mCircleProgressBar;
    private ToggleButton mToggleButton_CirclePgbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_progress_bar);

        iv_progressbar = (ImageView) findViewById(R.id.iv_progressbar);
        ad = (AnimationDrawable) iv_progressbar.getDrawable();

        // 一开始的进度条设置--不可见，等待按钮事件
        iv_progressbar.setVisibility(ProgressBar.INVISIBLE);


        toggleProgress = (ToggleButton) findViewById(R.id.btnStopProgress);
        toggleProgress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) { // 停止
                    iv_progressbar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (ad.isRunning()) {
                                ad.stop();
                                iv_progressbar.setVisibility(ProgressBar.INVISIBLE);
                            }
                        }
                    }, 100);
                } else { // 启动进度条
                    iv_progressbar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            iv_progressbar.setVisibility(ProgressBar.VISIBLE);
                            if(!ad.isRunning()) {
                                ad.start();
                            }
                        }
                    }, 100);
                }
            }
        });

        mCircleProgressBar = (CircleProgressBar) findViewById(R.id.circir_pgbar);
        mToggleButton_CirclePgbar = (ToggleButton) findViewById(R.id.tgbtn_circlepgbar);
        mToggleButton_CirclePgbar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { //进度开始刷新
                    mCircleProgressBar.addTargetProgressByStep(10);
                    mCircleProgressBar.invalidate();
                    mCircleProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mCircleProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
}
