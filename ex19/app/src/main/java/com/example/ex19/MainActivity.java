package com.example.ex19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("액션바"); // 상단에 'ex19'라고 출력되는 액션바의 타이틀 변경
        getSupportActionBar().setSubtitle("서브타이틀"); // 액션바 타이틀 밑의 작은 서브타이틀
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 액션바 왼쪽에 뒤로가기 생성 (안드로이드에서 지원하는 거이므로 id가 'home'으로 정해져있음)
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.img1); // img1을 어플의 로고로 사용
        // setHomeAsUpIndicator를 해줘야 setDisplayHomeAsUpEnabled를 통해 이미지 사용 가능

        final FragmentManager fm = getSupportFragmentManager();

        RadioButton btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) { // 상품목록
                FragmentTransaction tr = fm.beginTransaction();
                ProductFragment product = new ProductFragment();
                tr.replace(R.id.frame, product, "product"); // 어디에, 무엇을, 태그
                tr.commit();
            }
        });

        RadioButton btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction tr = fm.beginTransaction();
                AddressFragment address = new AddressFragment();
                tr.replace(R.id.frame, address, "address"); // 어디에, 무엇을, 태그
                tr.commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // main.xml에 대한 메뉴를 생성
        getMenuInflater().inflate(R.menu.main, menu); // 어떤 메뉴인지, menu 객체를 받음
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // 아이템을 선택했을때 발생하는 메소드
        switch (item.getItemId()){
            case android.R.id.home: // 뒤로가기 버튼일 때
                finish();
                break;
            case R.id.one:
                Toast.makeText(MainActivity.this, "One", Toast.LENGTH_SHORT).show();
                break;
            case R.id.two:
                Toast.makeText(MainActivity.this, "Two", Toast.LENGTH_SHORT).show();
                break;
            case R.id.three:
                Toast.makeText(MainActivity.this, "Three", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
