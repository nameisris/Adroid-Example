package com.example.ex16;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    EditText edit, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edit = findViewById(R.id.edit);
        name = findViewById(R.id.name);

        Intent intent = getIntent();
        String strEdit = intent.getStringExtra("text");
        String strName = intent.getStringExtra("name");

        edit.setText(strEdit);
        name.setText(strName);

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder box = new AlertDialog.Builder(MainActivity2.this);
                box.setTitle("질의");
                box.setMessage("저장하실래요?");
                box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String strEdit = edit.getText().toString();
                        String strName = name.getText().toString();

                        Intent intent = new Intent();
                        intent.putExtra("text", strEdit);
                        intent.putExtra("name", strName);
                        setResult(1, intent); // MainActivity의 onActivityResult메소드의 if문의 조건을 만족시키기 위해 1을 지정하고 intent값을 반영.
                        finish();
                    }
                });
                box.setNegativeButton("아니오", null);
                box.show();
            }
        });

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(0); // btn1과 달리 현재 메소드는 '취소'버튼이므로 intent를 반영하지 않기에 0만 넣어줌 (MainActivity의 onActivityResult메소드의 if문 사용x)
                finish();
            }
        });
    }
}
