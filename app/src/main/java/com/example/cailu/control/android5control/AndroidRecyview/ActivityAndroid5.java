package com.example.cailu.control.android5control.AndroidRecyview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cailu.control.R;

import java.util.ArrayList;

/**
 * Created by cailu on 2016/8/17.
 */
public class ActivityAndroid5 extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private ArrayList<String> mArrayList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android5);
        addListdata();
        initdata();
    }

    private void addListdata() {
        for (int i = 0; i < 20; i++) {
            mArrayList.add("新的列表项 <"+i+">");
        }
    }

    private void initdata() {
        mRecyclerView = (RecyclerView) findViewById(R.id.android5_recyview);
        RecyViewAdapter mA5dapter =new RecyViewAdapter(this);
        mA5dapter.setmArrayList(mArrayList);

        //设置样式LinearLayoutManager垂直
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
      //  mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        //设置线的样式
        mRecyclerView.addItemDecoration(new RecyViewDivider(this));
        //设置动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置适配器
        mRecyclerView.setAdapter(mA5dapter);
    }
}
