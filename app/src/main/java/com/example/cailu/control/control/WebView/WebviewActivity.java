package com.example.cailu.control.control.WebView;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.example.cailu.control.R;

/**
 * Created by cailu on 2016/7/17.
 */
public class WebviewActivity extends Activity {
    private WebView mWebView;
    private Button mButton;
    private EditText mEditText;
    private WebSettings settings;
    private String string = "http://image.so.com/";
    private String string2 = "http://baidu.com";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebView = (WebView) findViewById(R.id.my_webview);
        mButton= (Button) findViewById(R.id.my_webview_button);
        mEditText= (EditText) findViewById(R.id.my_webview_edittext);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               string=mEditText.getText().toString();
                string="http://"+string;
                mEditText.setText("");
                tiaozhuang();

            }
        });
        webHtml();

    }

    private void webHtml() {

         settings = mWebView.getSettings();

        settings.setJavaScriptCanOpenWindowsAutomatically(true); //支持js
        settings.setJavaScriptEnabled(true);//支持插件
        settings.setSupportZoom(true);//支持缩放
        settings.setBuiltInZoomControls(true);//设置支持缩放
        settings.setUseWideViewPort(true);//将图片调整到适合webview的大小
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);//关闭webview中缓存

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局
        settings.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        // AppCache使我们能够有选择的缓冲web浏览器中所有的东西，从页面、图片到脚本、css等等。尤其在涉及到应用于网站的多个页面上的CSS和JavaScript文件的时候非常有用。其大小目前通常是5M。
        // 在Android上需要手动开启（setAppCacheEnabled），并设置路径（setAppCachePath）和容量（setAppCacheMaxSize）
      settings.setAppCacheEnabled(true);

     //   settings.setAppCachePath("data/");
      settings.setDomStorageEnabled(true);//开启DOM Storage
        //在App中重写此方法,不然会打开默认浏览器浏览设定的网页.
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        tiaozhuang();


    }

    private void tiaozhuang() {
        try {
            mWebView.loadUrl(string);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//    // 设置网页加载的进度条
//    @Override
//    public void onProgressChanged(WebView view, int newProgress) {
//        WebviewActivity.this.getWindow().setFeatureInt(
//                Window.FEATURE_PROGRESS, newProgress * 100);
//        super.onProgressChanged(view, newProgress);
//    };

    //重写了onKeyDown()可以使他在按手机返回键时返回上个页面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
//在最后的一页可以返回到主页面
        return super.onKeyDown(keyCode, event);
    }

}
