package com.example.ex10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        // 데이터 생성
        // 보통 제일 먼저 데이터 생성을 하지만
        // 이미 res-values-array.xml 상에서 생성을 함

        // 과일에 대한 어댑터 생성
        final ArrayAdapter ad1 = ArrayAdapter.createFromResource(this, R.array.fruits, android.R.layout.simple_spinner_item); // 여기에 fruits 형태의 스피너에 대한 어댑터를 생성?
        ad1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice); // 스피너 창에서 옵션 선택형으로 화면에 출력됨

        // Spinner -> Adapter (과일)
        Spinner spinner1 = findViewById(R.id.spinner1); // spinner1에 대한 아이디 가져옴
        spinner1.setAdapter(ad1); // spinner1을 어댑처 ad1과 연결

        // 커피에 대한 어댑터 생성
        final ArrayAdapter ad2 = ArrayAdapter.createFromResource(this, R.array.coffee, android.R.layout.simple_spinner_item); // this에 R.array.coffee에 대해 simple_spiner_item형으로 어댑터 연결
        ad2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice); // 스피너 창에서 옵션 선택형으로 화면에 출력됨

        // Spiiner -> Adapter (커피)
        Spinner spinner2 = findViewById(R.id.spinner2);
        spinner2.setAdapter(ad2);

        // 스피너 항목 클릭하여 선택시 Toast로 출력
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity5.this, "선택 : "+ ad1.getItem(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 스피너 항목 클릭하여 선택시 Toast로 출력 (커피)
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity5.this, "선택 : "+ ad2.getItem(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // 위의  방법들과는 다른 방법
        // 데이터생성
        ArrayList<String> data = new ArrayList<String>();
        data.add("그랜저");
        data.add("소나타");

        // 어댑터생성
        final ArrayAdapter ad3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data);
        ad3.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        final ImageView img = findViewById(R.id.img);
        // Adapter -> Spinner
        Spinner spinner3 = findViewById(R.id.spinner3);
        spinner3.setAdapter(ad3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        img.setImageResource(R.drawable.grandeur);
                        break;
                    case 2:
                        img.setImageResource(R.drawable.sonata);
                        break;
                }
                Toast.makeText(MainActivity5.this, "선택 : "+ ad3.getItem(i), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
