package com.lintex9527.db;

import com.lintex9527.entities.ThreadInfo;

import java.util.List;

/**
 * 数据库访问接口
 */
public interface ThreadDAO {

    /**
     * 插入一个线程信息
     * @param threadInfo
     */
    public void insertThread(ThreadInfo threadInfo);

    /**
     * 删除线程
     * @param url
     * @param thread_id
     */
    public void deleteThread(String url, int thread_id);


    /**
     * 更新线程信息--完成进度
     * @param url
     * @param thread_id
     */
    public void updateThread(String url, int thread_id, int finished);


    /**
     * 查询文件的线程信息
     * @param url
     * @return
     */
    public List<ThreadInfo> getThreads(String url);


    /**
     * 线程信息是否已经存在
     * @param url
     * @param thread_id
     * @return
     */
    public boolean isExists(String url, int thread_id);
}
