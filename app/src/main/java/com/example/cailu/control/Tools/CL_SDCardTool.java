package com.example.cailu.control.Tools;

import android.os.Environment;

import java.io.File;

/**
 * SD卡辅助类
 * Created by Administrator on 2016/11/5 0005.
 */

public class CL_SDCardTool {
    private CL_SDCardTool() {
         /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断sd卡是否可用
     *
     * @author Administrator
     * @time 2016/11/5 0005 下午 8:44
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }

    /**
     * 获取SD卡的路径
     *
     * @author Administrator
     * @time 2016/11/5 0005 下午 8:45
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }
    /**
    *获取系统路径
    *@author Administrator
    *@time  2016/11/5 0005 下午 8:46
    */
    public static String getRootDirectoryPath()
    {
        return Environment.getRootDirectory().getAbsolutePath();
    }

}
