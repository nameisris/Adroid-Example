package com.example.ex15;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView name, add, age, tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        add = findViewById(R.id.add);
        age = findViewById(R.id.age);
        tel = findViewById(R.id.tel);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) { // 수정 버튼
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                String strName = name.getText().toString(); // 현재 name의 값을 String 형으로 받아옴
                String strAdd = add.getText().toString();
                String strAge = age.getText().toString();
                String strTel = tel.getText().toString();
                // int intAge - Integer.parseInt(strAge);

                intent.putExtra("name", strName); // intent에 함께 가져갈 값을 넣어줌
                intent.putExtra("add", strAdd);
                intent.putExtra("age", strAge);
                intent.putExtra("tel", strTel);
                // intent.putExtra("age", intAge);
                startActivityForResult(intent, 0); // 2번째 항목은 아무 값이나 넣어도 됨
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // MainActivity2로부터 받은 Intent를 data란 이름의 매개변수로 받아와서 사용.
        if(resultCode == 1) { // 참일 경우
            name.setText(data.getStringExtra("name")); // getStringExtra를 통해서 "name"을 받아와 name에 set
            add.setText(data.getStringExtra("add"));
            age.setText(data.getStringExtra("age"));
            tel.setText(data.getStringExtra("tel"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
