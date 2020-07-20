package com.example.ex05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText num1;
    EditText num2;
    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strNum1 = num1.getText().toString(); // 숫자를 입력할 때마다 다른 값이 되므로 onClick 메소드 내에서 초기화
                int intNum1 = Integer.parseInt(strNum1);

                String strNum2 = num2.getText().toString();
                int intNum2 = Integer.parseInt(strNum2);

                result = intNum1 + intNum2;
                Toast.makeText(MainActivity.this, "더한 값 = " + result, Toast.LENGTH_SHORT).show();
            }
        });

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strNum1 = num1.getText().toString();
                int intNum1 = Integer.parseInt(strNum1);

                String strNum2 = num2.getText().toString();
                int intNum2 = Integer.parseInt(strNum2);

                result = intNum1 - intNum2;
                Toast.makeText(MainActivity.this, "뺀 값 = " + result, Toast.LENGTH_SHORT).show();
            }
        });

        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strNum1 = num1.getText().toString();
                int intNum1 = Integer.parseInt(strNum1);

                String strNum2 = num2.getText().toString();
                int intNum2 = Integer.parseInt(strNum2);

                result = intNum1 * intNum2;
                Toast.makeText(MainActivity.this, "곱한 값 = " + result, Toast.LENGTH_SHORT).show();
            }
        });

        Button btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strNum1 = num1.getText().toString();
                int intNum1 = Integer.parseInt(strNum1);

                String strNum2 = num2.getText().toString();
                int intNum2 = Integer.parseInt(strNum2);

                result = intNum1 / intNum2;
                Toast.makeText(MainActivity.this, "나눈 값 = " + result, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
