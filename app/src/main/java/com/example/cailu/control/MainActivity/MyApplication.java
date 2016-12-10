package com.example.cailu.control.MainActivity;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by cailu on 2016/7/23.
 */
public class MyApplication extends Application {
    private static RequestQueue mQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mQueue=Volley.newRequestQueue(getApplicationContext());
    }
    public static RequestQueue getmQueue(){
        return mQueue;
    }
}
