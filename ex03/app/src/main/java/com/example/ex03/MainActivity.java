package com.example.ex03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText kor = findViewById(R.id.kor);
                EditText eng = findViewById(R.id.eng);
                EditText mat = findViewById(R.id.mat);

                String strKor = kor.getText().toString();
                String strEng = eng.getText().toString();
                String strMat = mat.getText().toString();

                int intKor = Integer.parseInt(strKor); // String형 strKor을 int형으로 변환
                int intEng = Integer.parseInt(strEng);
                int intMat = Integer.parseInt(strMat);

                int intTot = intKor + intEng + intMat;
                double dubAvg = intTot/3.;

                TextView tot = findViewById(R.id.tot);
                TextView avg = findViewById(R.id.avg);
                tot.setText("총점 : " + intTot); // 괄호 안에 정해주고자 하는 값을 넣어줌...setText()
                avg.setText("평균 : " + dubAvg);
            }
        });
    }
}
