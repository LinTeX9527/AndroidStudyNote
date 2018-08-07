package com.lintex9527.custom;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lintex9527.runoob.R;

/**
 * Service 使用详解
 * https://www.jianshu.com/p/95ec2a23f300
 * 通过Service 来播放音乐
 *
 *
 * Android 播放音频实例
 * https://blog.csdn.net/su20145104009/article/details/50640025
 */
public class MyPlayerService extends Service {

    private static final String TAG = MyPlayerService.class.getSimpleName();

    private MediaPlayer mMediaPlayer = null;

    private int startId;


    // 通过Intent传递数据时的 key 字符串
    public static final String KEY_CONTROL = "key_control";

    public enum Control {
        PLAY,
        PAUSE,
        STOP,
    };



    public MyPlayerService() {
        Log.d(TAG, "空的构造方法");
    }


    /**
     *
     */
    @Override
    public void onCreate() {
        if (mMediaPlayer == null) {
            // 加载音频文件，实例化 MediaPlayer 对象
            mMediaPlayer = MediaPlayer.create(this, R.raw.dear_diary);
            // 循环播放--否
            mMediaPlayer.setLooping(false);
        }
        Log.d(TAG, "onCreate() 加载音频资源");
        super.onCreate();
    }


    /**
     * 每一次回调 onStartCommand() 方法时，参数 startId 都是递增的，startId 用于唯一标识每次对 Service 发起的处理请求。
     * 如果服务同时处理多个onStartCommand() 请求，则不应在处理完一个启动请求之后立刻销毁服务，因为此时可能已经收到了新的
     * 启动请求，在第一个请求结束时停止服务会导致第二个请求被终止。为了避免这一问题，开始用 stopSelf(int) 确保服务停止
     * 请求始终基于最新一次的启动请求。也就是说如果调用 stopSelf(int)方法的参数值与onStartCommand() 接收到的最新的startId
     * 不相符的话，stopSelf() 方法就会失效，从而避免终止尚未处理的请求。
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId = startId;
        Log.d(TAG, "onStartCommand() 收到的 startId = " + startId);
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Control control = (Control) bundle.getSerializable(KEY_CONTROL);
            if (control != null) {
                switch (control) {
                    case PLAY:
                        play();
                        break;
                    case PAUSE:
                        pause();
                        break;
                    case STOP:
                        stop();
                        break;
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() 销毁Service");
        if(mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
        super.onDestroy();
    }


    private void play() {
        if (!mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
        }
    }


    private void pause() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }


    private void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
        stopSelf(startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() 绑定操作--暂未支持");
        throw new UnsupportedOperationException("Not Yet Implemented");
    }
}
