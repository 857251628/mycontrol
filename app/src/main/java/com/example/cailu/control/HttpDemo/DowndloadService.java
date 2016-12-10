package com.example.cailu.control.HttpDemo;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import java.io.File;

import java.io.RandomAccessFile;
import java.net.HttpURLConnection;

import java.net.URL;

/**
 * Created by cailu on 2016/7/28.
 */
public class DowndloadService extends Service {

    public static final String DOWNLOAD_PATH =
            //获取app的根目录。
            Environment.getExternalStorageDirectory().getAbsolutePath() + "/downloads/";
    /*
    * 用于标记状态。
    *Service要到配置文件进行声明。
    */
    public static final int MSG_INIT = 0;
    public static final String ACTTION_START = "ACTTION_START";
    public static final String ACTTION_STOP = "ACTTION_STOP";
    public static final String ACTTION_UPDATE = "ACTTION_UPDATE";
    private DownLoadTask mLoadTask = null;
    /*
    * 此方法是用来接收Activity传过来的数据。
    * */
    private static final String TAG = "1DowndloadService";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /*
        * 根据接收过来的不同数据进行不同的处理。
        * 获得到不同的对象
        * */

        if (ACTTION_START.equals(intent.getAction())) {
            //开始
            Fileinfo file = (Fileinfo) intent.getSerializableExtra("fileinfo");
            Log.i(TAG, "onStartCommand: ACTTION_START" + file);
            //启动子线程
           new InitThread(file).start();
        } else if (ACTTION_STOP.equals(intent.getAction())) {
            //关闭
            Fileinfo file = (Fileinfo) intent.getSerializableExtra("fileinfo");
            Log.i(TAG, "onStartCommand: ACTTION_STOP" + file);
            //判断子线程是否存在
            if (mLoadTask != null) {
                //将Ispause 设置为true是停止.
                mLoadTask.setIspause(true);
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //创建handler线程
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_INIT:
                    Fileinfo fileinfo = (Fileinfo) msg.obj;
                    Log.i(TAG, "handleMessage: " + fileinfo);
                    //启动下载线程
                    mLoadTask = new DownLoadTask(DowndloadService.this);
                    mLoadTask.setmFileinfo(fileinfo);
                    mLoadTask.download();
                    break;
            }

        }
    };

    /*初始化子线程
    * */
    class InitThread extends Thread {
        private Fileinfo mFile = null;

        public InitThread(Fileinfo mFile) {
            this.mFile = mFile;
        }

        @Override
        public void run() {

            HttpURLConnection conn = null;
            RandomAccessFile randomAccessFile = null;
            try {
                // 连接网络文件。
                URL url = new URL(mFile.getUrl());
                conn = (HttpURLConnection) url.openConnection();

                conn.setConnectTimeout(3000);
                conn.setRequestMethod("GET");
                conn .setRequestProperty("Accept-Encoding", "identity");
                conn.connect();
                int length = -1;
                if (conn.getResponseCode() == 200) {
                    ////--------------------------------////
                    length = conn.getContentLength();
                }
                Log.d(TAG, "run: "+length);

                if (length <= 0) {
                    return;
                }
                //文件地址
                File dir = new File(DOWNLOAD_PATH);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                // 创建本地文件。
                File file = new File(dir, mFile.getFilename());
                //RandomAccessFile 是特殊的输出流可在文件任意位置进行写出。
                randomAccessFile = new RandomAccessFile(file, "rwd");
                // 获得文件的长度（大小）；
                randomAccessFile.setLength(length);
                mFile.setLength(length);
                mHandler.obtainMessage(MSG_INIT, mFile).sendToTarget();
                // 设置本地文件的长度（大小）。
            } catch (Exception e) {
                e.printStackTrace();

            } finally {

                try {
                    randomAccessFile.close();
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }

}
