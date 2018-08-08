package com.lintex9527.custom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 动态注册广播接收器，监听网络状态发生改变
 * http://www.runoob.com/w3cnote/android-tutorial-broadcastreceiver.html
 */
public class WifiStateBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = WifiStateBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        // 其实这里加不加这个 if 对 intent.getAction() 判断无所谓，因为动态注册的时候限制了这个
        // 广播接收器只关注 "android.net.conn.CONNECTIVITY_CHANGE" 这个广播接收器，
        // 根据文档，这里应当更应该关注 intent.getData()
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            Toast.makeText(context, "网络状态发生变化", Toast.LENGTH_SHORT).show();
        }
    }
}
