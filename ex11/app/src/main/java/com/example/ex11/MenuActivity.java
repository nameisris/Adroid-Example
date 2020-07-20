package com.example.ex11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    // 클릭 메소드 선언
    public void mClick(View v) { // View가 모든 것을 포함 (TextView던 ImageView던)
        Intent intent = null; // Activity를 이동할 때 클래스 정보를 담아줄 intent 선언
        // 버튼의 id를 받아올 v가 무슨 버튼을 받아올지 모르기 때문에 switch문 이용
        switch(v.getId()) {
            case R.id.btn:
                intent = new Intent(MenuActivity.this, MainActivity.class); // (어디에서, 어디로) 이동할지 인텐트 참조
                break;
            case R.id.btn1:
                intent = new Intent(MenuActivity.this, MainActivity2.class);
                break;
            case R.id.btn2:
                intent = new Intent(MenuActivity.this, MainActivity3.class);
                break;
            case R.id.btn3:
                intent = new Intent(MenuActivity.this, MainActivity4.class);
                break;
            case R.id.btn4:
                intent = new Intent(MenuActivity.this, MainActivity5.class);
                break;
        }
        startActivity(intent); // 인텐트에 대해 startActivity()수행
    }
}
