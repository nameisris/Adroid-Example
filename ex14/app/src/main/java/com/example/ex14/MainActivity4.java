package com.example.ex14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity4 extends AppCompatActivity {
    EditText edit;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        intent = getIntent(); // Intent 값 가져옴
        String strText = intent.getStringExtra("text"); // "text"란 값에 저장된 String 값을 strText로 받아옴

        edit = findViewById(R.id.edit);
        edit.setText(strText); // EditText인 edit의 text값을 strText로 Set해줌
    }

    public void mClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                String strEdit = edit.getText().toString();
                intent.putExtra("text", strEdit);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btn2:
                setResult(RESULT_CANCELED, intent);
                finish(); // 취소 버튼. 액티비티가 닫히도록 함
                break;
        }
    }
}
