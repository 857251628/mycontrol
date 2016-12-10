package com.example.cailu.control.HttpDemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cailu.control.R;

/**
 * Created by cailu on 2016/7/27.
 */
public class ActivityHttp extends Activity implements View.OnClickListener {
    /*
    * 需要文件的信息和线程的信息，
    *文件信息就是被下载的文件。
    * 线程信息就是多线程的实例。
    *
    * Activity使用Intet将fileinfo对象传递给Service（需要创建Srevice类接收）
    *
    * */
    private TextView mName;
    private TextView mJindu;
    private Button mStart;
    private Button mStop;
    private ProgressBar mJindutiao;
    private Fileinfo mfileinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        initview();
        initdata();
    }


    private void initview() {
        mName = (TextView) findViewById(R.id.http_name);
        mJindu = (TextView) findViewById(R.id.http_jindu);
        mStart = (Button) findViewById(R.id.http_start);
        mStop = (Button) findViewById(R.id.http_stop);
        mJindutiao = (ProgressBar) findViewById(R.id.http_progressBar);
        mStart.setOnClickListener(this);
        mStop.setOnClickListener(this);
        mJindutiao.setMax(100);
    }

    private void initdata() {
        /*
        * 创建需要下载的文件对象。
        * */
        String s = "http://url.tduou.com/down/%E6%87%B5%E9%80%BC%E8%A1%A8%E6%83%85%E5%8C%85%E6%9C%80%E6%96%B0%E5%AE%8C%E6%95%B4%E7%89%88@83_1018634.exe";
        mfileinfo = new Fileinfo(0,s,"懵逼表情包最新完整版@83_1018634.exe" , 0, 0);

        mName.setText(mfileinfo.getFilename());
        //广播接收器的注册,  用完后要注销.
        //IntentFilter  意图过滤器
        IntentFilter filter = new IntentFilter();
        //addAction  添加操作
        filter.addAction(DowndloadService.ACTTION_UPDATE);
        // registerReceiver  代码注册广播接收器
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver  注销接收机
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.http_start:
               startFileInfo();
                break;
            case R.id.http_stop:
                stopFileInfo();
                break;
        }


    }

    /*
    * 用Intent给service传递需要的对象及状态。
    * */
    private void startFileInfo() {
        Intent intent=new Intent(this,DowndloadService.class);
        intent.setAction(DowndloadService.ACTTION_START);
        intent.putExtra("fileinfo",mfileinfo);
        startService(intent);
    }
    private void stopFileInfo() {
        Intent intent=new Intent(this,DowndloadService.class);
        intent.setAction(DowndloadService.ACTTION_STOP);
        intent.putExtra("fileinfo",mfileinfo);
        startService(intent);
    }

    /*
    * 更新ui的广播接收器
    * 需要进行注册 否则无效
    * */
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //根据返回值进行更新
            if (DowndloadService.ACTTION_UPDATE.equals(intent.getAction())) {
                //用一个值进行接收intent传过来的值
                int finished = intent.getIntExtra("finished", 0);
                //进行进度条的更新设置设置
                mJindutiao.setProgress(finished);
                Log.d("1111", "onReceive: "+finished);
            }
        }
    };


}
