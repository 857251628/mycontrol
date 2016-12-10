package com.example.cailu.control.Function.VolleyDemo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.cailu.control.R;
import com.example.cailu.control.Tools.VolleyTool;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cailu on 2016/7/23.
 */
public class ActivityVolley extends Activity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        Volley_get();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /*
    * Volley提供了JsonObjectRequest、JsonArrayRequest、StringRequest等Request形式。
    * JsonObjectRequest：返回JSON对象。
     JsonArrayRequest：返回JsonArray。
     StringRequest：返回String，这样可以自己处理数据，更加灵活。
    * */
    private void Volley_get() {
       /* String url="https://www.baidu.com/";
        StringRequest mRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ActivityVolley.this, "成功"+response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityVolley.this, "失败"+error, Toast.LENGTH_SHORT).show();
            }
        }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map =new HashMap<>();
                    map.put("","");
                    map.put("","");
                    return map;
                }
        };
        mRequest.setTag("cheshiVolley");
        MyApplication.getmQueue().add(mRequest);*/

        VolleyTool.volleyGet("https://www.baidu.com/", new VolleyTool.VolleyInterface(this) {

            @Override
            protected
                //实现成功的方法
            /*
            * 可以做网络数据 成功之后要做的事情
            * */
            void MyVolleyScuccessListener(String str) {
                Toast.makeText(ActivityVolley.this, "lalal" + str, Toast.LENGTH_SHORT).show();


            }

            @Override
            protected
                //实现失败的方法
            void MyVolleyErrorListener(VolleyError error) {
                Toast.makeText(ActivityVolley.this, "失败", Toast.LENGTH_SHORT).show();
            }
        });
        Map<String, String> map = new HashMap<>();
        map.put("", "");
        map.put("", "");
        VolleyTool.volleyPost("https://www.baidu.com/", new VolleyTool.VolleyInterface(this) {
            @Override
            protected void MyVolleyScuccessListener(String str) {
                Toast.makeText(ActivityVolley.this, "lalal" + str, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void MyVolleyErrorListener(VolleyError error) {
                Toast.makeText(ActivityVolley.this, "失败", Toast.LENGTH_SHORT).show();
            }
        }, map);


    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ActivityVolley Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
