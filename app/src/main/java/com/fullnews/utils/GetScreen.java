package com.fullnews.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class GetScreen {
    /**
     * 获取屏幕大小
     */
    public static Screen getScreenPix(Context context) {
        DisplayMetrics dm=new DisplayMetrics();
        WindowManager windowManager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        Log.d("widthPixels", dm.widthPixels+"");
        Log.d("heightPixels", dm.heightPixels+"");
        return new Screen(dm.widthPixels, dm.heightPixels);

    }


    public static class Screen {
        public int widthPixels;
        public int heightPixels;

        public Screen(int widthPixels,int heightPixels) {
            this.widthPixels=widthPixels;
            this.heightPixels=heightPixels;
        }

        public String toString() {

            return "("+widthPixels+","+heightPixels+")";
        }
    }

    /**
     * dp转px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}