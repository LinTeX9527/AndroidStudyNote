package com.lintex9527.downloadapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lintex9527.entities.FileInfo;
import com.lintex9527.services.DownloadService;


/**
 * 慕课网--多线程下载实例
 * https://www.imooc.com/video/7045
 */
public class MainActivity extends AppCompatActivity {

    private Context mContext = null;

    // 组件定义
    private TextView tvFileName = null;
    private ProgressBar pbProgress = null;
    private Button btnStartDownload = null;
    private Button btnStopDownload = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        initViews();
    }

    private void initViews() {
        tvFileName = (TextView) findViewById(R.id.tv_filename);
        pbProgress = (ProgressBar) findViewById(R.id.pb_progress);
        btnStartDownload = (Button) findViewById(R.id.btn_start_download);
        btnStopDownload = (Button) findViewById(R.id.btn_stop_download);

        // 创建文件信息对象
        final FileInfo fileInfo = new FileInfo(0,
                "http://readfree.me/edition/9d18f3cefc126ea49a3a66d10495c57e/down/%E5%8D%8A%E5%B0%8F%E6%97%B6%E6%BC%AB%E7%94%BB%E4%B8%AD%E5%9B%BD%E5%8F%B23.mobi",
                "历史漫画.mobi", 0, 0);

        // 给按钮添加事件监听器
        btnStartDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 通过Intent 把数据传递给DownloadService
                Intent intent = new Intent(mContext, DownloadService.class);
                intent.setAction(DownloadService.ACTION_START);
                intent.putExtra(DownloadService.KEY_FILEINFO, fileInfo);
                startService(intent);
            }
        });

        btnStopDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DownloadService.class);
                intent.setAction(DownloadService.ACTION_STOP);
                intent.putExtra(DownloadService.KEY_FILEINFO, fileInfo);
                stopService(intent);
            }
        });
    }
}
