package com.lintex9527.runoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * ScrollView 基础用法
 * http://www.runoob.com/w3cnote/android-tutorial-scrollview.html
 * 
 */
public class TestScrollViewActivity extends AppCompatActivity {

    private Button btnUP, btnDown;
    private ScrollView mScrollView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scroll_view);

        initGUI();
    }

    private void initGUI() {
        btnUP = findViewById(R.id.btnScrollTop);
        btnDown = findViewById(R.id.btnScrollBottom);
        mScrollView = findViewById(R.id.scrollView);
        mTextView = findViewById(R.id.tvContents);

        btnUP.setOnClickListener(btnOnClickListener);
        btnDown.setOnClickListener(btnOnClickListener);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= 100; i++) {
            stringBuilder.append("吾日三省乎吾身" + i + "\n");
        }
        mTextView.setText(stringBuilder.toString());
    }

    View.OnClickListener btnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnScrollTop:
                    mScrollView.fullScroll(ScrollView.FOCUS_UP);
                    break;
                case R.id.btnScrollBottom:
                    mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    break;
            }
        }
    };

}
