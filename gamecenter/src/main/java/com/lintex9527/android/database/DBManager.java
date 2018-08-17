package com.lintex9527.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.elvishew.xlog.Logger;
import com.elvishew.xlog.XLog;

/**
 * Created by lintex9527@yeah.net on 2018/8/17 15:01
 */
public class DBManager {

    private Context mContext = null;
    private UserInfoDatabaseOpenHelper dbOpenHelper = null;
    private SQLiteDatabase mDatabase = null;

    private Logger logger = XLog.tag("DBManager").build();

    public DBManager(Context context) {
        mContext = context;
        logger.d("DBManager() 构造方法");
    }


    /**
     * open() 打开数据库连接，必须对应这close() 关闭数据库连接。
     * @return
     * @throws SQLException
     */
    public DBManager open() throws SQLException {
        logger.d("open 打开数据库");
        dbOpenHelper = new UserInfoDatabaseOpenHelper(mContext);
        mDatabase = dbOpenHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        logger.d("close() 关闭数据库");
        mDatabase.close();
        dbOpenHelper.close();
    }

    public void insert(String username, String password) {
        logger.d("insert() 插入一个用户信息");
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.USERNAME, username);
        contentValues.put(Constants.PASSWORD, password);
        mDatabase.insert(Constants.TABLE_NAME_USERINFO, null, contentValues);
    }


    /**
     * 查询数据库中是否已经存在同名的用户。
     * @param username 待查询的用户名
     * @return 存在相同的记录则返回true；若不存在这样的记录则返回false
     */
    public boolean match(String username) {
        logger.d("match() 查询用户名");
        boolean retval = false;
        String[] columns = new String[] {Constants.USERNAME};
        Cursor cursor = mDatabase.query(Constants.TABLE_NAME_USERINFO,
                columns, Constants.USERNAME + " = ? ", new String[] {username}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String query_username = cursor.getString(cursor.getColumnIndex(Constants.USERNAME));
                if (username.equals(query_username)) {
                    logger.d("---------找到了匹配的用户名");
                    retval = true;
                    break;
                }
            }
            cursor.close();
        }

        return  retval;
    }


    /**
     * 查询数据库中是否存在同样的用户名和密码
     * @param username 待查询的用户名
     * @param password 待查询的密码
     * @return 两者都匹配则返回true；不匹配则返回false
     */
    public boolean match(String username, String password) {
        logger.d("match() 查询用户名和密码");
        boolean retval = false;
        String[] columns = new String[] {Constants.USERNAME, Constants.PASSWORD};
        String selection = Constants.USERNAME + " = ? AND " + Constants.PASSWORD + " = ? ";
        String[] selectionArgs = new String[] {username, password};
        Cursor cursor = mDatabase.query(Constants.TABLE_NAME_USERINFO,
                columns, selection, selectionArgs, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String query_username = cursor.getString(cursor.getColumnIndex(Constants.USERNAME));
                String query_password = cursor.getString(cursor.getColumnIndex(Constants.PASSWORD));
                if (username.equals(query_username) && password.equals(query_password)) {
                    logger.d("------------ 查到了对应的用户信息  --------------");
                    retval = true;
                    break;
                }
            }
            cursor.close();
        }
        return retval;
    }

}
