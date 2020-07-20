package com.example.ex11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list, list1; // 전역변수로서 ListView 타입의 변수 선언
    Spinner spinner;
    ArrayList<String> data; // ListView에 넣을 ArrayList형의 문자열 변수 선언 (ListView에 여러 개의 문자열을 넣을 것이므로 그냥 String형이 아닌 ArrayList의 String형으로 선언)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list); // xml의 list에 대한 주소값을 ListView형 변수 list에 넣어줌
        list1 = findViewById(R.id.list1);
        spinner = findViewById(R.id.spinner);

        // 데이터생성
        data = new ArrayList<String>(); // <> 안에 String은 생략 가능
        data.add("바나나");
        data.add("포도");
        data.add("수박");

        // 어댑터 생성
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, data); // 어디에 생성할 건지, 어떤 레이아웃으로 할지, 어떤 데이터와 연결할지
        ArrayAdapter ad1 = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, data); // 다른 리스트를 위한 새 어댑터이지만 data는 똑같이 사용 가능


        // ListView에 어댑터 set
        list.setAdapter(ad); // list에 어댑터 ad를 set
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); // 어댑터가 연결된 list에 대한 선택모드를 멀티플모드로 설정
        list1.setAdapter(ad1);
        list1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // Spinner 어댑터 생성한 뒤 set (Spinner는 선택 방식이 이미 정해져 있으므로 어댑터만 set해주면 됨)
        ArrayAdapter ad2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data); // Spinner에 쓸 어댑터
        ad2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice); // Spinner는 DropDownViewResource를 지정해줘야 어쩌구...
        spinner.setAdapter(ad2);
    }

}
