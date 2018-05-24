package com.lintex9527.uistuff;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lintex9527.study.R;

/**
 * 相对布局
 *
 * 参见 activity_relativelayout.xml
 * 可以先申请一个resource ID，然后赋值给某个view
 *
 */
public class RelativeLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relativelayout);
    }
}
