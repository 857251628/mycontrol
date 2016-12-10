package com.example.cailu.control.customcontrol.ImageModify;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.cailu.control.R;
import com.example.cailu.control.Tools.ImageModifyTool;

import java.util.ArrayList;

/**
 * Created by cailu on 2016/8/8.
 */
public class Imageprimary extends Activity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar hue;
    private SeekBar saturation;
    private SeekBar lum;
    private ImageView imageView;
    private Bitmap bitmap;
    //色彩最大值是255.
    private static float MAX_VALUE = 255;
    private static float MID_VALUE = 127;
    private float mhue, mstauration, mlum;

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.imagemodify_primary);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.tu3);
        hue = (SeekBar) findViewById(R.id.seek_hur);
        saturation = (SeekBar) findViewById(R.id.seek_saturation);
        lum = (SeekBar) findViewById(R.id.seek_lum);
        imageView = (ImageView) findViewById(R.id.image_primary);
        hue.setOnSeekBarChangeListener(this);
        saturation.setOnSeekBarChangeListener(this);
        lum.setOnSeekBarChangeListener(this);
        ArrayList<SeekBar> seekBars = new ArrayList<>();
        seekBars.add(hue);
        seekBars.add(saturation);
        seekBars.add(lum);
        //设置seekbar的最大值
        setMaxValue(seekBars, MAX_VALUE);
        //设置Seekbar的初始值
        setinitvalue(seekBars, MID_VALUE);
        imageView.setImageBitmap(bitmap);

    }

    private void setinitvalue(ArrayList<SeekBar> seekBars, float initvalue) {
        for (int i = 0; i < seekBars.size(); i++) {
            //设置seekbar的值
            seekBars.get(i).setProgress((int) initvalue);
        }
    }

    private void setMaxValue(ArrayList<SeekBar> seekBars, float MAX_VALUE) {
        for (int i = 0; i < seekBars.size(); i++) {
            //设置seekbar的最大值
            seekBars.get(i).setMax((int) MAX_VALUE);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seek_hur:
                //色相的计算公式
                mhue = (progress - MID_VALUE) * 1.0f / MID_VALUE * 180;
                break;
            case R.id.seek_saturation:
                mstauration = progress * 1.0f / MID_VALUE;
                break;

            case R.id.seek_lum:
                mlum=progress * 1.0f / MID_VALUE;
                break;
        }
     imageView.setImageBitmap(ImageModifyTool.handImagrEffect(bitmap, mhue, mstauration, mlum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
