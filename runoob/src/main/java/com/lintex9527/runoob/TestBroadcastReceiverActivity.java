package com.lintex9527.runoob;

import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lintex9527.custom.WifiStateBroadcastReceiver;


/**
 * 广播接收器
 * http://www.runoob.com/w3cnote/android-tutorial-broadcastreceiver.html
 *
 * 动态注册广播接收器，监听网络状态发生变化
 * 【注意】
 * 动态注册广播接收器，必须要主动注销广播接收器。
 */
public class TestBroadcastReceiverActivity extends AppCompatActivity {

    private Context mContext = null;
    private WifiStateBroadcastReceiver mWifiStateBroadcastReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_broadcast_receiver);

        initViews();
    }

    private void initViews() {
        // 注册广播接收器
        mWifiStateBroadcastReceiver = new WifiStateBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(mWifiStateBroadcastReceiver, intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 必须要注销广播接收器
        unregisterReceiver(mWifiStateBroadcastReceiver);
    }
}
