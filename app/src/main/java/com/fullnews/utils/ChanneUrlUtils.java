package com.fullnews.utils;

/**
 * Created by Administrator on 2016/11/6 0006.
 */

public class ChanneUrlUtils {

    public static String newsChannelId(String channel) {
        if (channel.equals("热点")) {
            return "63";
        } else if (channel.equals("推荐")) {
            return "0";
        } else if (channel.equals("手机")) {
            return "2";
        } else if (channel.equals("汽车")) {
            return "6";
        } else if (channel.equals("互联网")) {
            return "1";
        } else if (channel.equals("游戏")) {
            return "3";
        } else if (channel.equals("电影")) {
            return "4";
        } else if (channel.equals("电商")) {
            return "5";
        }
        return "63";
    }

    public static String bookChanne(String channel) {
        if (channel == "畅销") {
            return "1";
        } else if (channel == "新书") {
            return "2";
        } else if (channel == "全本") {
            return "3";
        }
        return "";
    }
}
