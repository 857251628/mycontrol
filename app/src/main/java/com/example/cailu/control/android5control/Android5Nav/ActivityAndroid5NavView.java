package com.example.cailu.control.android5control.Android5Nav;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;


import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cailu.control.R;
import com.example.cailu.control.Tools.CL_AbAnimationTool;
import com.example.cailu.control.Tools.CL_TTool;

/**
 * Created by Administrator on 2016/10/31 0031.
 */
public class ActivityAndroid5NavView extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ImageView mImageView;
    private TextInputLayout mTextInputEditText;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cehualan);
        initView();
        initdate();
    }

    private void initdate() {
        String imaurl = "http://pic41.nipic.com/20140501/2531170_162158900000_2.jpg";
        Glide.with(this).load(imaurl).into(mImageView);

    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.cehualan_draw);
        TextView mtext = (TextView) findViewById(R.id.id_tv_content);
        mImageView = (ImageView) findViewById(R.id.id_tv_image);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mTextInputEditText = (TextInputLayout) findViewById(R.id.id_tv_inputlayout);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.id_tv_floatbutton);
        mFloatingActionButton.setOnClickListener(this);
        mTextInputEditText.setErrorEnabled(false);
        //集的色彩应用于菜单项的图标
        mNavigationView.setItemIconTintList(null);
        mtext.setText("NavigationView");
        //获取头布局文件
        // 然后通过调用headerView中的findViewById方法来查找到头部的控件，设置点击事件即可。
        View headerView = mNavigationView.getHeaderView(0);
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        //  setSupportActionBar(toolbar);
        //   final ActionBar ab = getSupportActionBar();
        //  ab.setHomeAsUpIndicator(R.drawable.shixyuan);
        //  ab.setDisplayHomeAsUpEnabled(true);
        //设置侧边栏的点击事件自己的方法
        setupDrawerContent(mNavigationView);
        // setupDrawerContent(mNavigationView);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        //*设置一个侦听器将菜单项被选中时通知
        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        //在这里处理item的点击事件
                        //关闭所有当前打开抽屉视图动画出来的视图。
                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                CL_TTool.showShort(ActivityAndroid5NavView.this,menuItem.getTitle());
                                CL_AbAnimationTool.playRotateAnimation(mImageView,1000,3, Animation.ABSOLUTE);
                               // Toast.makeText(ActivityAndroid5NavView.this, "" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.nav_discussion:
                                CL_TTool.showShort(ActivityAndroid5NavView.this,menuItem.getTitle());

                                break;
                            case R.id.nav_friends:
                                Toast.makeText(ActivityAndroid5NavView.this, "" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.nav_messages:
                                Toast.makeText(ActivityAndroid5NavView.this, "" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                                break;

                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //设置顶边栏的menu
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }

    //我们在Activity里面可以通过navigationView去navigationView.setNavigationItemSelectedListener，
// 当selected的时候，menuItem去setChecked(true)。
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_tv_floatbutton:
               EditText med= (EditText) mTextInputEditText.getEditText();
               String s= med.getText().toString();
                if (s.isEmpty()) {
                    mTextInputEditText.setError("不能为空");
                } else if (s.equals("123")) {
                    mTextInputEditText.setError("正确");
                } else {
                    mTextInputEditText.setError("错误");
                }
                mTextInputEditText.setErrorEnabled(true);
        }
    }
}
