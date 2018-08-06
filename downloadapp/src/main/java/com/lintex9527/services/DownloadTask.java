package com.lintex9527.services;

import android.content.Context;

import com.lintex9527.db.ThreadDAO;
import com.lintex9527.db.ThreadDAOImp;
import com.lintex9527.entities.FileInfo;
import com.lintex9527.entities.ThreadInfo;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 下载任务类 -- 对一个文件进行下载
 */
public class DownloadTask {
    private Context mContext = null;
    private FileInfo mFileInfo = null;
    // TODO: 这里为什么是接口，而不是用实现？
    private ThreadDAO mThreadDAO = null;

    public DownloadTask(Context context, FileInfo fileInfo) {
        mContext = context;
        mFileInfo = fileInfo;
        // TODO: 从这里明白，同一个接口可以套用不同的实现，所以上面应该定义接口类型的成员 mThreadDAO
        mThreadDAO = new ThreadDAOImp(mContext);
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
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                raf.seek(start); // 设置文件的写入位置
                // TODO: 开始下载

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
