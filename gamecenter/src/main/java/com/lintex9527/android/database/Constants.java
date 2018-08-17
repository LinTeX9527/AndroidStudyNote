package com.lintex9527.android.database;

/**
 * Created by lintex9527@yeah.net on 2018/8/17 14:47
 * 和数据库相关的常量
 */
public class Constants {

    // 数据库名称，保存用户信息
    public static final String DATABASE_NAME = "user.db";

    // 数据库版本号
    public static final int VERSIOIN_CODE = 1;
    // 表格名称
    public static final String TABLE_NAME_USERINFO = "userinfo";


    // 表格中的列名称
    public static final String _ID = "_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    // 创建表格 userinfo 的语句
    public static final String CREATE_TABLE_USERINFO = "CREATE TABLE " + TABLE_NAME_USERINFO + "( "
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USERNAME + " TEXT NOT NULL, "
            + PASSWORD + " TEXT NOT NULL"
            + ");";

}
