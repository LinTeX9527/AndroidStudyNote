package com.lintex9527.custom;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * IntentService 类是什么，以及意义
 * https://developer.android.com/guide/components/services#ExtendingIntentService
 *
 * 一个相关的例子
 * 多次点击发送启动多次IntentService
 * https://www.jianshu.com/p/95ec2a23f300
 *
 * 经过测试，按钮发送线程，即 TestServicePlayerActivity中的按钮所在的线程编号ID是2，而这个IntentService 的回调
 * 方法 onStartCommand() 所在线程编号是2，不过 onHandleIntent() 所在线程编号一直在变动，但连续的Intent 都处理完了，
 * 才销毁这个服务，即一个队列中的所有Intent都处理完了才销毁这个IntentService，而不是回调方法结束就立刻销毁。
 *
 */
public class HelloIntentService extends IntentService {

    private static final String TAG = HelloIntentService.class.getSimpleName();

    // 从Intent中接收按键点击的次数
    public static final String KEY_CLICK_COUNT = "click_count";

    /**
     * 【注意】
     * 必须要有这个构造方法，而且必须调用超类的方法传入一个字符串，标志这个类名称。
     */
    public HelloIntentService() {
        super("HelloIntentService");
    }


    /**
     * IntentService 从工作线程（工作线程是这个IntentService独有的，不同于主线程）中调用这个方法，这个 intent 就是启动服务的intent。
     * 当此方法结束时，IntentService 也被销毁。
     * 【注意】
     * 在 IntentService 中只有构造方法和这个 onHandleIntent() 方法是必须的，其他的方法可选，而且其他的方法必须
     * 返回默认的实现，以确保 IntentService 能够妥善处理工作线程的生命周期。
     * @param intent 启动服务的intent
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // 通常在这里执行工作，例如下载文件。
        long threadId = Thread.currentThread().getId();
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(100);
                Log.d(TAG, "[" + threadId + "] onHandleIntent() 收到的数值是：" + intent.getIntExtra(KEY_CLICK_COUNT, 0));
            } catch (InterruptedException e) {
                // 保存中断状态
                Thread.currentThread().interrupt();
            }
        }
    }


    /**
     * 这个方法不是必须要实现的，因为真正的工作应该要在 onHandleIntent() 中完成，即一个子线程中完成，而不是
     * onStartCommand() 所在的主线程中完成。
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Toast.makeText(this, TAG + " 启动了", Toast.LENGTH_SHORT).show();
        long threadId = Thread.currentThread().getId();
        Log.d(TAG, "[" + threadId + "] onStartCommand() 接收到的数值：" + intent.getIntExtra(KEY_CLICK_COUNT, 0));

        // 必须返回默认的实现，因为它会把 Intent 传递给 onHandleIntent() 方法
        return super.onStartCommand(intent, flags, startId);
    }
}
