package com.example.ex05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    EditText num1, num2;
    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
    }
    // 클릭 메소드
    public void mClick(View v){
        String strNum1 = num1.getText().toString();
        int intNum1 = Integer.parseInt(strNum1);
        String strNum2 = num2.getText().toString();
        int intNum2 = Integer.parseInt(strNum2);

        switch (v.getId()){
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
                result = (double) intNum1 / intNum2;
                break;
        }
        Toast.makeText(MainActivity3.this, "결과 : " + result, Toast.LENGTH_SHORT).show();
    }
}
