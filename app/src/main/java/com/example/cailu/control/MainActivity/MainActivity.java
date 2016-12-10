package com.example.cailu.control.MainActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.example.cailu.control.GSYvideplay.ActivityGSYVideo;
import com.example.cailu.control.android5control.AndroidRecyview.ActivityAndroid5;
import com.example.cailu.control.android5control.Android5BottomNav.Android5BottomNav;
import com.example.cailu.control.android5control.Android5Nav.ActivityAndroid5NavView;
import com.example.cailu.control.control.FragemtTab.FragmentTabActivity;
import com.example.cailu.control.Function.Magnify.ActivityMagnify;
import com.example.cailu.control.Function.popup.ActivityPopup;
import com.example.cailu.control.HttpDemo.ActivityHttp;
import com.example.cailu.control.customcontrol.ImageModify.ActivityImage;
import com.example.cailu.control.Function.Progressbar_AsyncTask.ActivityProgressbar;
import com.example.cailu.control.R;
import com.example.cailu.control.customcontrol.ScrollContainer.ActivityScrollViewContainer;
import com.example.cailu.control.customcontrol.TuLingRobot.ActivityTuLin;
import com.example.cailu.control.control.ViewPager.ActivityViewPager;
import com.example.cailu.control.Function.VolleyDemo.ActivityVolley;
import com.example.cailu.control.control.WebView.WebviewActivity;
import com.example.cailu.control.Function.Headreplacement.ActivityHeadreplacement;
import com.example.cailu.control.customcontrol.Zxing.ActivityZxing;
import com.example.cailu.control.customcontrol.openbutton.Activityopenbtn;
import com.example.cailu.control.Function.titlebargon.ActivityTitlebarGone;
import com.example.cailu.control.customcontrol.tabmain.TabMainActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView myControlListView;
    private ArrayList<String> Contrilname = new ArrayList<>();
    private ArrayList<Intent> myIntentlist = new ArrayList<>();
    private MyContrilListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        myControlListView = (ListView) findViewById(R.id.main_listview);
        initlistview();
    }

    private void initlistview() {
        addname();
        addIntent();
        if (Contrilname.size() != myIntentlist.size()) {
            new Exception("项目长度异常 ");

        }
        listAdapter = new MyContrilListAdapter(this);
        listAdapter.setName(Contrilname);
        listAdapter.setMyIntentlist(myIntentlist);
        myControlListView.setAdapter(listAdapter);

    }

    private void addname() {
        Contrilname.add("可滑动开关按钮");  //Activityopenbtn.class
        //  Contrilname.add("圆形图");   ActivityViewPager.class
        Contrilname.add("底部菜单栏");   // FragmentTabActivity.class
        Contrilname.add("网页显示及交互");//WebviewActivity.class
        Contrilname.add("轮播图");         //ActivityViewPager.class
        Contrilname.add("progressbar 进度条AsyncTask更新");//ActivityProgressbar.class
        Contrilname.add("Volley请求");// ActivityVolley.class
        Contrilname.add("断点续传");//ActivityHttp.class
        Contrilname.add("图片修改");//ActivityImage.class
        Contrilname.add("Android5.0新组件 RecycleView");//ActivityAndroid5.class
        Contrilname.add("Android5.0新组件 NavigationView");//ActivityAndroid5NavView.class
        Contrilname.add("图灵机器人");//ActivityTuLin.class
        Contrilname.add("底部导航视图");// Android5BottomNav.class
        Contrilname.add("移动标题栏隐藏");//ActivityTitleGone.class
        Contrilname.add("头像替换");//ActivityHeadreplacement.class
        Contrilname.add("弹出效果及手电筒");//ActivityPopup.class
        Contrilname.add("放大镜");// ActivityMagnify.class
        Contrilname.add("分页详情页");//ActivityScrollViewContainer.class
        Contrilname.add("视频");//ActivityGSYVideo.class
        Contrilname.add("二维码zxing");// ActivityZxing.class
        Contrilname.add("Tabviewpager");//TabMainActivity.class
    }

    private ArrayList<Intent> intentArrayList;
    private Object[] objects;

    private void addIntent() {
        objects = new Object[]{
                Activityopenbtn.class, FragmentTabActivity.class, WebviewActivity.class,
                ActivityViewPager.class, ActivityProgressbar.class, ActivityVolley.class,
                ActivityHttp.class, ActivityImage.class, ActivityAndroid5.class,
                ActivityAndroid5NavView.class, ActivityTuLin.class, Android5BottomNav.class,
                ActivityTitlebarGone.class, ActivityHeadreplacement.class, ActivityPopup.class,
                ActivityMagnify.class, ActivityScrollViewContainer.class, ActivityGSYVideo.class,
                ActivityZxing.class, TabMainActivity.class};
//        myIntentlist.add(new Intent(this, Activityopenbtn.class));
//        myIntentlist.add(new Intent(this, Activityratingview.class));
//        myIntentlist.add(new Intent(this, FragmentTabActivity.class));
//        myIntentlist.add(new Intent(this,WebviewActivity.class));
//        myIntentlist.add(new Intent(this, ActivityViewPager.class));
        for (int i = 0; i < objects.length; i++) {
            myIntentlist.add(new Intent(this, (Class<?>) objects[i]));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
