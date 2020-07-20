package com.example.ex15;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    EditText name, add, age, tel;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name = findViewById(R.id.name);
        add = findViewById(R.id.add);
        age = findViewById(R.id.age);
        tel = findViewById(R.id.tel);

        intent = getIntent(); // Intent값 가져옴
        String strName = intent.getStringExtra("name"); // 보낼때 정한 "name"이란 값으로 똑같이 받아옴
        String strAdd = intent.getStringExtra("add");
        String strAge = intent.getStringExtra("age");
        String strTel = intent.getStringExtra("tel");
        // int intAge = intent.getIntExtra("age", 0);

        name.setText(strName); // MainActivity에서 받아온 String 값을 TextView인 name과 add에 Set해줌
        add.setText(strAdd);
        age.setText(strAge);
        tel.setText(strTel);
        // age.setText(intAge + "");

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) { // 저장 버튼을 눌렀을 때
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity2.this);
                alert.setTitle("질의");
                alert.setMessage("저장하시겠습니까?");
                alert.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String strName = name.getText().toString(); // 새로 입력된 상태인 EditText형 name의 값을 String 변수에 String형으로 넣어줌
                        String strAdd = add.getText().toString(); // (MainActivity에 다시 보내주기 위해)
                        String strAge = age.getText().toString();
                        String strTel = tel.getText().toString();

                        // Intent intent = new Intent();
                        intent.putExtra("name", strName); // intent에 strName을 "name"이라는 이름으로 넣음
                        intent.putExtra("add", strAdd);
                        intent.putExtra("age", strAge);
                        intent.putExtra("tel", strTel);
                        setResult(1, intent); // setResult() 메소드를 이용한 결과값 저장
                        // 현재 메소드는 저장 버튼이므로 RESULT_OK를 통해 결과값이 성공됐음을 세팅
                        // + resultCode를 1로 저장하여 MainActivity의 onActivityResult 메소드 내의 if문에서 1일 때의 조건에서만 수행하도록 함
                        finish(); // 현재 액티비티가 닫히면서 intent의 값을 가진 상태로 MainActivity로 돌아감
                    }
                });
                alert.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity2.this, "취소", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        });

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(0, intent); // setResult() 메소드를 이용한 결과값 저장.
                //
                // 현재 메소드는 취소 버튼이므로 RESULT_CANCELED를 통해 취소됐음을 세팅
                finish(); // 액티비티가 닫히도록 함
            }
        });
    }

}
