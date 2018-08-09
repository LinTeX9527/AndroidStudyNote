package com.lintex9527.android.broadcastreceiverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 这个广播接收器用静态注册的方式来监听广播
 * com.lintex9527.android.STATIC_BROADCAST
 *
 * 并且广播附带一个字符串，用Toast展示出来
 */
public class StaticReceiver extends BroadcastReceiver {

    public static final String KEY_STATIC_MSG = "key_static_msg";

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra(KEY_STATIC_MSG);
        Toast.makeText(context, "静态广播接收器消息：" + msg, Toast.LENGTH_SHORT).show();
    }
}
