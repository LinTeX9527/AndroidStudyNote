package com.lintex9527.android.sendbroadcastapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class LocalReceiver extends BroadcastReceiver {

    // 监听的广播的action
    public static final String RECEIVER_ACTION = "com.lintex9527.android.action.LOCAL_BROADCAST";
    // 接收的本地广播中包含的一条信息
    public static final String KEY_LOCAL_MSG = "key_local_msg";

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra(KEY_LOCAL_MSG);
        Toast.makeText(context, "本地广播接收器收到的消息：" + msg, Toast.LENGTH_SHORT).show();
    }
}
