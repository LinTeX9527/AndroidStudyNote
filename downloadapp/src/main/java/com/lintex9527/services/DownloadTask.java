package com.lintex9527.services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lintex9527.db.ThreadDAO;
import com.lintex9527.db.ThreadDAOImp;
import com.lintex9527.entities.FileInfo;
import com.lintex9527.entities.ThreadInfo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * 下载任务类 -- 对一个文件进行下载
 */
public class DownloadTask {

    private static final String TAG = DownloadTask.class.getSimpleName();

    // 通过广播接收发出通知，当前文件下载进度是多少，百分比形式
    public static final String UPDATE_PROGRESS_VALUE = "download_percent";

    private Context mContext = null;
    private FileInfo mFileInfo = null;
    // TODO: 这里为什么是接口，而不是用实现？
    private ThreadDAO mThreadDAO = null;

    private int mFinished = 0; // 已经下载的进度，百分比形式

    private boolean flag_stop = false; // 决定下载线程是否继续
    public DownloadTask(Context context, FileInfo fileInfo) {
        mContext = context;
        mFileInfo = fileInfo;
        // TODO: 从这里明白，同一个接口可以套用不同的实现，所以上面应该定义接口类型的成员 mThreadDAO
        mThreadDAO = new ThreadDAOImp(mContext);
    }


    /**
     * 外部条件出发才能改变停止位
     */
    public void stopDownload() {
        flag_stop = true;
    }

    public void download () {
        // 从数据库中读取上一次下载的进度信息，然后在启动线程开始下载
        List<ThreadInfo> threadInfos = mThreadDAO.getThreads(mFileInfo.getUrl());
        ThreadInfo threadInfo = null;
        if (threadInfos.size() == 0) { // 如果是第一次下载
            threadInfo = new ThreadInfo(0, mFileInfo.getUrl(), 0, mFileInfo.getLength(), 0);
        } else { // 已经存在下载记录
            threadInfo = threadInfos.get(0);
        }
        // 创建子线程进行下载
        new DownloadThread(threadInfo).start();
    }

    /**
     * 下载线程
     */
    class DownloadThread extends Thread {
        private ThreadInfo mThreadInfo = null;

        public DownloadThread(ThreadInfo threadInfo) {
            mThreadInfo = threadInfo;
        }


        @Override
        public void run() {
            // 向数据库插入线程信息
            if (!mThreadDAO.isExists(mThreadInfo.getUrl(), mThreadInfo.getId())) { // 不存在则插入
                mThreadDAO.insertThread(mThreadInfo);
            }

            HttpURLConnection conn = null;
            InputStream input = null;
            RandomAccessFile raf = null;
            // 设置下载位置
            try {
                URL url = new URL(mThreadInfo.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000); // 超时时间3000毫秒
                conn.setRequestMethod("GET");
                // 设置下载的范围
                int start = mThreadInfo.getStart() + mThreadInfo.getFinished();
                conn.setRequestProperty("Range", "bytes=" + start + "-" + mThreadInfo.getEnd());
                // 设置文件的写入位置
                File file = new File(DownloadService.DOWNLOAD_PATH, mFileInfo.getFileName());
                raf = new RandomAccessFile(file, "rwd");
                raf.seek(start); // 设置文件的写入位置
                // TODO: 开始下载
                Intent intent = new Intent(DownloadService.ACTION_UPDATE);
                mFinished += mThreadInfo.getFinished();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_PARTIAL || conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    // 读取数据
                    input = conn.getInputStream();
                    byte[] buffer = new byte[1024 * 4];
                    int len = -1; // 每一次读取的长度
                    long time = System.currentTimeMillis();
                    while ((len = input.read(buffer)) != -1) {
                        // 写入文件
                        raf.write(buffer, 0, len);
                        // 把下载进度发送广播给Activity
                        mFinished += len; // 更新已经下载的进度，百分比形式
                        if (System.currentTimeMillis() - time > 500 || (mFinished * 100 / mFileInfo.getLength() >= 98)) { // 每隔500毫秒发送一次广播
                            time = System.currentTimeMillis();
                            intent.putExtra(UPDATE_PROGRESS_VALUE, mFinished * 100 / mFileInfo.getLength());
                            mContext.sendBroadcast(intent);
                            Log.d(TAG, "下载总字节个数：" + mFinished);
                        }
                        // 下载暂停时，保存下载进度
                        if (flag_stop) {
                            Log.d(TAG, "flag_stop 让下载停止");
                            mThreadDAO.updateThread(mThreadInfo.getUrl(),
                                    mThreadInfo.getId(),
                                    mFinished);
                            return; // 退出循环，不再下载
                        }
                    }

                    Log.d(TAG, "退出while时下载总的字节个数： " + mFinished);
                    // 删除线程信息
                    mThreadDAO.deleteThread(mThreadInfo.getUrl(), mThreadInfo.getId());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Log.d(TAG, "DownloadTask 结束，关闭输入输出流");
                if (conn != null) {
                    try {
                        conn.disconnect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (raf != null) {
                    try {
                        raf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
