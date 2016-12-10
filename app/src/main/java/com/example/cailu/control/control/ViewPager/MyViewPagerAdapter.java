package com.example.cailu.control.control.ViewPager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by cailu on 2016/7/21.
 */
public class MyViewPagerAdapter extends PagerAdapter {
    private ImageView[] imageViews;

    public void setImageViews(ImageView[] imageViews) {
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        ((ViewPager) container).removeView(imageViews[position % imageViews.length]);
    }
    /**
     * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
     */
    //过时
//    @Override
//    public Object instantiateItem(View container, int position) {
//        ((ViewPager)container).addView(mImageViews[position % mImageViews.length], 0);
//        return mImageViews[position % mImageViews.length];
//    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
   //     return super.instantiateItem(container, position);
        container.addView(imageViews[position % imageViews.length], 0);
        return imageViews[position % imageViews.length];
    }
}
