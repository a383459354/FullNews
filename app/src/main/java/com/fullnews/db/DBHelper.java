package com.fullnews.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/11/21 0021.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "readHistory.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        //CursorFactory设置为null,使用默认值
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //数据库第一次被创建时onCreate会被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_HISTORY);
    }

    //如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("ALTER TABLE facedb ADD COLUMN other STRING");
    }

    public static final String CREATE_HISTORY = "create table history ("
            + "id integer primary key autoincrement, "
            + "book_id text, "
            + "book_name text, "
            + "book_auther text, "
            + "book_chatper text, "
            + "book_img text, "
            + "book_save_time text)";
}
