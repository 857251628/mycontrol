package com.example.cailu.control.customcontrol.ImageModify;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.cailu.control.R;
import com.example.cailu.control.Tools.ImageModifyTool;

/**
 * Created by cailu on 2016/8/10.
 */
public class PixelsEffect extends Activity {

    private ImageView mImage1;
    private ImageView mImage2;
    private ImageView mImage3;
    private ImageView mImage4;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagemodify_pixelseffect);
        initview();
    }

    private void initview() {
        mImage1 = (ImageView) findViewById(R.id.pixelseffect_image1);
        mImage2 = (ImageView) findViewById(R.id.pixelseffect_image2);
        mImage3 = (ImageView) findViewById(R.id.pixelseffect_image3);
        mImage4 = (ImageView) findViewById(R.id.pixelseffect_image4);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.tu4);
        mImage1.setImageBitmap(mBitmap);
        mImage2.setImageBitmap(ImageModifyTool.handleImageNegative(mBitmap));
        mImage3.setImageBitmap(ImageModifyTool.handleImageOldPhoto(mBitmap));
        mImage4.setImageBitmap(ImageModifyTool.handleImageRelief(mBitmap));
    }
}
