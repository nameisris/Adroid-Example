package com.example.ex04;

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
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = findViewById(R.id.name);
                String strName = name.getText().toString();
                EditText add = findViewById(R.id.add);
                String strAdd = add.getText().toString();

                String str = strName + "\n" + strAdd;
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show(); // 토스트가 튀어나오듯이 텍스트 만들어냄
            }
        });
    }
}
