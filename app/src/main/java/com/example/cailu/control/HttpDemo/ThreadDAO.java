package com.example.cailu.control.HttpDemo;

import java.util.List;

/**
 * 数据访问接口
 * Created by cailu on 2016/8/4.
 */
public interface ThreadDAO {
    /*
    * 插入线程信息        threadInfo
    * */
    public void insertThread(ThreadInfo threadInfo);

    /*
    * 删除线程
    * 删除哪里的线程。      url
    * 删除哪个的线程。      thread_id
    * */
    public void deleteThread(String url, int thread_id);

    /*
    * 更新线程下载进度
    *更新哪里的线程。      url
    * 更新哪个的线程。      thread_id
    * 更新的进度         finished
    * */
    public void updateThread(String url, int thread_id, int finished);

    /*
    * 查询文件的线程信息
    *查询哪里的文件的线程信息  url
    * */
    public List<ThreadInfo> getThreadinfo(String url);

    /*
    * 线程是否存在
    * */
    public boolean isExists(String url, int thread_id);
}
