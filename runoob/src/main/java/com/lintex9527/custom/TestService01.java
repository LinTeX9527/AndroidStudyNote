package com.lintex9527.custom;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * http://www.runoob.com/w3cnote/android-tutorial-service-1.html
 *
 * 这个自定义的Service 必须要在 AndroidManifest.xml 中注册
 */
public class TestService01 extends Service {

    private static final String TAG = TestService01.class.getSimpleName();

    /**
     * 必须要实现的方法
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind() -- 绑定时被调用");
        return null;
    }


    /**
     * Service 被创建时调用
     */
    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate() -- Service 被创建时调用");
        super.onCreate();
    }


    /**
     * Service 被启动时调用
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand() -- Service 被启动时调用");
        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * Service 被关闭之前回调
     */
    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy() -- Service 被关闭之前");
        super.onDestroy();
    }
}
