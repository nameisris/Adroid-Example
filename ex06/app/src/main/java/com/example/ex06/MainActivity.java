package com.example.ex06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView image; // 전역변수로서 선언 (모든 메소드에서 사용하기 위해)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.image); // onCreate가 시작될 시 이미지를 받아옴 (아마 기본이미지로 설정된 car1)
        findViewById(R.id.btn1).setOnLongClickListener(mLong); // 1번을 롱클릭 했을때 mLong을 실행. onCreate되었을 때 실행가능
        findViewById(R.id.btn2).setOnLongClickListener(mLong);
        findViewById(R.id.btn3).setOnLongClickListener(mLong);
        findViewById(R.id.btn4).setOnLongClickListener(mLong);
        findViewById(R.id.btn5).setOnLongClickListener(mLong);

        // 1~100까지 합을 토스트로 출력
        int num = 1;
        int sum = 0;
        while(num < 101) {
            sum = sum + num;
            num++;
        }
        String strSum = String.valueOf(sum);
        Toast.makeText(MainActivity.this, strSum, Toast.LENGTH_SHORT).show();
    }

    // 롱클릭 메소드
    Button.OnLongClickListener mLong = new View.OnLongClickListener() { // 롱클릭을 했을 때
        String str;
        @Override
        public boolean onLongClick(View view) {
            switch (view.getId()){
                case R.id.btn1:
                    str = "1번째 이미지";
                    break;
                case R.id.btn2:
                    str = "2번째 이미지";
                    break;
                case R.id.btn3:
                    str = "3번째 이미지";
                    break;
                case R.id.btn4:
                    str = "4번째 이미지";
                    break;
                case R.id.btn5:
                    str = "5번째 이미지";
                    break;
            }
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            return false; // 반환값이 false이므로 롱클릭과 일반클릭이 다 적용되어, 롱클릭시 텍스트 출력뿐만 아니라 이미지 변경도 됨.
        }
    };

    // 클릭 이벤트
    public void mClick(View v){ // 어떤 버튼이 눌려졌는지 View 형태로 받아옴

        switch(v.getId()){
            case R.id.btn1:
                image.setImageResource(R.drawable.car1);
                break;
            case R.id.btn2:
                image.setImageResource(R.drawable.car2);
                break;
            case R.id.btn3:
                image.setImageResource(R.drawable.car3);
                break;
            case R.id.btn4:
                image.setImageResource(R.drawable.car4);
                break;
            case R.id.btn5:
                image.setImageResource(R.drawable.car5);
                break;
        }
    }
}
