package com.example.cailu.control.HttpDemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cailu on 2016/8/4.
 * 数据库的帮助类
 */
public class DBHelper extends SQLiteOpenHelper {
    /*
    * 数据库常用属性
    * */
    //数据库的名称
    public static final String DB_NAME = "download.db";
    //数据库的版本
    public static final int VESION = 1;

    /*
    * 数据库需要用到的语法信息
    * _id integer primary key autoincrement :id是int 自增长
    * */
    //创建
    public static final String SQL_CREATE = "create table thread_info(_id integer primary key autoincrement," +
            "thread_id integer,url text, start integer,end integer,finished integer )";
    //删除
    public static final String SQL_DROP = "drop table if exists thread_info";

    //返回的依次是上下文，数据库名称，   ，版本编号。
    public DBHelper(Context context) {
        super(context, DB_NAME, null, VESION);
    }

    //开始数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //先要删除数据库，在重新新建数据库
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }
}
