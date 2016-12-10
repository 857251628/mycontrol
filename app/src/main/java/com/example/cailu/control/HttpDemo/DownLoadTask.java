package com.example.cailu.control.HttpDemo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by cailu on 2016/8/5.
 * 下载任务类
 */
public class DownLoadTask {
    private Context mContext=null;
    private ThreadInfo mThreadInfo=null;
    private ThreadDAO mThreadDAO=null;
    private int mFinished = 0;
    private Fileinfo mFileinfo;
    private boolean ispause = false;

    public DownLoadTask(Context mContext) {
        this.mContext = mContext;
        mThreadDAO=new ThreadDAOImpl(mContext);
    }

    public boolean ispause() {
        return ispause;
    }

    public void setIspause(boolean ispause) {
        this.ispause = ispause;
    }



    public Fileinfo getmFileinfo() {

        return mFileinfo;
    }

    public void setmFileinfo(Fileinfo mFileinfo) {
        this.mFileinfo = mFileinfo;
    }


    public ThreadInfo getmThreadInfo() {
        return mThreadInfo;
    }

    public void setmThreadInfo(ThreadInfo mThreadInfo) {
        this.mThreadInfo = mThreadInfo;
    }



    public void download() {
        //读取线程信息
        String url = mFileinfo.getUrl();
        List<ThreadInfo> threadinfo = mThreadDAO.getThreadinfo(mFileinfo.getUrl());
        //  如果是第一次使用那么就没有线程信息我们就需要自己创建线程
        //不是就从数组中取出
        ThreadInfo info = null;
        if (threadinfo.size() == 0) {
            //初始化线程信息
            info = new ThreadInfo(0, mFileinfo.getUrl(), 0, mFileinfo.getLength(), 0);
        } else {
            info=threadinfo.get(0);
        }
        //创建子线程进行下载
        DownLoadThread downLoadThread = new DownLoadThread();
        downLoadThread.setThreadInfo(info);
        downLoadThread.start();
    }


    /*
    *
    * 下载线程
    * */
    class DownLoadThread extends Thread {
        private ThreadInfo threadInfo = null;


        public ThreadInfo getThreadInfo() {
            return threadInfo;
        }

        public void setThreadInfo(ThreadInfo threadInfo) {
            this.threadInfo = threadInfo;
        }

        @Override
        public void run() {
            //向数据库 插入线程信息
            //判断线程是否存在
            if (!mThreadDAO.isExists(threadInfo.getUrl(), threadInfo.getId())) {
                //不存在就可以插入线程信息
                mThreadDAO.insertThread(threadInfo);
            }
            HttpURLConnection connection = null;
            RandomAccessFile randomAccessFile = null;
            InputStream inputStream = null;
            try {
                URL url = new URL(threadInfo.getUrl());
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(3000);
                connection.setRequestMethod("GET");
                //设置下载位置
                int start = threadInfo.getStart() + threadInfo.getFinished();
                //Range 范围
                connection.setRequestProperty("Range", "bytes=" + start + "-" + threadInfo.getEnd());
                //设置文件写入位置
                File file = new File(DowndloadService.DOWNLOAD_PATH, mFileinfo.getFilename());
                randomAccessFile = new RandomAccessFile(file, "rwd");
                //设置从哪里开始的方法  seek();跳过传入的值.
                randomAccessFile.seek(start);
                Intent intent = new Intent(DowndloadService.ACTTION_UPDATE);
                mFinished += threadInfo.getFinished();
                //开始下载
                //206的意思是下载其中的一段
                if (connection.getResponseCode() == 206) {
                    //读取数据
                    inputStream = connection.getInputStream();
                    byte[] bytes = new byte[1024 * 4];
                    int len = -1;
                    long time = System.currentTimeMillis();
                    while ((len = inputStream.read(bytes)) != -1) {
                        //写入文件
                        randomAccessFile.write(bytes, 0, len);
                        //把下载进度用广播发送给activity
                        mFinished += len;
                        //给intent添加数据,为防止频繁发送广播更新UI添加判断
                        if (System.currentTimeMillis() - time > 500) {
                            time = System.currentTimeMillis();
                            intent.putExtra("finished", mFinished * 100 / mFileinfo.getLength());
                            //发送广播
                            mContext.sendBroadcast(intent);
                        }

                        //下载暂停时要保存状态并退出;
                        if (ispause) {
                            mThreadDAO.updateThread(threadInfo.getUrl(), threadInfo.getId(), mFinished);
                            return;
                        }

                    }
                    intent.putExtra("finished", mFinished * 100 / mFileinfo.getLength());
                    //发送广播
                    mContext.sendBroadcast(intent);

                    //下载完成后需要线程就没用了可以删除
                    mThreadDAO.deleteThread(threadInfo.getUrl(), threadInfo.getId());
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //关闭各种流
                try {
                    connection.disconnect();
                    randomAccessFile.close();
                    inputStream.close();

                    Log.d("1111", "run: "+mFileinfo.getLength());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
