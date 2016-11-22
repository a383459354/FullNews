package com.fullnews.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fullnews.entity.ReadHistoryEntity;
import com.fullnews.presenter.BookRackData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/21 0021.
 */

public class DBManager {

    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void closeDB() {
        if (db != null) {
            db.close();
        }
    }

    /**
     * 添加一个数据
     *
     * @param name
     */
    public void add(String bookId,String name,String auther,String chatper,String time,String img) {
        if (queryBookExists(name)==0){
            ContentValues values = new ContentValues();
            // 开始组装第一条数据
            values.put("book_id", bookId);
            values.put("book_name", name);
            values.put("book_auther", auther);
            values.put("book_chatper", chatper);
            values.put("book_save_time", time);
            values.put("book_img", img);
            db.insert("history", null, values); // 插入第一条数据
        }
    }

    /**
     * 修改阅读章节
     *
     * @param
     */
    public void updateChapter(String name, String chatper) {
        ContentValues values = new ContentValues();
        values.put("book_chatper", chatper);
        db.update("Book", values, "book_name = ?", new String[]{name});
    }

    /**
     * 删除数据
     *
     * @param name
     */
    public void delete(String name) {
        db.beginTransaction();    //开始事务
        try {
            db.delete("history", "name = ?", new String[]{name});
            db.setTransactionSuccessful();    //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public void queryBookRack(BookRackData bookRackData) {
        List<ReadHistoryEntity> list = new ArrayList<>();
        Cursor cursor = db.query("history", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象，取出数据并打印
                ReadHistoryEntity readHistoryEntity = new ReadHistoryEntity();
                readHistoryEntity.setBookId(cursor.getString(cursor.getColumnIndex("book_id")));
                readHistoryEntity.setName(cursor.getString(cursor.getColumnIndex("book_name")));
                readHistoryEntity.setAuther(cursor.getString(cursor.getColumnIndex("book_auther")));
                readHistoryEntity.setChatper(cursor.getString(cursor.getColumnIndex("book_chatper")));
                readHistoryEntity.setTime(cursor.getString(cursor.getColumnIndex("book_save_time")));
                readHistoryEntity.setImg(cursor.getString(cursor.getColumnIndex("book_img")));
                list.add(readHistoryEntity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        bookRackData.getBookRackDB(list);
    }

    /**
     * 查询书架中是否存在该本书
     *
     * @return
     */
    public int queryBookExists(String name) {
        Cursor cursor = db.query("history", new String[]{"COUNT(book_name)"}, "book_name=?", new String[]{"qwe"}, null, null, null);
        cursor.moveToFirst();
        int number = cursor.getInt(cursor.getColumnIndex("COUNT(book_name)"));
        Log.d("MainActivity", "数量:" + number);
        cursor.close();
        return number;
    }
}
