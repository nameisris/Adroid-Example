package com.example.ex19;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ProductFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View product = inflater.inflate(R.layout.fragment_product, container, false);

        // 데이터베이스 Open
        DataBase db = new DataBase(getContext()); // 데이터베이스 생성
        SQLiteDatabase sql = db.getReadableDatabase(); // 데이터베이스 오픈
        Cursor cursor = sql.rawQuery("select * from product", null); // 데이터를 select 해옴. cursor 안에 select된 결과가 들어감

        // 어댑터 생성
        SimpleCursorAdapter ad = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_2, cursor,
                new String[] {"name", "price"}, // 컬럼 이름
                new int[]{android.R.id.text1, android.R.id.text2});

        // List <- 어댑터
        ListView list = product.findViewById(R.id.list);
        list.setAdapter(ad);
        return product;
    }

}
