package com.jueyes.shoppingcartdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 创建一个自己的数据库
 */
public class MyDateBase extends SQLiteOpenHelper {
    public static final String TABLENAME = "tablename";
    public static final String CONTENT = "content";
    public static final String MID = "_id";
    public static final String TIME = "time";
//    public static final String CREATE_BOOK = "create table book("+"id";

    public MyDateBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//          db.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
