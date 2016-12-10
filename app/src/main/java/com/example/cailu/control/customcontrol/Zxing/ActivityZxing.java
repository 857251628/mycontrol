package com.example.cailu.control.customcontrol.Zxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.cailu.control.R;

public class ActivityZxing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        Button defaultStart = (Button) findViewById(R.id.default_start);
        defaultStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityZxing.this, DefaultCaptureActivity.class);
                startActivity(intent);
            }
        });

        Button weStart = (Button) findViewById(R.id.wechat_start);
        weStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityZxing.this, WeChatCaptureActivity.class);
                startActivity(intent);
            }
        });

    }
}
