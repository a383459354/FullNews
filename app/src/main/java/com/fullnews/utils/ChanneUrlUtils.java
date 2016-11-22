package com.fullnews.utils;

import android.util.Log;

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

    public static String bookChanne(String channel,String sex) {
        Log.d("channel----",channel+"---"+sex);
        if (sex.equals("男生")){
            if (channel.equals("热门")){
                return "http://api.zhuishushenqi.com/ranking/54d42d92321052167dfb75e3";
            }else if (channel.equals("留存")){
                return "http://api.zhuishushenqi.com/ranking/564547c694f1c6a144ec979b";
            }else if (channel.equals("完结")){
                return "http://api.zhuishushenqi.com/ranking/564eb878efe5b8e745508fde";
            }else if (channel.equals("月榜")){
                return "http://api.zhuishushenqi.com/ranking/57eb86f0ef9e5a8f20543d7d";
            }else if (channel.equals("潜力")){
                return "http://api.zhuishushenqi.com/ranking/54d42e72d9de23382e6877fb";
            }else if (channel.equals("其他")){
                return "http://api.zhuishushenqi.com/ranking/564ef4f985ed965d0280c9c2";
            }
        }else {
            if (channel.equals("热门")){
                return "http://api.zhuishushenqi.com/ranking/54d43437d47d13ff21cad58b";
            }else if (channel.equals("留存")){
                return "http://api.zhuishushenqi.com/ranking/5645482405b052fe70aeb1b5";
            }else if (channel.equals("完结")){
                return "http://api.zhuishushenqi.com/ranking/564eb8a9cf77e9b25056162d";
            }else if (channel.equals("月榜")){
                return "http://api.zhuishushenqi.com/ranking/57eb959df60eb7e21fb3a8b7";
            }else if (channel.equals("潜力")){
                return "http://api.zhuishushenqi.com/ranking/54d43709fd6ec9ae04184aa5";
            }else if (channel.equals("其他")){
                return "http://api.zhuishushenqi.com/ranking/564d80d0e8c613016446c5aa";
            }
        }
        return "http://api.zhuishushenqi.com/ranking/54d4306c321052167dfb75e4";
    }

    public static String bookClassifyContentChanne(String channel) {
        if (channel.equals("最新")){
            return "new";
        }else if (channel.equals("热门")){
            return "hot";
        }else if (channel.equals("完结")){
            return "over";
        }else if (channel.equals("好评")){
            return "reputation";
        }
        return "hot";
    }
}
