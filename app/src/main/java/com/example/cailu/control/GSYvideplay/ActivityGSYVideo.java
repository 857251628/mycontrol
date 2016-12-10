package com.example.cailu.control.GSYvideplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.cailu.control.GSYvideplay.utils.JumpUtils;
import com.example.cailu.control.R;

import com.shuyu.gsyvideoplayer.utils.Debuger;



public class ActivityGSYVideo extends AppCompatActivity implements View.OnClickListener {


    private Button openbtn;
    private Button listbtn;
    private Button listbtn2;
    private Button listdetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsyvideo);
        initView();
        initonclick();
        Debuger.enable();

    }

    private void initonclick() {
        openbtn.setOnClickListener(this);
        listbtn.setOnClickListener(this);
        listbtn2.setOnClickListener(this);
        listdetail.setOnClickListener(this);
    }

    private void initView() {
        openbtn = (Button) findViewById(R.id.open_btn);
         listbtn = (Button) findViewById(R.id.list_btn);
         listbtn2 = (Button) findViewById(R.id.list_btn_2);
       listdetail = (Button) findViewById(R.id.list_detail);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.open_btn:
                //直接一个页面播放的
                JumpUtils.goToVideoPlayer(this, openbtn);
                break;
            case R.id.list_btn:
                //普通列表播放，只支持全屏，但是不支持屏幕重力旋转，滑动后不持有
                JumpUtils.goToVideoPlayer(this);
                break;
            case R.id.list_btn_2:
                //支持全屏重力旋转的列表播放，滑动后不会被销毁
                JumpUtils.goToVideoPlayer2(this);
                break;
            case R.id.list_detail:
                //支持全屏重力旋转的列表播放，滑动后不会被销毁
                JumpUtils.goToDetailPlayer(this);
                break;
        }
    }
}
