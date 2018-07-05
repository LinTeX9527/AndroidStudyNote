package com.lintex9527.runoob;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 *
 * TextView 参见属性，参见教程：
 * http://www.runoob.com/w3cnote/android-tutorial-textview.html
 *
 * 如何通过XML代码设置一个ShapeDrawable参见：
 * https://blog.csdn.net/dangnianmingyue_gg/article/details/51675453
 */
public class TestTextViewActivity extends AppCompatActivity {

    private static final String TAG = TestTextViewActivity.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_textview);

        Log.d(TAG, "onCreate()");
    }
}
