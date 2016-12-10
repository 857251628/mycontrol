package com.example.cailu.control.customcontrol.openbutton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.cailu.control.R;

/**
 * Created by cailu on 2016/6/1.
 */
public class MyOpenBtn extends View implements View.OnClickListener {

    //定义需要的视图
    private Bitmap background;
    private Bitmap btn;
    //定义画笔
    private Paint paint;

    /**
     * 在布局文件中声名的view，创建时由系统自动调用。
     *
     * @param context 上下文对象
     * @param attrs   属性集
     */
    public MyOpenBtn(Context context, AttributeSet attrs) {
        super(context, attrs);

        initview();
    }

    //创建图片视图
    private void initview() {
        background = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_background);
        btn = BitmapFactory.decodeResource(getResources(), R.mipmap.slide_button);
        //初始化画笔属性
        paint = new Paint();
        paint.setAntiAlias(true);
        //设置监听事件
        setOnClickListener(this);
        //这些是初始化要使用的图片和工具,要将图片按自己的要求显示
        //先要测量你需要多大的view区域来安放你的图片onMeasure(int,int);
        //确定view的位置 ，view自身有一些建议权，决定权在 父view手中。  onLayout();
        // 绘制 view 的内容需要重写 View中的的onDraw();
    }

    /*
         * view 对象显示的屏幕上，有几个重要步骤：
         * 1、构造方法 创建 对象。
         * 2、测量view的大小。	onMeasure(int,int);
         * 3、确定view的位置 ，view自身有一些建议权，决定权在 父view手中。  onLayout();
         * 4、绘制 view 的内容 。 onDraw(Canvas)
         */
    private int btnleft;

    @Override
    protected void onDraw(Canvas canvas) {
//使用上面传下来的canvas对象来调用drawBitmap方法来绘制视图
        // 绘制 背景
        /*
         * backgroundBitmap	要绘制的图片
		 * left	图片的左边届
		 * top	图片的上边届
		 * paint 绘制图片要使用的画笔
		 */
        //绘制了底面图片
        canvas.drawBitmap(background, 0, 0, paint);
        //绘制按键图片
        //按键图片,因为按键图片会改变所以图片左边界的值应该是变量值
        canvas.drawBitmap(btn, btnleft, 0, paint);
    }

    @Override
    /**
     * 测量尺寸时的回调方法
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 设置当前view的大小
         * width  :view的宽度
         * height :view的高度   （单位：像素）
         */
        setMeasuredDimension(background.getWidth(), background.getHeight());
    }

    //确定位置的时候调用此方法
    //自定义view的时候，作用不大
//	@Override
//	protected void onLayout(boolean changed, int left, int top, int right,
//			int bottom) {
//		super.onLayout(changed, left, top, right, bottom);
//	}
    private boolean flag = false;

    @Override
    public void onClick(View v) {
        //设置点击事件,需要一个flag记录他的开关状态
        if (!touchflag){
            flag = !flag;
            flushstate();
        }

    }



    private int downX;
    private int moveX;
    private Boolean touchflag=false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
      super.onTouchEvent(event);
        //使用event对象的getAction方法获取到当前点击滑动时的状态
        //要记录下按下状态是的位置使用event.getX();
        //记录移动是的 位置
        //按下移动的进行比较大于一定值时图片位置发生改变
        //在拿起时要判断距离那边更近就往那边移动
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:   //按下状态

                downX = moveX=(int) event.getX();

                touchflag = false;

                break;
            case MotionEvent.ACTION_MOVE:   //移动状态
                if (Math.abs(event.getX()-downX  ) > 5) {

                    touchflag = true;
                }

                int touchX = (int) (event.getX() - moveX);
                moveX=(int) event.getX();
                btnleft = btnleft + touchX;

                break;
            case MotionEvent.ACTION_UP:    //拿起状态
                if (touchflag) {
                    int maxleft = background.getWidth() - btn.getWidth();
                    if (btnleft > maxleft / 2) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                    flushstate();
                }

                break;
        }
        flushView();

        return true;
    }
    public void flushstate() {
        if (flag) {
            btnleft = background.getWidth() - btn.getWidth();
        } else {
            btnleft = 0;
        }
        /*
		 * 刷新当前视图  导致 执行onDraw执行
		 * 为了可以滑动我们还需要设置滑动监听
		 */
        flushView();
    }
    //滑动时会划出频幕要设置可移动最大值

    private void flushView() {
        int maxleft=background.getWidth()-btn.getWidth();

        btnleft=(btnleft>0)?btnleft:0;

        btnleft=(btnleft<maxleft)?btnleft:maxleft;
     //下面的方法是刷新视图用以调用onDraw()方法;(要有滑动视觉效果必须调用)
        invalidate();
    }




}
