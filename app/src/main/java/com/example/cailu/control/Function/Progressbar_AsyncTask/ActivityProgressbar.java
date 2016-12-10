package com.example.cailu.control.Function.Progressbar_AsyncTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cailu.control.R;

/**
 * Created by cailu on 2016/7/22.
 */
public class ActivityProgressbar extends Activity {
    private ProgressBar mprogressBar;
    private TextView mtextView;
    private MyAsyncTask mAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        mprogressBar = (ProgressBar) findViewById(R.id.my_progressbar);
        mtextView = (TextView) findViewById(R.id.my_progressbar_jindu);
        mAsyncTask = new MyAsyncTask(this);
        mAsyncTask.setProgressBar(mprogressBar);
        mAsyncTask.setTestview(mtextView);
        mAsyncTask.execute(true);//启动AsyncTask
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAsyncTask != null && mAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            //AsyncTask不可强行关闭。
            //对当前 线程进行标记。好在AsyncTask内部将耗时操作取消，以达到停止该线程的目的。
            mAsyncTask.cancel(true);
        }
    }
}
