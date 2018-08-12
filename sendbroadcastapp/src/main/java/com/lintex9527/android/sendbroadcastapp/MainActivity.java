package com.lintex9527.android.sendbroadcastapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个工程和 broadcastreceiverapp 联合使用才行。
 * 验证广播接收器的两种注册方式：
 * 1、动态注册，代码中合适的位置成对的注册和注销广播接收器，例如onCreate() 和 onDestroy() 是一对，或者 onStop() 和 onStart() 是一对；
 * 2、静态注册，即在AndroidManifest.xml 文件中声明一个广播接收器，这个广播接收器可以在应用没有启动的时候收到广播，而动态注册就不可以。
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String KEY_DYNAMIC_MSG = "key_dynamic_msg";
    public static final String KEY_STATIC_MSG = "key_static_msg";
    public static final String KEY_WIFISTATE_MSG = "key_wifistate_msg";
    public static final String KEY_WEATHER_MSG = "key_weather_msg";

    private static int clickCount = 0;

    private Context mContext = null;
    private LocalReceiver mLocalReceiver = null;
    // 本地广播管理器
    LocalBroadcastManager mLocalBroadcastManager = null;


    // 需要动态申请的权限
    private static final String[] REQUIRED_PERMISSION_LIST = new String[] {
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.INTERNET,
    };

    // 动态检查申请的权限，如果哪项权限没有许可，则放到这个列表中
    private List<String> missingPermission = new ArrayList<>();

    private static final int REQUEST_PERMISSION_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 一开始初始化的时候就开始申请权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkAndRequestPermissions();
        }
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(mContext);

        // 注册本地广播接收器
        mLocalReceiver = new LocalReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LocalReceiver.RECEIVER_ACTION);
        // 注意这里使用的是本地广播管理器的注册方法
        mLocalBroadcastManager.registerReceiver(mLocalReceiver, intentFilter);
    }

    private void checkAndRequestPermissions() {
        for (String eachPermission : REQUIRED_PERMISSION_LIST) {
            if (ContextCompat.checkSelfPermission(this, eachPermission) != PackageManager.PERMISSION_GRANTED) {
                missingPermission.add(eachPermission);
            }
        }

        if (missingPermission.isEmpty()) {
            Log.d(TAG, "所有需求的权限都许可了");
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Log.d(TAG, "需要动态申请权限");
            ActivityCompat.requestPermissions(this,
                    missingPermission.toArray(new String[missingPermission.size()]),
                    REQUEST_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = grantResults.length-1; i >= 0; i--) {
                missingPermission.remove(permissions[i]);
            }
        }

        if (missingPermission.isEmpty()) {
            Log.d(TAG, "动态权限申请结束，所有的权限都满足了");
        } else {
            Log.d(TAG, "动态权限申请结束，还有权未满足");
        }
    }

    public void onClickSendBroadcast(View view) {
        switch (view.getId()) {
            case R.id.btn_dynamic:
                Intent intent = new Intent();
                intent.setAction("com.lintex9527.android.action.DYNAMIC_BROADCAST");
                intent.putExtra(KEY_DYNAMIC_MSG, "变化次数" + (++clickCount));
                sendBroadcast(intent);
                break;
            case R.id.btn_static:
                Intent intent2 = new Intent();
                intent2.setAction("com.lintex9527.android.action.STATIC_BROADCAST");
                intent2.putExtra(KEY_STATIC_MSG, "变化次数" + (++clickCount));
                sendBroadcast(intent2);
                break;


            case R.id.btn_local:
                // 发送本地广播
                Intent intent3 = new Intent();
                intent3.setAction(LocalReceiver.RECEIVER_ACTION);
                intent3.putExtra(LocalReceiver.KEY_LOCAL_MSG, "变化次数" + (++clickCount));
                mLocalBroadcastManager.sendBroadcast(intent3);
                break;

            case R.id.btn_restrict:
                // 发送一个受限制的广播
                Intent intent4 = new Intent();
                intent4.setAction("com.lintex9527.android.action.MY_WIFI_STATE_CHANGED");
                intent4.putExtra(KEY_WIFISTATE_MSG, "来自有权发送消息的广播" + (++clickCount));
                // 在这里第二个参数限制广播接收者必须具备的权限
                sendBroadcast(intent4, "com.lintex9527.android.permission.MY_WIFI_STATE");
                break;

            case R.id.btn_weather:
                // 发送天气状态
                Log.d(TAG, "发送天气状态");
                Intent intent5 = new Intent();
                intent5.setAction("com.lintex9527.android.action.WEATHER_CHANGED");
                intent5.putExtra(KEY_WEATHER_MSG, "下雨天");
                sendBroadcast(intent5);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        // 注销广播接收器
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
        super.onDestroy();
    }
}
