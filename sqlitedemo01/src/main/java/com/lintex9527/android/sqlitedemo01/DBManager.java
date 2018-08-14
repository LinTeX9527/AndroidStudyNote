package com.lintex9527.android.sqlitedemo01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by lintex9527@yeah.net on 2018/8/14 15:13
 */
public class DBManager {

    private static final String TAG = DBManager.class.getSimpleName();

    private DatabaseOpenHelper dbOpenHelper = null;
    private Context mContext = null;
    private SQLiteDatabase mDatabase = null;

    public DBManager(Context context) {
        mContext = context;
        Log.d(TAG, "DBManager() 新建一个实例");
    }


    public DBManager open() throws SQLException {
        Log.d(TAG, "open() 打开数据库");
        dbOpenHelper = new DatabaseOpenHelper(mContext);
        mDatabase = dbOpenHelper.getWritableDatabase();
        Log.d(TAG, "数据库信息-->" + mDatabase.toString() + ", version: " + mDatabase.getVersion());
        return this;
    }

    public void close() {
        Log.d(TAG, "close() 关闭数据库");
        mDatabase.close();
        dbOpenHelper.close();
    }

    public void insert(String name, String desc) {
        Log.d(TAG, "插入一条记录");
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseOpenHelper.SUBJECT, name);
        contentValues.put(DatabaseOpenHelper.DESC, desc);
        mDatabase.insert(DatabaseOpenHelper.TABLE_NAME, null, contentValues);
    }

    public Cursor fetch() {
        Log.d(TAG, "fetch() 获取数据库中所有的数据，返回Cursor");
        String[] columns = new String[]{DatabaseOpenHelper._ID,
                                        DatabaseOpenHelper.SUBJECT,
                                        DatabaseOpenHelper.DESC};
        Cursor cursor = mDatabase.query(DatabaseOpenHelper.TABLE_NAME,
                                        columns,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    public int update(long _id, String name, String desc) {
        Log.d(TAG, "update() 更新一条记录");
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseOpenHelper.SUBJECT, name);
        contentValues.put(DatabaseOpenHelper.DESC, desc);
        return mDatabase.update(DatabaseOpenHelper.TABLE_NAME,
                contentValues,
                DatabaseOpenHelper._ID + " = " + _id,
                null);
    }


    public void delete(long _id) {
        Log.d(TAG, "delete() 删除一条记录");
        mDatabase.delete(DatabaseOpenHelper.TABLE_NAME, DatabaseOpenHelper._ID + " = " + _id, null);
    }

}
