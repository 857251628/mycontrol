package com.example.cailu.control.Function.popup;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cailu.control.Tools.FlashLightManager;
import com.example.cailu.control.R;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class ActivityPopup extends Activity {
    private PublishSelectPicPopupWindow menuWindow;
    private FlashLightManager mFlashLightManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_popup);
        mFlashLightManager = new FlashLightManager(this);
        mFlashLightManager.init();
        final Button button = (Button) findViewById(R.id.function_popup);
        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // 实例化SelectPicPopupWindow
                menuWindow = new PublishSelectPicPopupWindow(ActivityPopup.this, itemsOnClick);
                // 显示窗口
                menuWindow.showAtLocation(ActivityPopup.this.findViewById(R.id.function_popup),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }

    // 为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_hand:


                    if (mFlashLightManager.isSupportFlash()){
                        if (!mFlashLightManager.isTurnOnFlash()){
                            mFlashLightManager.turnOn();
                            Toast.makeText(ActivityPopup.this, "开启闪光灯", Toast.LENGTH_SHORT).show();
                        }else {
                            mFlashLightManager.turnOff();
                            Toast.makeText(ActivityPopup.this, "关闭闪光灯", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }else {
                        Toast.makeText(ActivityPopup.this, "不支持闪光灯", Toast.LENGTH_SHORT).show();
                    }

                case R.id.btn_two_code:
                    Toast.makeText(ActivityPopup.this, "2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_library:
                    Toast.makeText(ActivityPopup.this, "3", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }

        }
    };


}
