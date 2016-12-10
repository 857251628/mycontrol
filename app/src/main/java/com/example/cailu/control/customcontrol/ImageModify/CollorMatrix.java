package com.example.cailu.control.customcontrol.ImageModify;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.cailu.control.R;

/**
 * Created by cailu on 2016/8/10.
 */
public class CollorMatrix extends Activity {
    private ImageView mImageView;
    private GridLayout mGroup;
    private Bitmap mBitmap;
    private int mEtWidth, mEtHigth;
    private EditText[] mEdits = new EditText[20];
    private float[] mColorMatrix = new float[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagemodify_collormatrix);
        initview();
    }

    private void initview() {
        mImageView = (ImageView) findViewById(R.id.collormatrix_image);
        mGroup = (GridLayout) findViewById(R.id.collormatrix_group);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.tu2);
        mImageView.setImageBitmap(mBitmap);
        //计算mGroup的高和宽
        mGroup.post(new Runnable() {
            @Override
            public void run() {
                mEtHigth = mGroup.getHeight();
                mEtWidth = mGroup.getWidth();
                //给mgroup增加edittext;
                addEts();
                //将edittext的值进行初始化
                initMatrix();
            }
        });

    }

    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            //颜色矩阵初始化时每第6个为1,其他为0.
            if (i % 6 == 0) {
                mEdits[i].setText(String.valueOf(1));
                mColorMatrix[i] = 1;
            } else {
                mEdits[i].setText(String.valueOf(0));
                mColorMatrix[i] = 0;

            }
        }
    }

    private void addEts() {
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(this);
            //要将edittext保存起来使用
            mEdits[i] = editText;
            mGroup.addView(editText, mEtWidth / 5, mEtHigth / 4);
        }
    }

    public void kaishi(View view) {
        //要将edittext的值取出并赋值给flot[];
        getmatrix();
        setmImageMatrix();
    }

    public void chushihua(View view) {
        initMatrix();
        kaishi(view);
    }

    public void getmatrix() {
        for (int i = 0; i < mEdits.length; i++) {
            mColorMatrix[i] = Float.valueOf(mEdits[i].getText().toString());
        }
    }

    public void setmImageMatrix() {
        //不可以对原图进行更改.要创建一个新的原图
        Bitmap bitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //创建颜色矩阵
        ColorMatrix colorMatrix = new ColorMatrix();
        //颜色矩阵设置值
        colorMatrix.set(mColorMatrix);
        //通过原图创建一个新的画布
        Canvas canvas = new Canvas(bitmap);
        //创建画笔
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //给画笔设置颜色矩阵
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        //用 画笔 根据 原图 在 画布上 生成 图片.
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        //在imageview上设置图片
        mImageView.setImageBitmap(bitmap);
    }
}
