package com.lintex9527.android.broadcastreceiverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by LinTeX9527 on 2018/8/12.
 * 这个广播接收器监听的action 是
 * com.lintex9527.android.action.WEATHER_CHANGED
 *
 * 而且广播发送这必须具备的权限是
 * com.lintex9527.android.permission.WEATHER_STATE
 */
public class WeatherBcReceiver extends BroadcastReceiver {

    private static final String TAG = WeatherBcReceiver.class.getSimpleName();
    public static final String LISTEN_ACTION = "com.lintex9527.android.action.WEATHER_CHANGED";
    public static final String KEY_MSG = "key_weather_msg";
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到了广播：" + intent.getStringExtra(KEY_MSG), Toast.LENGTH_SHORT).show();
    }
}
