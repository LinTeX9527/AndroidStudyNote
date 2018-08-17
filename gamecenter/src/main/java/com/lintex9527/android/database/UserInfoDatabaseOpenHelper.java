package com.lintex9527.android.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.elvishew.xlog.Logger;
import com.elvishew.xlog.XLog;

/**
 * Created by lintex9527@yeah.net on 2018/8/17 14:55
 *
 * 数据库SQLiteOpenHelper类
 */
public class UserInfoDatabaseOpenHelper extends SQLiteOpenHelper {

    Logger logger = XLog.tag("userinfo_database").build();

    public UserInfoDatabaseOpenHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSIOIN_CODE);
        logger.d("UserInfoDatabaseOpenHelper() 1个参数");
    }

    public UserInfoDatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSIOIN_CODE);
        logger.d("UserInfoDatabaseOpenHelper() 4个参数");
    }

    public UserInfoDatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSIOIN_CODE, null);
        logger.d("UserInfoDatabaseOpenHelper() 5个参数");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        logger.d("onCreate() 创建表格");
        db.execSQL(Constants.CREATE_TABLE_USERINFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        logger.d("onUpgrade() 升级数据库");
    }
}
