package com.lintex9527.runoob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lintex9527.custom.HelloIntentService;
import com.lintex9527.custom.HelloService;
import com.lintex9527.custom.MyPlayerService;

import static com.lintex9527.custom.MyPlayerService.KEY_CONTROL;

/**
 *
 * Service 使用详解
 * https://www.jianshu.com/p/95ec2a23f300
 * 通过Service 来播放音乐
 *
 *
 * Android 播放音频实例
 * https://blog.csdn.net/su20145104009/article/details/50640025
 */
public class TestServicePlayerActivity extends AppCompatActivity {

    private static final String TAG = TestServicePlayerActivity.class.getSimpleName();
    // 按钮点击次数计数器
    private int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service_player);
    }


    public void playMusic(View view) {
        Intent intent = new Intent(this, MyPlayerService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_CONTROL, MyPlayerService.Control.PLAY);
        intent.putExtras(bundle);
        startService(intent);
    }

    public void pauseMusic(View view) {
        Intent intent = new Intent(this, MyPlayerService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_CONTROL, MyPlayerService.Control.PAUSE);
        intent.putExtras(bundle);
        startService(intent);
    }

    public void stopMusic(View view) {
        Intent intent = new Intent(this, MyPlayerService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_CONTROL, MyPlayerService.Control.STOP);
        intent.putExtras(bundle);
        startService(intent);
    }

    /**
     * 发送按钮点击次数给IntentService
     * @param view
     */
    public void sendClickCount(View view) {
        long threadid = Thread.currentThread().getId();
        clickCount++;
        Log.d(TAG, "[" + threadid + "] 按钮线程发送数值:" + clickCount);

        Intent intent = new Intent(this, HelloIntentService.class);
        intent.putExtra(HelloIntentService.KEY_CLICK_COUNT, clickCount);
        startService(intent);
    }


    public void click2(View view) {
        long threadid = Thread.currentThread().getId();
        clickCount++;
        Log.d(TAG, "click2: clickCount = " + clickCount);

        Intent intent = new Intent(this, HelloService.class);
        intent.putExtra(HelloService.KEY_CLICK_COUNT, clickCount);
        startService(intent);
    }
}
