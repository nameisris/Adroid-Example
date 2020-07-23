package com.example.ex22;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) { // 생성자
        super(context, "project.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sql) {
        sql.execSQL("create table address(_id integer primary key autoincrement, name text, tel test, address text)");
        sql.execSQL("insert into address(name, tel, address) values('홍길동', '010-0001-0001', '인천시 서구')");
        sql.execSQL("insert into address(name, tel, address) values('심청이', '010-0002-0002', '인천시 부평구')");
        sql.execSQL("insert into address(name, tel, address) values('성춘향', '010-0003-0003', '인천시 계양구')");
        sql.execSQL("insert into address(name, tel, address) values('이몽룡', '010-0004-0004', '서울 강남구')");
        sql.execSQL("insert into address(name, tel, address) values('이순신', '010-0005-0005', '서울 강북구')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
