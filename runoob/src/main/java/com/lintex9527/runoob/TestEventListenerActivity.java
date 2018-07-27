package com.lintex9527.runoob;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.lintex9527.custom.MyButton;

public class TestEventListenerActivity extends AppCompatActivity {

    private static final String TAG = TestEventListenerActivity.class.getName();

    private Context mContext;
    private MyButton btnTestEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_event_listener);
        mContext = TestEventListenerActivity.this;

        bindViews();
    }

    private void bindViews() {
        btnTestEvent = (MyButton) findViewById(R.id.btn_test_events);
//        btnTestEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "按钮被单击了", Toast.LENGTH_SHORT).show();
//            }
//        });

        btnTestEvent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    Log.d(TAG, "按钮监听器的ACTION_DOWN 检测到了");
                }
                return false;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        Log.d(TAG, "Activity的onKeyDown() 被调用");
        return false;
    }
}
