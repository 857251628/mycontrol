package com.example.cailu.control.Tools;

import android.content.Context;

/**
 * 常用单位转换的辅助类
 * Created by Administrator on 2016/11/4 0004.
 */

public class CL_DensityTool {

    private CL_DensityTool() {
         /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px
     * @author Administrator
     * @time 2016/11/4 0004 下午 4:14
     */

    public static float dp2px(Context context, float dpVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dpVal / scale);
    }
}
