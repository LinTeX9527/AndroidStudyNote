package com.lintex9527.custom;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


/**
 * 扩展服务类 -- 多线程
 * https://developer.android.com/guide/components/services#ExtendingService
 *
 * 使用 IntentService 显著简化了启动服务的实现。但是若要求服务执行多线程（而不是通过工作队列处理启动请求），
 * 则可扩展Service类来处理每个Intent。
 *
 * 为了便于比较 HelloIntentService，以下提供了 Service 类实现的代码示例，对于每一个启动请求，
 * 它均使用工作线程执行作业，且每次仅处理一个请求。
 *
 * TODO: 服务销毁的问题
 * 经过修改，onStartCommand() 中每次都启动一个新的线程来处理一个Intent。虽然能新建线程，但是线程何时终止，还不清楚。
 * 以及每次调用 stopSelf() 能否终止也有问题。
 *
 */
public class HelloService extends Service {
    private static final String TAG = HelloService.class.getSimpleName();

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    public static final String KEY_CLICK_COUNT = "click_count";

    // Handler that receives message from the thread.
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            try {
                long threadid = Thread.currentThread().getId();
                Log.d(TAG, "[" + threadid + "] ServiceHandler 在处理消息，参数 = " + msg.arg2);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // Restore the Interrupt status.
                Thread.currentThread().interrupt();
            }
            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job.
//            stopSelf(msg.arg1);

            // 如果要一个线程处理一个Intent，则必须优先销毁当前的服务，而不是 stopSelf(msg.arg1);
            stopSelf();
        }
    }

    @Override
    public void onCreate() {
        // Start up the thread running the service. Not that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block. We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
//        HandlerThread thread = new HandlerThread("ServiceStartArguments",
//                                                Process.THREAD_PRIORITY_BACKGROUND);
//        thread.start();

        long threadid = Thread.currentThread().getId();
        Log.d(TAG, "[" + threadid + "] onCreate() 服务创建");

//        mServiceLooper = thread.getLooper();
//        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        long threadid = Thread.currentThread().getId();

        Log.d(TAG, "[" + threadid + "] onStartCommand() 服务启动");

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////     每一次onStartCommand   都启动一个新的线程  /////////////////////
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
        ////////////////////////////////////////////////////////////////////////////////////////////

        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        msg.arg2 = intent.getIntExtra(KEY_CLICK_COUNT, 0);
        mServiceHandler.sendMessage(msg);

        // If we get killed, after returning from here, restart.
//        return START_STICKY;

//        return START_NOT_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding. so return null.
        return null;
    }

    @Override
    public void onDestroy() {
        long threadid = Thread.currentThread().getId();
        Log.d(TAG, "[" + threadid + "] onDestory() 服务被销毁");
        super.onDestroy();
    }
}
