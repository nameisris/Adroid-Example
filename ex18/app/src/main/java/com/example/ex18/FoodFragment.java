package com.example.ex18;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class FoodFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        // 데이터생성
        final ArrayList<String> data = new ArrayList<String>(); // 과일 이름을 저장할 것이므로 String
        data.add("짜장면");
        data.add("짬뽕");
        data.add("탕수육");

        // 어댑터 생성
        ArrayAdapter<String> ad = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data); // 어디에, 어떻게, 무엇을 adapt 할 것인지

        // ListView -> 어댑터
        ListView list = view.findViewById(R.id.list);
        list.setAdapter(ad);

        // ListView형 list에 대한 항목 클릭 이벤트
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() { // list에 대한 클릭 이벤트
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String strFood = data.get(i); // data의 i번째 항목을 받아옴 (get)
                Toast.makeText(getActivity(), strFood, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}
