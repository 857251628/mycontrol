package com.example.cailu.control.control.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cailu.control.R;


/**
 * Created by cailu on 2016/7/21.
 */
public class ActivityViewPager extends Activity implements ViewPager.OnPageChangeListener {

    private ViewPager mviewpager;
    /**
     * 装点点的ImageView数组
     */
    private ImageView[] tips;
    /**
     * 装ImageView数组
     */
    private ImageView[] mImageViews;
    /**
     * 图片资源id
     */
    private int[] imageIdArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        mviewpager = (ViewPager) findViewById(R.id.my_viewpager);
        ViewGroup group = (ViewGroup) findViewById(R.id.my_viewpager_viewGroup);
        //载入图片资源ID
        imageIdArray = new int[]{R.mipmap.tu1, R.mipmap.tu2, R.mipmap.tu3, R.mipmap.tu4, R.mipmap.tu5,};
        //将点点加入到ViewGroup中
        tips = new ImageView[imageIdArray.length];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.shixyuan);
            } else {
                tips[i].setBackgroundResource(R.drawable.kongxyuan);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            group.addView(imageView, layoutParams);
        }
        //将图片装载到数组中
        mImageViews = new ImageView[imageIdArray.length];
        for (int i = 0; i < mImageViews.length; i++) {
            ImageView imageView = new ImageView(this);
            mImageViews[i] = imageView;
            imageView.setBackgroundResource(imageIdArray[i]);
        }
        //设置Adapter
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter();
        myViewPagerAdapter.setImageViews(mImageViews);
        mviewpager.setAdapter(myViewPagerAdapter);
        //设置监听，主要是设置点点的背景
        // mviewpager.setOnPageChangeListener(this);

        mviewpager.addOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        mviewpager.setCurrentItem((mImageViews.length) * 100);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position % mImageViews.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 设置选中的tip的背景
     *
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.drawable.shixyuan);
            } else {
                tips[i].setBackgroundResource(R.drawable.kongxyuan);
            }
        }
    }
}