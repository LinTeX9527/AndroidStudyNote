package com.lintex9527.runoob_framelayout;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * 多个图片切换，形成动画的效果
 * http://www.runoob.com/w3cnote/android-tutorial-framelayout.html
 *
 * 重点是使用定时器发布消息给handler
 */
public class XiaojiejieActivity extends AppCompatActivity {

    private static final String TAG = XiaojiejieActivity.class.getName();

    private static final int UPDATE_IMAGE_CODE = 0x123;

    // 初始化帧布局
    FrameLayout mFrameLayout = null;

    // 自定义一个用户定时更新UI界面的handler类对象
    // TODO: 这个handler也会造成内存泄露
    private Handler mHandler;
    private Timer mTimer;

    void changePicture(int index) {
        Drawable a = getResources().getDrawable(R.drawable.meizitu5467_01);
        Drawable b = getResources().getDrawable(R.drawable.meizitu5467_02);
        Drawable c = getResources().getDrawable(R.drawable.meizitu5467_03);
        Drawable d = getResources().getDrawable(R.drawable.meizitu5467_04);
        Drawable e = getResources().getDrawable(R.drawable.meizitu5467_05);
        Drawable f = getResources().getDrawable(R.drawable.meizitu5467_06);
        Drawable g = getResources().getDrawable(R.drawable.meizitu5467_07);
        Drawable h = getResources().getDrawable(R.drawable.meizitu5467_08);

        Log.d(TAG, "changePicture + " + index);

        if (mFrameLayout != null) {
            switch (index) {

                case 0:
                    mFrameLayout.setForeground(a);
                    break;
                case 1:
                    mFrameLayout.setForeground(b);
                    break;
                case 2:
                    mFrameLayout.setForeground(c);
                    break;
                case 3:
                    mFrameLayout.setForeground(d);
                    break;
                case 4:
                    mFrameLayout.setForeground(e);
                    break;
                case 5:
                    mFrameLayout.setForeground(f);
                    break;
                case 6:
                    mFrameLayout.setForeground(g);
                    break;
                case 7:
                    mFrameLayout.setForeground(h);
                    break;
            }
        } // if (mFrameLayout != null)
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaojiejie);

        mFrameLayout = (FrameLayout) findViewById(R.id.myframe);

        mHandler = new Handler() {
            int step = 0;

            @Override
            public void handleMessage(Message msg) {
                if (msg.what == UPDATE_IMAGE_CODE) {
                    step++;
                    changePicture(step % 8);
                }
                super.handleMessage(msg);
            }
        };

        // 定义一个定时器对象，定时发送信息给handler
        // TODO: 即便从这个activity返回了，但是这个定时器依旧存在，有漏洞。
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 发送一条信息通知系统改变前景图片
                Log.d(TAG, "sendEmptyMessage()");
                mHandler.sendEmptyMessage(UPDATE_IMAGE_CODE);
            }
        }, 0, 150);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 覆盖了onDestroy()方法，看起来能消除内存泄露，因为logcat没有看到timer和handler在活动。
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
        mHandler = null;
        mTimer.cancel();
        mTimer.purge();
    }
}
