package com.example.cailu.control.customcontrol.ImageModify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.cailu.control.R;

/**
 * Created by cailu on 2016/8/8.
 */
public class ActivityImage extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_imagemodify);
    }
    public void primary(View view) {
        startActivity(new Intent(this,Imageprimary.class));
    }

    public void collormatrix(View view) {
        startActivity(new Intent(this,CollorMatrix.class));
    }

    public void pixelseffect(View view) {
        startActivity(new Intent(this,PixelsEffect.class));
    }
}
