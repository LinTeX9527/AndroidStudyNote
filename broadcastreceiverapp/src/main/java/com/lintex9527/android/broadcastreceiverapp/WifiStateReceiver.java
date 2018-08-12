package com.lintex9527.android.broadcastreceiverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 一个演示BroadcastReceiver收发权限的例子
 * 需求：
 * 这里 WifiStateReceiver 关注的广播 action string 为
 * com.lintex9527.android.action.MY_WIFI_STATE_CHANGED
 *
 * 并且限制广播接收者必须具备的权限是
 * <uses-permission android:name="com.lintex9527.android.permission.MY_WIFI_STATE"/>
 *
 */
public class WifiStateReceiver extends BroadcastReceiver {

    public static final String RECEIVER_ACTION = "com.lintex9527.android.action.MY_WIFI_STATE_CHANGED";
    public static final String KEY_WIFISTATE_MSG = "key_wifistate_msg";

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra(KEY_WIFISTATE_MSG);
        Toast.makeText(context, "WifiStateReceiver接收器消息：" + msg, Toast.LENGTH_SHORT).show();
    }

}
