package com.example.cailu.control.Tools;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.cailu.control.MainActivity.MyApplication;

import java.util.Map;

/**
 * Created by cailu on 2016/7/23.
 */
public class VolleyTool {
    public VolleyTool() {

    }

    public static void volleyPost(String url, VolleyInterface volleyInterface, final Map map) {
        //使用队列前将要使用的队列重置（就是关闭）确保队列没有被使用。
        MyApplication.getmQueue().cancelAll(volleyInterface.getmContext());
                                                                             //调用了 成功和失败的方法。
        StringRequest request = new StringRequest(Request.Method.POST, url, volleyInterface.getSuccessVolleyListener(), volleyInterface.getErrorVolleyListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        request.setTag(volleyInterface.getmContext());
        MyApplication.getmQueue().add(request);
    }

    public static void volleyGet(String url, VolleyInterface volleyInterface) {
        //使用队列前将要使用的队列重置（就是关闭）确保队列没有被使用。
        MyApplication.getmQueue().cancelAll(volleyInterface.getmContext());
                                                                            //调用了 成功和失败的方法。
        StringRequest request = new StringRequest(Request.Method.GET, url, volleyInterface.getSuccessVolleyListener(), volleyInterface.getErrorVolleyListener()) {
        };
        request.setTag(volleyInterface.getmContext());
        MyApplication.getmQueue().add(request);
    }

    //匿名内部类
    public static abstract class VolleyInterface {
        public Context mContext;

        public VolleyInterface(Context context) {
            mContext = context;
        }
        //成功的方法
        protected abstract void MyVolleyScuccessListener(String str);
        //失败的方法
        protected abstract void MyVolleyErrorListener(VolleyError error);

        private Context getmContext() {
            return  mContext;
        }

        Response.Listener getSuccessVolleyListener() {
            return new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //成功的方法
                    MyVolleyScuccessListener(response);
                    Toast.makeText(VolleyInterface.this.mContext, "成功", Toast.LENGTH_SHORT).show();
                }
            };
        }

        Response.ErrorListener getErrorVolleyListener() {
            return new Response.ErrorListener() {
                @Override

                public void onErrorResponse(VolleyError error) {
                    //失败的方法
                    MyVolleyErrorListener(error);
                }
            };
        }
    }

}

