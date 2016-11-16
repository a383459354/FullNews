package com.fullnews.utils;

/**
 * Created by Administrator on 2016/11/16 0016.
 */

public class WordNumber {

    public static String words(int number){
        if (number>9999&&number<=99999999){
            return String.format("%.2f",(number/10000f))+"万字";
        }else if (number>99999999){
            return number/100000000+"亿字";
        }else {
            return number+"字";
        }
    }
}
