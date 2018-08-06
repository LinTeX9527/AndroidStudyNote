package com.lintex9527.services;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lintex9527.entities.FileInfo;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Service 接收MainActivity传递过来的文件参数，启动新的线程来下载文件。
 * 最主要的方法是 onStartCommand() ，分析文件参数，启动下载子线程；
 * 下载线程访问网络，更新文件信息，并且回传给MainActivity。（在此文件的Handler中）
 */
public class DownloadService extends Service {

    private static final String TAG = DownloadService.class.getSimpleName();

    public static final String ACTION_START = "ACTION_START";
    public static final String ACTION_STOP = "ACTION_STOP";
    public static final String ACTION_UPDATE = "ACTION_UPDATE";
    public static final String KEY_FILEINFO = "FileInfo";
    // 设置下载文件的路径
    public static final String DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/downloads/";
//    public static final String DOWNLOAD_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/downloads/";

    public static final int MSG_INIT = 0x0;

    private DownloadTask mTask = null;

    /**
     * 处理子线程回传的信息
     */
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_INIT:
                    FileInfo fileInfo = (FileInfo) msg.obj;
                    Log.i(TAG, "handleMessage() 中的 " + fileInfo.toString());
                    // 在这里启动下载任务
                    mTask = new DownloadTask(DownloadService.this, fileInfo);
                    mTask.download();
                    break;
            }
        }
    };
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 获得Activity传来的参数
        if (ACTION_START.equals(intent.getAction())) {
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra(KEY_FILEINFO);
            Log.i(TAG, "START: " + fileInfo.toString());

            // 启动子线程
            new InitThread(fileInfo).start();

        } else if (ACTION_STOP.equals(intent.getAction())) {
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra(KEY_FILEINFO);
            Log.i(TAG, "STOP: " + fileInfo.toString());
            // 停止下载
            if (mTask != null) {
                mTask.isPause = true;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate() 创建服务");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() 销毁服务");
    }

    /**
     * 初始化子线程
     */
    class InitThread extends Thread {
        private FileInfo mFileInfo = null;

        public InitThread(FileInfo fileInfo) {
            mFileInfo = fileInfo;
        }

        @Override
        public void run() {
            HttpURLConnection conn = null;
            RandomAccessFile raf = null;
            try {
                // 连接网络文件
                URL url = new URL(mFileInfo.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                conn.setRequestMethod("GET");
                int length = -1;
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    // 获得文件长度
                    length = conn.getContentLength();
                }
                if (length <= 0) {
                    return;
                }
                // 在本地创建文件
                File dir = new File(DOWNLOAD_PATH);
                if (!dir.exists()) { // 如果不存在则创建目录
                    if (!dir.mkdirs()) { // 如果创建父目录失败，则直接返回
                        Log.i(TAG, "创建目录失败：" + dir.getAbsolutePath());
                        return;
                    }
                }
                File file = new File(dir, mFileInfo.getFileName());
                // 随机访问的输出流，可在任意位置读写
                raf = new RandomAccessFile(file, "rwd");
                // 设置本地文件长度
                raf.setLength(length);
                mFileInfo.setLength(length);

                // 需要把长度返回给Activity
                mHandler.obtainMessage(MSG_INIT, mFileInfo).sendToTarget();

            } catch (Exception e){
                e.printStackTrace();
            } finally {
                // 处理完毕则关闭输入输出流
                try {
                    if (conn != null) {
                        conn.disconnect();
                    }
                    if (raf != null) {
                        raf.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
