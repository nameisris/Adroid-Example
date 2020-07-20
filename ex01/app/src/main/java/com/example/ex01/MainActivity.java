package com.example.ex01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub1);

        // 버튼을 클릭했을때 이름 출력
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = findViewById(R.id.name);
                String strName = name.getText().toString();
                Toast.makeText(MainActivity.this, strName, Toast.LENGTH_SHORT).show();
            }
        });

        // 주소 출력
        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText add = findViewById(R.id.add);
                String strAdd = add.getText().toString();
                Toast.makeText(MainActivity.this, strAdd, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
