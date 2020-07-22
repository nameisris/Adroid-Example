package com.example.ex20;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    public DataBase(@Nullable Context context) {
        super(context, "project.db", null, 1); // project.db가 데이터베이스의 이름
    }

    @Override
    public void onCreate(SQLiteDatabase sql) { // 데이터베이스 생성
        sql.execSQL("create table product(_id integer primary key autoincrement, name text, price integer)"); // SQL문을 실행 ("create table 테이블이름()")
        // db안에 테이블 만듬. 테이블의 이름은 pruduct. 시트에는 번호가 들어감.
        // _id는 그런 번호. autoincrement이므로 값을 넣을 때마다 자동으로 올라감
        sql.execSQL("insert into product(name, price) values('오징어땅콩', 900)");
        // 데이터를 insert하는 SQL문
        // product 테이블에 name과 price를 넣음. 각각 오징어땅콩과 900
        // autoincrement이므로 값을 넣어줄 때마다 알아서 값이 1씩 증가하므로 _id는 안줌
        sql.execSQL("insert into product(name, price) values('포테토칩', 2000)");
        sql.execSQL("insert into product(name, price) values('로보트', 1000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

