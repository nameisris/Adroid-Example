package com.example.ex11;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }

    public void mClick (View v) {
        switch(v.getId()) {
            case R.id.btn1:
                AlertDialog.Builder alert = new AlertDialog.Builder(this); // 대화상자 사용을 위해 AreltDialog.Builder형 alert 생성
                alert.setTitle("알립니다."); // 대화상자 타이틀
                alert.setMessage("대화상자가 열렸습니다."); // 대화상자 내용
                alert.setPositiveButton("닫기", null); // 닫기 버튼. 리스너로 null값을 주면 닫기 기능을 함
                alert.show(); // 대화상자 보여줌?
                break;
            case R.id.btn2:
                alert = new AlertDialog.Builder(this);
                alert.setTitle("알립니다.");
                alert.setMessage("저장하시겠습니까?");
                alert.setPositiveButton("저장", new DialogInterface.OnClickListener() { // 클릭했을때
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity5.this, "저장", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity5.this, "취소", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
                break;
        }
    }
}
