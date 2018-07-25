package com.lintex9527.runoob;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Notification 状态栏通知
 * http://www.runoob.com/w3cnote/android-tutorial-notification.html
 *
 * 更详细的使用方法参见：
 * https://blog.csdn.net/vipzjyno1/article/details/25248021
 * 
 */
public class TestNotificationActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = TestNotificationActivity.class.getName();

    private Context mContext;
    private NotificationManager mNotificationManager;
    private Notification mNotification;
    Bitmap largeBitmap = null;
    private static final int NOTIFYID_01 = 1;

    private Button btnShowNormal;
    private Button btnCloseNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_notification);

        mContext = TestNotificationActivity.this;
        // 创建大图标的Bitmap
        largeBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.iv_lc_icon);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        bindViews();
    }

    private void bindViews() {
        btnShowNormal = (Button) findViewById(R.id.btn_show_normal);
        btnCloseNormal = (Button) findViewById(R.id.btn_close_normal);
        btnShowNormal.setOnClickListener(this);
        btnCloseNormal.setOnClickListener(this);
    }

    /**
     * 控件单击时的响应
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_normal:
                Log.d(TAG, "显示通知");
                // 定义一个PendingIntent 点击Notification 之后启动一个Activity
                Intent intent = new Intent(mContext, OtherActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);

                // 设置图片，通知图标，发送时间，提示方式等属性
                Notification.Builder mBuilder = new Notification.Builder(this);
                mBuilder.setContentTitle("叶良辰")
                        .setContentText("我有一百种方法让你待不下去")
                        .setSubText("---记住我叫叶良辰")
                        .setTicker("收到叶良辰发送过来的信息")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_lol_icon)
                        .setLargeIcon(largeBitmap)
                        .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);
                mNotification = mBuilder.build();
                mNotificationManager.notify(NOTIFYID_01, mNotification);
                break;
            case R.id.btn_close_normal:
                // 除了可以根据ID来取消Notification 之外，还可调用 cancelAll() 取消应用的所有同志
                mNotificationManager.cancel(NOTIFYID_01);
                Log.d(TAG, "取消通知");
                break;
        }
    }
}
