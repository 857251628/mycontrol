package com.example.cailu.control.Function.titlebargon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.cailu.control.R;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class ActivityTitlebarGone extends Activity{

    private boolean ismove  = true;
    private ViewPropertyAnimator animatebottom,animatetop;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_titlebargone);

        ListView mListView = (ListView) findViewById(R.id.my_listview);
        LinearLayout tabbottom  = (LinearLayout) findViewById(R.id.tabbottom);
        LinearLayout  tabtop  = (LinearLayout) findViewById(R.id.tabtop);
        animatebottom = tabbottom.animate();
        animatetop = tabtop.animate();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1);
        for (int i = 0; i < 100; i++) {
            adapter.add(i + "");
        }
        mListView.setAdapter(adapter);
        mListView.setOnTouchListener(new View.OnTouchListener() {

            private float y;
            private boolean down = true;
            private float lasty;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //点下是记录Y值
                        y = event.getY();
                        if (down) {
                            lasty = y;
                        }
                        //设置标记为false
                        down = false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        //抬起时 设置标记为true
                        down = true;
                        //如果老的Y小于新的Y  就是向上滑动
                        if (lasty - event.getY() < -5) {
                            onScrollReset();
                        } //如果老的Y大于新的Y 就是向下滑动
                        else if (lasty - event.getY() > 5) {
                            onScroll();
                        }
                        break;
                }

                return false;
            }
        });
    }



    @SuppressLint("NewApi")
    public void onScroll() {
        //如果标题栏没隐藏了才进入,
        if (ismove) {
            animatebottom.setDuration(500).translationY(200);
            animatetop.setDuration(500).translationY(-200);
            ismove = false;
        }

    }
    @SuppressLint("NewApi")
    public void onScrollReset() {
        //如果标题栏隐藏了才进入,
        if (!ismove) {
            animatebottom.setDuration(500).translationY(0);
            animatetop.setDuration(500).translationY(0);
            ismove = true;
        }
    }



}
