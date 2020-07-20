package com.example.ex03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    int cnt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btn1 = findViewById(R.id.btn1); // button을 가져옴
        Button btn2 = findViewById(R.id.btn2);

        // 증가버튼 클릭 (btn1)
        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView count = findViewById(R.id.count); // text를 가져옴
                cnt = cnt + 1;
                count.setText(cnt + ""); // Integer + string은 string (즉, 문자로 바꿔줌)
            }
        });

        // 감소버튼 클릭 (btn2)
        btn2.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView count = findViewById(R.id.count); // text를 가져옴
                cnt = cnt - 1;
                count.setText(cnt + ""); // Integer + string은 string (즉, 문자로 바꿔줌)
            }
        });

        // 증가버튼 롱클릭 (btn1)
        btn1.setOnLongClickListener(new Button.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView count = findViewById(R.id.count);
                cnt = 100;
                count.setText(cnt + "");
                return true; // 롱클릭과 클릭이 중첩되는걸 방지하기 위해 false를 true로
            }
        });

        // 감소버튼 롱클릭 (btn2)
        btn2.setOnLongClickListener(new Button.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView count = findViewById(R.id.count);
                cnt = 0;
                count.setText(cnt + "");
                return true;
            }
        });
    }
}
