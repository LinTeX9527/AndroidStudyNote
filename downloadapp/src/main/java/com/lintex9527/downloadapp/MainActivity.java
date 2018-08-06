package com.lintex9527.downloadapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lintex9527.entities.FileInfo;
import com.lintex9527.services.DownloadService;

import java.util.ArrayList;
import java.util.List;


/**
 * 慕课网--多线程下载实例
 * https://www.imooc.com/video/7045
 */
public class MainActivity extends AppCompatActivity {

    private Context mContext = null;

    private static final String TAG = MainActivity.class.getSimpleName();

    // 组件定义
    private TextView tvFileName = null;
    private ProgressBar pbProgress = null;
    private Button btnStartDownload = null;
    private Button btnStopDownload = null;


    // 应用程序需要申请的权限
    private static final String[] REQUIRED_PERMISSION_LIST = new String[] {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    private List<String> missingPermission = new ArrayList<>();

    private static final int REQUEST_PERMISSION_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 当编译器和目标版本高于22时，需要动态申请权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkAndRequestPermissions();
        }

        mContext = MainActivity.this;
        initViews();
    }


    /**
     * 主动申请权限
     * 当检查有部分权限未满足时，主动申请权限
     */
    private void checkAndRequestPermissions() {
        for (String eachPermission: REQUIRED_PERMISSION_LIST) {
            if (ContextCompat.checkSelfPermission(this, eachPermission) != PackageManager.PERMISSION_GRANTED) {
                missingPermission.add(eachPermission);
            }
        }

        // 主动申请权限
        if (!missingPermission.isEmpty()) { // 有部分权限没有满足，需要动态申请
            Log.d(TAG, "checkAndRequestPermissions() 开始动态申请权限");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this,
                        missingPermission.toArray(new String[missingPermission.size()]),
                        REQUEST_PERMISSION_CODE);
            }
        }
    }


    /**
     * 动态申请权限的结果检查
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = grantResults.length - 1; i >= 0; i--) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    missingPermission.remove(permissions[i]);
                }
            }
        }

        if (missingPermission.isEmpty()) { // 所有的权限都已经满足了
            Log.d(TAG, "所有需要的权限都已经满足了");
        } else {
            Toast.makeText(mContext, "还有权限未满足，应用可能会崩溃", Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        tvFileName = (TextView) findViewById(R.id.tv_filename);
        pbProgress = (ProgressBar) findViewById(R.id.pb_progress);
        btnStartDownload = (Button) findViewById(R.id.btn_start_download);
        btnStopDownload = (Button) findViewById(R.id.btn_stop_download);

        pbProgress.setMax(100);

        // 注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadService.ACTION_UPDATE);
        registerReceiver(mReceiver, filter);


        // 创建文件信息对象
        final FileInfo fileInfo = new FileInfo(0,
                "http://mm.chinasareview.com/wp-content/uploads/2016a/08/22/07.jpg",
                "girl.jpg", 0, 0);

//        final FileInfo fileInfo = new FileInfo(0,
//                "http://www.imooc.com/mobile/imooc.apk",
//                "imooc.apk", 0, 0);

        // 给按钮添加事件监听器
        btnStartDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvFileName.setText(fileInfo.getFileName());
                pbProgress.setProgress(0);

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

                tvFileName.setText("");
                pbProgress.setProgress(0);

                Intent intent = new Intent(mContext, DownloadService.class);
                intent.setAction(DownloadService.ACTION_STOP);
                intent.putExtra(DownloadService.KEY_FILEINFO, fileInfo);
                stopService(intent);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    /**
     * 更新UI的广播接收器
     */
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownloadService.ACTION_UPDATE.equals(intent.getAction())) {
                int finished = intent.getIntExtra("finished", 0);
                Log.d(TAG, "广播接收器接收进度为：" + finished);
                pbProgress.setProgress(finished); // 更新进度条
            }
        }
    };
}
