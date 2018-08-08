package com.lintex9527.custom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 自定义的广播接收器，模拟接收到火警报警
 *
 * 这个广播接收器用静态注册方式，直接在AndroidManifest.xml 中注册。
 */
public class FireAlarmBroadcastReceiver extends BroadcastReceiver {

    public static final String ACTION_NAME = "com.lintex9527.custom.BROADCAST_FIREALARM";


    /**
     * 接收到广播
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_NAME.equals(intent.getAction())) {
            Toast.makeText(context, "收到火警报警", Toast.LENGTH_SHORT).show();
        }
    }
}
