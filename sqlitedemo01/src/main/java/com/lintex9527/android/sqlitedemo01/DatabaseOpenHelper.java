package com.lintex9527.android.sqlitedemo01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lintex9527@yeah.net on 2018/8/14 14:58
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseOpenHelper.class.getSimpleName();

    // 表名称
    public static final String TABLE_NAME = "COUNTRIES";

    // 列名称
    public static final String _ID = "_id";
    public static final String SUBJECT = "subject";
    public static final String DESC = "description";

    // 数据库名称
    static final String DB_NAME = "JOURNALDEV_COUNTRIES.db";

    // 数据库版本号
    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SUBJECT + " TEXT NOT NULL, "
            + DESC + " TEXT "
            + ");";

    private static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    /**
     * 继承过来的构造方法
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(TAG, "DatabaseOpenHelper() 4个参数的构造方法被调用");
    }

    /**
     * 只要求传递Context的构造方法
     * @param context
     */
    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(TAG, "DatabaseOpenHelper() 1个参数的构造方法被调用");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d(TAG, "onOpen() 打开数据库");
    }


    @Override
    public synchronized void close() {
        super.close();
        Log.d(TAG, "close() OpenHelper关闭");
    }

    /**
     * APP 第一次启动时新建数据库调用这个方法新建表格。
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d(TAG, "onCreate() 新建表格");
    }


    /**
     * 如果真的要升级数据库的话，调用顺序肯定是 onUpgrade() 再接着 onCreate()
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade() 数据库升级");
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }


    /**
     * 如果真的要降级数据库的话，调用顺序肯定是 onDowngrade() 再接着 onCreate()
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onDowngrade() 数据库降级");
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }
}
