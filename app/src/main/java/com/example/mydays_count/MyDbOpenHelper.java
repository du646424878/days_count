package com.example.mydays_count;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 杜哲凯 on 2016/9/17.
 */
public class MyDbOpenHelper extends SQLiteOpenHelper {
    public MyDbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context,name, null,1);

    }

    //第一次创建数据库时
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE rili ( id INTEGER PRIMARY KEY AUTOINCREMENT,describe varchar(20),day INTEGER)");
    }

    //数据库版本更改时
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE person ADD phone Varchar(12) NULL");
    }
}
