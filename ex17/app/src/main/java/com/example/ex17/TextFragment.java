package com.example.ex17;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class TextFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_text, container, false);

        final EditText edit = view.findViewById(R.id.edit);

        Button btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) { // 상위의 view와 헷갈리지 않도록 v로 줌
                String strEdit = edit.getText().toString(); // edit를 통해 입력받은 값을 넘겨줌
                Toast.makeText(getActivity(), strEdit, Toast.LENGTH_SHORT).show(); // getActivity()로 자기를 호출한 액티비티를 가져올 수 있음
            }
        });
        return view;
    }

}
