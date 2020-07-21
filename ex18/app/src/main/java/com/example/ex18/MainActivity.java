package com.example.ex18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fm = getSupportFragmentManager(); // 프래그먼트를 출력하기 위해 필요한 FragmentManager를 fm에 저장

        // 각각 버튼의 이벤트를 지정
        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) { // 음식 목록 출력
                FoodFragment food = new FoodFragment(); // FoodFragment 클래스에 대한 객체 생성 (food)
                FragmentTransaction tr = fm.beginTransaction();
                tr.replace(R.id.frame, food, "food"); // replace로 프래그먼트를 replace
                tr.commit();
            }
        });

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                FruitFragment fruit = new FruitFragment(); // FruitFragment 클래스에 대한 객체 생성 (fruit)
                FragmentTransaction tr = fm.beginTransaction();
                tr.replace(R.id.frame, fruit, "fruit");
                tr.commit();
            }
        });
    }
}
