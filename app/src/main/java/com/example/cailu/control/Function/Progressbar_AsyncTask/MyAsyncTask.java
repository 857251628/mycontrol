package com.example.cailu.control.Function.Progressbar_AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by cailu on 2016/7/22.
 */
public class MyAsyncTask extends AsyncTask<Boolean, Integer, Boolean> {
    private final Context mContext;
    private ProgressBar progressBar;
    private TextView mTestview;


    public MyAsyncTask(Context context) {
        this.mContext = context;
    }

    /*
        * 执行后台操作前需被调用，可以做一些初始化操作
        * */
    @Override
    protected void onPreExecute() {
        progressBar.setMax(100);
        progressBar.setProgress(0);
        mTestview.setText(0 + "%");
        super.onPreExecute();
    }

    /*
    * 当doInBackground（）；完成后自动调用并可以拿到doInBackground（）返回的值
    *
    * */
    @Override
    protected void onPostExecute(Boolean flag) {
        super.onPostExecute(flag);
        if (flag) {
            Toast.makeText(mContext, "成功", Toast.LENGTH_LONG).show();
        }
    }

    /*
    * 在doInBackground（）；调用publishProgress（）；更新任务的执行进度后，会触发此方法。
    * */
    @Override
    protected void onProgressUpdate(Integer... values) {
        if (isCancelled()) {
            return;
        }
        progressBar.setProgress(values[0]);
        mTestview.setText(values[0] + "%");
    }

    /*
    * 你需要后台去完成的事情，此为异步执行后台线程
    * */
    @Override
    protected Boolean doInBackground(Boolean... params) {
        int i = 0;
        for (; i <= 100; i++) {
            if (isCancelled()) {
                break;
            }
            SystemClock.sleep(50);
            publishProgress(i);
        }
        if (i >= 100) {
            return true;
        } else {
            return false;
        }
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void setTestview(TextView testview) {
        this.mTestview = testview;
    }
}
