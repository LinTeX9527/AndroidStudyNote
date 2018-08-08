package com.lintex9527.custom;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 绑定服务的例子
 * http://www.runoob.com/w3cnote/android-tutorial-service-1.html
 */
public class ExamBinderService extends Service {

    private static final String TAG = ExamBinderService.class.getSimpleName();

    private int count = 0;

    private boolean quit = false;

    private MyBinder mBinder = new MyBinder();

    public class MyBinder extends Binder {
        public int getCount() {
            return count;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() 绑定服务");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate() 创建服务");
        new Thread() {
            @Override
            public void run() {
                while (!quit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        } .start();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind() 解除绑定");
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        this.quit = true;
        Log.d(TAG, "onDestory() 销毁服务");
    }


    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind() 重新绑定");
        super.onRebind(intent);
    }
}
