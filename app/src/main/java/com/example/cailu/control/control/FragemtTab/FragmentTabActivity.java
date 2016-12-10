package com.example.cailu.control.control.FragemtTab;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.TextView;

import com.example.cailu.control.R;

/**
 * Created by cailu on 2016/6/17.
 */
public class FragmentTabActivity extends FragmentActivity {
    //FragmentTabHost控件
    private FragmentTabHost mfragmentTabHost = null;
    //给farmenttab设置插入进去的图片和标题
    private View indicator = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myfarmenttabhost);
        mfragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mfragmentTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //设置需要的tab布局
        indicator = getIndicatorView("我的联系人", R.layout.mycontact_indicator);
        //添加选项卡，    newTabSpec设置说明，setIndicator 设置指示器视图，
        //addTab（设置指示器， 设置视图，传递的bundle信息可以是null）
        mfragmentTabHost.addTab(mfragmentTabHost.newTabSpec("myContact")
                .setIndicator(indicator), FirstFragment.class, null);


        indicator = getIndicatorView("陌生人", R.layout.strangercontact_indicator);
        mfragmentTabHost.addTab(
                mfragmentTabHost.newTabSpec("stranger").setIndicator(indicator),
                SecondFragment.class, null);

        indicator = getIndicatorView("常联系人", R.layout.alwayscontact_indicator);
        mfragmentTabHost.addTab(
                mfragmentTabHost.newTabSpec("alwaysContact").setIndicator(indicator),
                ThirdFragment.class, null);
    }

    private View getIndicatorView(String name, int layoutId) {
        View v = getLayoutInflater().inflate(layoutId, null);
        TextView tv = (TextView) v.findViewById(R.id.tabText);
        tv.setText(name);
        return v;
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        //退出时fragmentTabhost要置null
        mfragmentTabHost = null;
    }

}
