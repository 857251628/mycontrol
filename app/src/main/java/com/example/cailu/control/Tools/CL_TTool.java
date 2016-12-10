package com.example.cailu.control.Tools;

import android.content.Context;
import android.widget.Toast;

/**
 * *
 * Toast统一管理类
 * Created by Administrator on 2016/11/4 0004.
 */

public class CL_TTool {
    private static boolean isShow = true;

    private CL_TTool() {
         /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated(不能被实例化)");
    }

    /**
     * 短时间显示Toast
     *
     * @author Administrator
     * @time 2016/11/4 0004 下午 2:21
     */

    public static void showShort(Context context, CharSequence message) {
        if (isShow) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * 短时间显示Toast
     *
     * @author Administrator
     * @time 2016/11/4 0004 下午 2:26
     */

    public static void showShort(Context context, int message) {
        if (isShow) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 短时间显示Toast
     *
     * @author Administrator
     * @time 2016/11/4 0004 下午 2:26
     */

    public static void showLong(Context context, CharSequence message) {
        if (isShow) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 短时间显示Toast
     *
     * @author Administrator
     * @time 2016/11/4 0004 下午 2:26
     */

    public static void showLong(Context context, int message) {
        if (isShow) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }

    }

    /**
     * 自定义显示Toast时间
     *
     * @author Administrator
     * @time 2016/11/4 0004 下午 2:26
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @author Administrator
     * @time 2016/11/4 0004 下午 2:26
     */
    public static void show(Context context, int message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

}
