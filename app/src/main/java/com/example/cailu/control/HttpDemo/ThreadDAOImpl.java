package com.example.cailu.control.HttpDemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据访问接口的实现
 * Created by cailu on 2016/8/4.
 */
public class ThreadDAOImpl implements ThreadDAO {

    private DBHelper mHelper = null;

    public ThreadDAOImpl(Context context) {
        this.mHelper = new DBHelper(context);
    }

    //插入线程
    @Override
    public void insertThread(ThreadInfo threadInfo) {
        //从DBhelper的父类SQLiteOpenHelper中调取写入流
        SQLiteDatabase database = mHelper.getWritableDatabase();
        //将对象写入数据库
        database.execSQL(
                "insert into thread_info(thread_id,url,start,end,finished) values(?,?,?,?,?)",
                new Object[]{threadInfo.getId(), threadInfo.getUrl(), threadInfo.getStart(),
                        threadInfo.getEnd(), threadInfo.getFinished()});
        //写入流的关闭
        database.close();
    }

    //删除线程
    @Override
    public void deleteThread(String url, int thread_id) {
        //从DBhelper的父类SQLiteOpenHelper中调取写入流
        SQLiteDatabase database = mHelper.getWritableDatabase();
        //将对象写入数据库
        database.execSQL(
                "delete from thread_info where url = ? and thread_id = ?",
                new Object[]{url, thread_id});
        //写入流的关闭
        database.close();
    }

    //更新
    @Override
    public void updateThread(String url, int thread_id, int finished) {
        //从DBhelper的父类SQLiteOpenHelper中调取写入流
        SQLiteDatabase database = mHelper.getWritableDatabase();
        //将对象写入数据库
        database.execSQL(
                "update thread_info set finished = ? where url = ? and thread_id = ?",
                new Object[]{finished, url, thread_id});
        //写入流的关闭
        database.close();
    }

    //查询
    @Override
    public List<ThreadInfo> getThreadinfo(String url) {
        //从DBhelper的父类SQLiteOpenHelper中调取写入流
        SQLiteDatabase database = mHelper.getWritableDatabase();
        List<ThreadInfo> mList = new ArrayList<>();

        //数据库查找对象
       Cursor cursor= database.rawQuery("select * from thread_info where url = ?" ,new String[]{url});

        while (cursor.moveToNext()){
            ThreadInfo info =new ThreadInfo();
            info.setId(cursor.getInt(cursor.getColumnIndex("thread_id")));
            info.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            info.setStart(cursor.getInt(cursor.getColumnIndex("start")));
            info.setEnd(cursor.getInt(cursor.getColumnIndex("end")));
            info.setFinished(cursor.getInt(cursor.getColumnIndex("finished")));
            mList.add(info);
        }

            cursor.close();

        //写入流的关闭
        database.close();
        return mList;
    }


//判断线程是否存在
    @Override
    public boolean isExists(String url, int thread_id) {
        SQLiteDatabase database =mHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from thread_info where url = ? and thread_id = ?",
                new String[]{url, String.valueOf(thread_id)});
        boolean exists =cursor.moveToNext();
        cursor.close();
        return exists;
    }
}
