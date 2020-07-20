package com.example.ex14;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) { // 수정 버튼을 눌렀을때
                Intent intent = new Intent(MainActivity3.this,  MainActivity4.class);
                text = findViewById(R.id.text);
                String strText = text.getText().toString(); // Sample Data 값이 strText에 저장
                intent.putExtra("text", strText); // 어디에 어떤값을 저장할지
                startActivityForResult(intent, 0); // 액티비티 이동과 동시에 결과를 보내주기 위해 startActivityForResult 사용
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String strText = data.getStringExtra("text"); // 바뀐 "text"값을 받아옴
        text.setText(strText); // TextView 형인 text의 값을 strText 값으로 Set해줌
        super.onActivityResult(requestCode, resultCode, data);
    }
}
