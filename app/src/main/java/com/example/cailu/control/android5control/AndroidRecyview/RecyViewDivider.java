package com.example.cailu.control.android5control.AndroidRecyview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by cailu on 2016/8/17.
 */
public class RecyViewDivider extends RecyclerView.ItemDecoration {

    //android 默认分隔条Drawable资源ID;
    public static final int[] ATTRS = {android.R.attr.listDivider};
    //Drawable 分割线对象
    private Drawable mDrawable;

    public RecyViewDivider(Context context) {
        TypedArray array = context.obtainStyledAttributes(ATTRS);
        //获取系统提供的分割条对象
        mDrawable = array.getDrawable(0);
        //回收TypeArray 所占的资源
        array.recycle();
    }

    //这个方法中绘制分割线
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        //获取列表的页数
        int childCount = parent.getChildCount();
        //距离左边缘的距离;
        int left = parent.getPaddingLeft();
        //距离右边缘的距离;
        int right = parent.getWidth() - parent.getPaddingRight();
        //开始绘制分割线
        for (int i = 0; i < childCount; i++) {
            //获取当前要绘制分割线的item
            View child = parent.getChildAt(i);
            //获取当前列表项的信息
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            //获取分割线的上端位置
            int top = child.getBottom() + layoutParams.bottomMargin;
            //获取分割线的下端位置
            int bottom = top + mDrawable.getIntrinsicHeight();
            //设置分割条的位置
            mDrawable.setBounds(left, top, right, bottom);
            //开始绘制
            mDrawable.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}