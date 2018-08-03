package com.lintex9527.runoob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Service 基础
 * http://www.runoob.com/w3cnote/android-tutorial-service-1.html
 *
 */
public class TestServiceActivity extends AppCompatActivity {

    private Button btnStartService;
    private Button btnStopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);

        initViews();
    }

    private void initViews() {
        btnStartService = (Button) findViewById(R.id.btnStartService);
        btnStopService = (Button) findViewById(R.id.btnStopService);

        // 创建启动Service 的Intent 以及 Intent 的属性
        final Intent intent = new Intent();
        intent.setAction("com.lintex9527.custom.TestService01.ACTION");

        // 为两个按钮设置点击事件，分别是启动和停止服务
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });
    }
}
