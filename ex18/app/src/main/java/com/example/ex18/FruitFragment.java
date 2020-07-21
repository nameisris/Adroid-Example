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


public class FruitFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        // 데이터생성
        final ArrayList<String> data = new ArrayList<>(); // 뒷쪽 <> 안에 String 생략 가능
        data.add("망고");
        data.add("망고스틴");
        data.add("코코넛");

        // 어댑터생성
        ArrayAdapter<String> ad = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data); // 어디에, 어떻게, 무엇을 adapt 할 것인지
        // 현재 액티비티는 프래그먼트이므로 어느 곳에서 호출이 될지 모르니 액티비티 이름이 아닌 getActivity() 메소드를 이용함
        // ListView -> 어댑터
        ListView list = view.findViewById(R.id.list);
        list.setAdapter(ad);

        // ListView형 list에 대한 항목 클릭 이벤트
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String strFruit = data.get(i); // data의 i번째 항목을 받아옴 (get)
                Toast.makeText(getActivity(), strFruit, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
