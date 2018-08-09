package com.lintex9527.android.sendbroadcastapp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * 这个工程和 broadcastreceiverapp 联合使用才行。
 * 验证广播接收器的两种注册方式：
 * 1、动态注册，代码中合适的位置成对的注册和注销广播接收器，例如onCreate() 和 onDestroy() 是一对，或者 onStop() 和 onStart() 是一对；
 * 2、静态注册，即在AndroidManifest.xml 文件中声明一个广播接收器，这个广播接收器可以在应用没有启动的时候收到广播，而动态注册就不可以。
 */
public class MainActivity extends AppCompatActivity {

    public static final String KEY_DYNAMIC_MSG = "key_dynamic_msg";
    public static final String KEY_STATIC_MSG = "key_static_msg";

    private static int clickCount = 0;

    private Context mContext = null;
    private LocalReceiver mLocalReceiver = null;
    // 本地广播管理器
    LocalBroadcastManager mLocalBroadcastManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public void sendBroadcast(View view) {
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
        }
    }


    @Override
    protected void onDestroy() {
        // 注销广播接收器
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
        super.onDestroy();
    }
}
