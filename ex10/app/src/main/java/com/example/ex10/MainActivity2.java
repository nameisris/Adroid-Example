package com.example.ex10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 리스트뷰(ListView)는 여러 개의 목록을 보여주는 위젯으로,
        // 리스트뷰에 보여줄 데이터는 리스트뷰에 직접 입력하는 것이 아니라, adapter 객체에 담고
        // 그 adapter 객체를 리스트뷰에 넣어주어야 함

        // adapter 객체에 담을 데이터는 xml에서  불러올 수도 있고 자바에서 넣어줄 수도 있다.

        // ListView처럼 다량의 데이터를 처리하는 위젯은 어댑터라는 중간 객체에 의존하게 된다.
        // 이유는 디자인에 불과한 ListView가 직접 데이터를 제어하게 되면, 유지보수성이 떨어지게 되기 때문이다.



        // 출력할 데이터생성
        ArrayList<String> data = new ArrayList<>(); // 바구니라 생각. 장군 이름을 넣어줄 것임. 장군 이름은 String 형으로 들어감. data라는 이름의 바구니를 만들어줌. 안에는 String이 들어감.
        data.add("김유신"); // 리스트 안에 String인 "김유신"을 더해줌
        data.add("이순신");
        data.add("강감찬");
        data.add("을지문덕");

        // 어댑터 생성
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data); // ArrayList인 data에 대한 어댑터 ArrayAdapter를 ad에 연결
        // android.R.layout.simple_list_item_1는 안드로이드에서 제공해주는 폼이다.
        // android.R.layout.simple_list_item_1 대신 개발자가 만든 xml을 사용해도 된다.

        // 어댑터를 리스트뷰에 끼워줌
        ListView list = findViewById(R.id.list); // xml에서의 list 아이디를 받아와서 ListView형 변수인 list에 넣어줌 (두 list는 다름)
        list.setAdapter(ad); // 받아온 list 아이디에 어댑터 ad를 끼워줌 (어댑터 ad는 장군이름이 들어간 ArrayList인 data와 연결되어 있음)
        // 어댑터 객체를 리스트뷰인 list에 넣어줌 (set해줌. setAdapter)


        // 출력할 데이터생성
        ArrayList<String> data1 = new ArrayList<>();
        data1.add("사과");
        data1.add("바나나");
        data1.add("망고스틴");
        data1.add("포도");

        // 어댑터 생성
        ArrayAdapter ad1 = new ArrayAdapter(this, R.layout.item, data1); // android.R.layout.simple_list_item_1 대신 개발자가 만든 xml을 사용
        // 위의 어댑터와는 다르게 안드로이드 스튜디오에서 기본으로 제공되는 xml이 아닌. 직접 만든 item이라는 이름의 xml을 사용함

        // 어댑터를 리스트뷰에 끼워줌
        ListView list1 = findViewById(R.id.list1);
        list1.setAdapter(ad1);

    }


}
