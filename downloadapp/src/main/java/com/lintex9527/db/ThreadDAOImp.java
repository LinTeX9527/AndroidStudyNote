package com.lintex9527.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lintex9527.entities.ThreadInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据访问接口的实现
 */
public class ThreadDAOImp implements ThreadDAO {

    private DBHelper mDBHelper = null;

    public ThreadDAOImp(Context context) {
        mDBHelper = new DBHelper(context);
    }


    /**
     * 数据库中插入一个 ThreadInfo 记录
     * @param threadInfo
     */
    @Override
    public void insertThread(ThreadInfo threadInfo) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL("insert into thread_info (thread_id, url, start_point, end_point, finished) values(?, ?, ?, ?, ?)",
                new Object[]{threadInfo.getId(), threadInfo.getUrl(), threadInfo.getStart(), threadInfo.getEnd(), threadInfo.getFinished()});

        db.close();
    }


    /**
     * 数据库中删除一个符合指定条件的 ThreadInfo 记录
     * @param url
     * @param thread_id
     */
    @Override
    public void deleteThread(String url, int thread_id) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL("delete from thread_info where url = ? and thread_id = ?",
                new Object[]{url, thread_id});

        db.close();
    }


    /**
     * 数据库中更新一个 ThreadInfo 中的 finished ，表示下载是否完成
     * @param url
     * @param thread_id
     * @param finished
     */
    @Override
    public void updateThread(String url, int thread_id, int finished) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL("update thread_info set finished = ? where url = ? and thread_id = ?",
                new Object[] {finished, url, thread_id});
        db.close();
    }


    /**
     * 根据 url 查询数据库中所有的下载的线程信息
     * @param url 网络文件地址
     * @return 所有下载此文件的线程记录
     */
    @Override
    public List<ThreadInfo> getThreads(String url) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        List<ThreadInfo> list = new ArrayList<ThreadInfo>();
        Cursor cursor = db.rawQuery("select * from thread_info where url = ?",
                new String[] {url});
        while (cursor.moveToNext()) {
            ThreadInfo threadInfo = new ThreadInfo();
            threadInfo.setId(cursor.getInt(cursor.getColumnIndex("thread_id")));
            threadInfo.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            threadInfo.setStart(cursor.getInt(cursor.getColumnIndex("start_point")));
            threadInfo.setEnd(cursor.getInt(cursor.getColumnIndex("end_point")));
            threadInfo.setFinished(cursor.getInt(cursor.getColumnIndex("finished")));

            list.add(threadInfo);
        }
        cursor.close();
        db.close();
        return list;
    }


    /**
     * 下载某个网络文件的线程是否存在
     * @param url 网络文件的url
     * @param thread_id 线程ID
     * @return 此线程存在则返回true，否则返回 false。
     */
    @Override
    public boolean isExists(String url, int thread_id) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from thread_info where url = ? and thread_id = ?",
                new String[] {url, thread_id + ""});
        boolean exists = cursor.moveToNext();
        cursor.close();
        db.close();
        return exists;
    }
}
