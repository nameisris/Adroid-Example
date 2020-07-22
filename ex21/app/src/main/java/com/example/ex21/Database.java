package com.example.ex21;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context, "project.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sql) { // project.db에 들어갈 테이블 생성
        sql.execSQL("create table address(_id integer primary key autoincrement, name text, address text)");
        // 만든다 테이블을(create table), address란 이름으로(address), 아이디는 integer형으로 자동 증가하고 text형인 name과 address를 column으로 갖는 테이블
        // 3개의 column을 가짐
        sql.execSQL("insert into address(name, address) values('홍길동', '인천 서구 경서동')");
        // 넣는다(insert), adress에(into address), 이름과 주소를(name, adress), 값은 '홍길동'과 '인천 서구 경서동'(values('홍길동', '인천 서구 경서동'))
        sql.execSQL("insert into address(name, address) values('심청이', '서울 강남구 압구정동')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
