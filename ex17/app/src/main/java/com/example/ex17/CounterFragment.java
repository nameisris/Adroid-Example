package com.example.ex17;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class CounterFragment extends Fragment {
    int count = 0; // 버튼을 누를 때마다 증가할 값
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_counter, container, false);
        Button btn = view.findViewById(R.id.btn);
        final TextView text = view.findViewById(R.id.text); // text가 아래의 메소드 안에서 재초기화 되므로 final 붙여줌

        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                text.setText(count + ""); // count가 정수이므로 공백을 붙여줌
            }
        });
        return view;
    }
}
