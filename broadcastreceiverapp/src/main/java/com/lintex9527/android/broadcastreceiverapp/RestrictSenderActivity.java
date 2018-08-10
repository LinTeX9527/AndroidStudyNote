package com.lintex9527.android.broadcastreceiverapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class RestrictSenderActivity extends AppCompatActivity {

    private static final String TAG = RestrictSenderActivity.class.getSimpleName();
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restrict_sender);
        mContext = RestrictSenderActivity.this;
    }


    /**
     * 点击按钮发送广播 <action android:name="com.lintex9527.android.action.MY_WIFI_STATE_CHANGED"/>
     * 但是因为这个APP 没有申请权限 android:permission="android.permission.ACCESS_WIFI_STATE"
     * 所以广播接收器是无法收到这个广播的。
     *
     * TODO: debug 模式居然能收到这个广播，为什么？ release 模式能不能收到？
     * @param view
     */
    public void send(View view) {
        Log.d(TAG, "发送了MY_WIFI_STATE_CHANGED 广播");
        Intent intent = new Intent();
        intent.setAction("com.lintex9527.android.action.MY_WIFI_STATE_CHANGED");
        intent.putExtra(WifiStateReceiver.KEY_WIFISTATE_MSG, "不应该收到这条消息");
        sendBroadcast(intent);
    }

    /**
     * 点击返回按钮回到应用主界面
     * @param view
     */
    public void backHome(View view) {
        if (view.getId() == R.id.btn_backhome) {
            Log.d(TAG, "返回主页面");
            finish(); // 在启动新的Activity之前最好新销毁当前这个Activity，否则不会调用本身的onDestroy()方法
            startActivity(new Intent(mContext, MainActivity.class));
        }
    }


    @Override
    protected void onDestroy() {
        Log.d(TAG, "活动被销毁");
        super.onDestroy();
    }

}
