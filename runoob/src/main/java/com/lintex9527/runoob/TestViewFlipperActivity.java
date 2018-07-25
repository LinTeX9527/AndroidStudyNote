package com.lintex9527.runoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ViewFlipper;

/**
 * ViewFlipper 翻转视图
 * http://www.runoob.com/w3cnote/android-tutorial-viewflipper.html
 *
 * 这个案例演示静态图片轮播
 */
public class TestViewFlipperActivity extends AppCompatActivity {

    private ViewFlipper vflp_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_flipper);

        vflp_help = (ViewFlipper) findViewById(R.id.viewflipper_help);
        vflp_help.startFlipping();
    }
}
