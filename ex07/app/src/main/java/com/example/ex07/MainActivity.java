package com.example.ex07;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView text; // mClick 메소드 상에서 text의 속성을 set해주기 위해 선언
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text); // xml 상의 TextView의 아이디를 통해 TextView를 MainActivity상의 변수 text에 넣어줌
        findViewById(R.id.btn1).setOnLongClickListener(mLong); // 밑에서 만든 롱클릭메소드를 버튼에서 사용할 수 있도록 버튼마다 지정해줌
        findViewById(R.id.btn2).setOnLongClickListener(mLong);
        findViewById(R.id.btn3).setOnLongClickListener(mLong);
    }

    // 클릭메소드
    public void mClick(View v) { // 버튼이 여러 개이므로, 몇 번 버튼을 받는지 View를 통해 받음. xml에서 onClick에 대해 지정한 mClick을 메소드 이름으로 줌
        switch (v.getId()) {
            case R.id.btn1: // btn1일 경우 TextView의 아이디를 알아야 함. 그러므로 전역변수로 TextView 형의 변수 선언
                text.setTextColor(Color.WHITE);
                text.setBackgroundColor(Color.BLACK);
                break;
            case R.id.btn2:
                text.setTextColor(Color.RED);
                text.setBackgroundColor(Color.YELLOW);
                break;
            case R.id.btn3:
                text.setTextColor(Color.BLUE);
                text.setBackgroundColor(Color.GREEN);
                break; // 마지막 case이므로 break 해주어도 되고 안해주어도 됨
        }
    }

    // 롱클릭
    Button.OnLongClickListener mLong=new Button.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            String str = ""; // Toast로 출력해낼 문자열 변수 선언. 초기값으로 빈값을 안주면 57행의 Toast에서 사용 불가
            switch(view.getId()) {
                case R.id.btn1:
                    str = "첫번째 버튼";
                    break;
                case R.id.btn2:
                    str = "두번째 버튼";
                    break;
                case R.id.btn3:
                    str = "세번째 버튼"; // 마지막이라 break 안해줘도 됨. 위의 클릭 메소드와는 다르게 break 안넣음.
            }
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show(); // 어디서 출력할건지, 무엇을 출력할건지, 얼마동안 출력할건지
            return false;
        }
    };
}
