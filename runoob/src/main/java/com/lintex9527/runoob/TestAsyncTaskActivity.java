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
 */
public class TestAsyncTaskActivity extends AppCompatActivity {

    private TextView txttitle;
    private ProgressBar pgbar;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_async_task);

        initViews();
    }

    private void initViews() {
        txttitle = (TextView) findViewById(R.id.txt_title);
        pgbar = (ProgressBar) findViewById(R.id.pgbar);
        btnUpdate = (Button) findViewById(R.id.btn_update);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTask myAsyncTask = new MyAsyncTask(txttitle, pgbar);
                myAsyncTask.execute(1000);
            }
        });
    }
}
