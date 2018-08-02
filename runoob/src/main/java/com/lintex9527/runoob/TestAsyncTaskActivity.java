package com.lintex9527.runoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lintex9527.custom.MyAsyncTask;

/**
 * AsyncTask 异步任务
 * http://www.runoob.com/w3cnote/android-tutorial-ansynctask.html
 *
 * Android 旋转屏幕--处理Activity与AsyncTask的最佳解决方案
 * https://www.cnblogs.com/jycboy/p/save_state_data.html
 *
 */
public class TestAsyncTaskActivity extends AppCompatActivity {

    private TextView txttitle;
    private ProgressBar pgbar;
    private Button btnStart;
    private Button btnStop;
    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_async_task);

        initViews();
    }

    private void initViews() {
        txttitle = (TextView) findViewById(R.id.txt_title);
        pgbar = (ProgressBar) findViewById(R.id.pgbar);
        btnStart = (Button) findViewById(R.id.btn_task_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAsyncTask = new MyAsyncTask(txttitle, pgbar);
                myAsyncTask.execute(1000);
            }
        });

        btnStop = (Button) findViewById(R.id.btn_task_stop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myAsyncTask != null) {
                    myAsyncTask.cancel(true);
                }
            }
        });
    }
}
