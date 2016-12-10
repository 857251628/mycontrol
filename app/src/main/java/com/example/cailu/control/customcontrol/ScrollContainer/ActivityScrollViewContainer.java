package com.example.cailu.control.customcontrol.ScrollContainer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.cailu.control.R;

public class ActivityScrollViewContainer extends AppCompatActivity implements View.OnClickListener {

    //自定义的相对布局，就是整体布局
    private ScrollViewContainer svc;

    //上半部分的scrollview
    private ScrollView mScrollView;
    //下半部分的webview
    private WebView mWebView;

    //“置顶”的图片
    private ImageView ivTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview_container);

        initView();
    }

    private void initView() {
        svc = (ScrollViewContainer) findViewById(R.id.svc);
        ivTop = (ImageView) findViewById(R.id.iv_top);
        ivTop.setOnClickListener(this);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl("http://image.so.com/");
        mScrollView = (ScrollView) findViewById(R.id.scrollview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_top:
                //置顶
                svc.setTop();
                mWebView.scrollTo(0, 0);
                mScrollView.scrollTo(0, 0);
                mScrollView.smoothScrollBy(0, 0);
                break;
        }
    }
}
