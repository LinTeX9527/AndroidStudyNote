package com.lintex9527.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类，用来创建数据库
 *
 */
public class DBHelper extends SQLiteOpenHelper{

    // 数据库名称
    private static final String DB_NAME = "download.db";
    // 数据库版本号
    private static final int VERSION = 1;

    // 表格名称 thread_info ，用来保存线程下载文件时的 url，起始位置和结束为止，以及是否完成
    public static final String TABLE_NAME = "thread_info";

    // 创建表格的语法
    private static final String SQL_CREATE = "CREATE TABLE thread_info (_id integer PRIMARY KEY AUTOINCREMENT," +
            "thread_id INTEGER," +
            "url TEXT," +
            "start_point INTEGER," +
            "end_point INTEGER," +
            "finished INTEGER)";

    // 删除表格的语法
    private static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }
}
