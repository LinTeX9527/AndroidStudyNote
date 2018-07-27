package com.lintex9527.runoob;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lintex9527.custom.MyButton;

public class TestEventListenerActivity extends AppCompatActivity {

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
        btnTestEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "按钮被单击了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
