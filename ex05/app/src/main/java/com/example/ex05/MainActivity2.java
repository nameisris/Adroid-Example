package com.example.ex05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    EditText num1, num2;
    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(mClick);

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(mClick);

        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(mClick);

        Button btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(mClick);
    }

    // 버튼을 클릭했을때 메소드
    Button.OnClickListener mClick = new Button.OnClickListener(){
        @Override
        public void onClick(View view) {
            String strNum1 = num1.getText().toString();
            int intNum1 = Integer.parseInt(strNum1);

            String strNum2 = num2.getText().toString();
            int intNum2 = Integer.parseInt(strNum2);

            switch (view.getId()){
                case R.id.btn1:
                    result = intNum1 + intNum2;
                    break;
                case R.id.btn2:
                    result = intNum1 - intNum2;
                    break;
                case R.id.btn3:
                    result = intNum1 * intNum2;
                    break;
                case R.id.btn4:
                    result = (double)intNum1 / intNum2; // 둘 중에 하나를 형변환
                    break;
            }
            Toast.makeText(MainActivity2.this, "결과 : " + result, Toast.LENGTH_SHORT).show();
        }
    };
}
