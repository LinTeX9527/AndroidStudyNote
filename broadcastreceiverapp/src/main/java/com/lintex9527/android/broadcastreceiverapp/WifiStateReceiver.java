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
 * 并且限制广播发送者必须具备的权限是
 * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
 *
 * 实施：
 * 那么实现的两种方法是：
 * 第一种：通过在 AndroidManifest.xml 文件中注册 （这里采用这一种）
 <receiver
 android:name=".WifiStateReceiver"
 android:permission="android.permission.CHANGE_WIFI_STATE"
 >
 <intent-filter>
 <action android:name="com.lintex9527.android.action.MY_WIFI_STATE_CHANGED"/>
 </intent-filter>

 </receiver>
 *
 * 第二种：动态注册一个广播接收器
 * IntentFilter filter = new IntentFilter("com.lintex9527.android.action.MY_WIFI_STATE_CHANGED");
 * registerReceiver(receiver, filter, Manifest.permisson.ACCESS_WIFI_STATE, null);
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
