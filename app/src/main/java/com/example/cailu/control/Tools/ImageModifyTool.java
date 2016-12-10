package com.example.cailu.control.Tools;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by cailu on 2016/8/8.
 */
public class ImageModifyTool {
    //图片修改需要的东西;bitmap图片,(   hue色相,饱和度,透明度)为int值.
    public static Bitmap handImagrEffect(Bitmap bm, float hue, float saturation, float transparency) {
        //图片修改不可以直接使用传入的原图,要重新生成.
        //Bitmap.Config.ARGB_8888,设置成32位色.
        Bitmap bitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        //创建画布Canvas和画笔Paint

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置色相 0为红色.1为绿色,2为蓝色.
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);
        //设置饱和度
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);
        //设置透明度
        ColorMatrix transparencyMatrix = new ColorMatrix();
        //3原色值为一样大时为白色  当大于1时就会增加其亮度.
        transparencyMatrix.setScale(transparency, transparency, transparency, 1);
        //要将3种属性进行融合
        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(transparencyMatrix);
        //使用画笔将图画到画布上
        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bm, 0, 0, paint);
        return bitmap;
    }

    public static Bitmap handleImageNegative(Bitmap bitmap) {
        int wight = bitmap.getWidth();
        int hight = bitmap.getHeight();
        Bitmap bmp = Bitmap.createBitmap(wight, hight, Bitmap.Config.ARGB_8888);
        int color;
        int r, g, b, a;
        int[] oldpx = new int[wight * hight];
        int[] newpx = new int[wight * hight];
        bitmap.getPixels(oldpx, 0, wight, 0, 0, wight, hight);
        for (int i = 0; i < oldpx.length; i++) {
            color = oldpx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);
            r = 255 - r;
            g = 255 - g;
            b = 255 - b;
            r = valuesize(r);
            g = valuesize(g);
            b = valuesize(b);
            newpx[i] = Color.argb(a, r, g, b);
        }
        bmp.setPixels(newpx, 0, wight, 0, 0, wight, hight);

        return bmp;
    }

    public static Bitmap handleImageOldPhoto(Bitmap bitmap) {
        int wight = bitmap.getWidth();
        int hight = bitmap.getHeight();
        Bitmap bmp = Bitmap.createBitmap(wight, hight, Bitmap.Config.ARGB_8888);
        int color;
        int r, g, b, a, r1, g1, b1;
        int[] oldpx = new int[wight * hight];
        int[] newpx = new int[wight * hight];
        bitmap.getPixels(oldpx, 0, wight, 0, 0, wight, hight);
        for (int i = 0; i < oldpx.length; i++) {
            color = oldpx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);
            r1 = (int) (0.393 * r + 0.769 * g + 0.189 * b);
            g1 = (int) (0.349 * r + 0.686 * g + 0.168 * b);
            b1 = (int) (0.272 * r + 0.534 * g + 0.131 * b);
            r1 = valuesize(r1);
            g1 = valuesize(g1);
            b1 = valuesize(b1);
            newpx[i] = Color.argb(a, r1, g1, b1);
        }
        bmp.setPixels(newpx, 0, wight, 0, 0, wight, hight);

        return bmp;
    }

    public static Bitmap handleImageRelief(Bitmap bitmap) {
        int wight = bitmap.getWidth();
        int hight = bitmap.getHeight();
        Bitmap bmp = Bitmap.createBitmap(wight, hight, Bitmap.Config.ARGB_8888);
        int color, colorbefore;
        int r, g, b, a, r1, g1, b1;
        int[] oldpx = new int[wight * hight];
        int[] newpx = new int[wight * hight];
        bitmap.getPixels(oldpx, 0, wight, 0, 0, wight, hight);
        for (int i = 1; i < oldpx.length; i++) {
            colorbefore = oldpx[i - 1];
            r = Color.red(colorbefore);
            g = Color.green(colorbefore);
            b = Color.blue(colorbefore);
            a = Color.alpha(colorbefore);
            color = oldpx[i];
            r1 = Color.red(color);
            g1 = Color.green(color);
            b1 = Color.blue(color);

            r = r - r1 + 127;
            g = g - g1 + 127;
            b = b - b1 + 127;
            r = valuesize(r);
            g = valuesize(g);
            b = valuesize(b);
            newpx[i] = Color.argb(a, r, g, b);
        }
        bmp.setPixels(newpx, 0, wight, 0, 0, wight, hight);

        return bmp;
    }

    private static int valuesize(int vale) {
        if (vale > 255) {
            vale = 255;
        } else if (vale < 0) {
            vale = 0;
        }
        return vale;
    }
}
