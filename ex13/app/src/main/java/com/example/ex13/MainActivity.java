package com.example.ex13;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mClick(View v) { // 버튼의 id를 받아올 View v
        switch (v.getId()) {
            case R.id.btn1:
                AlertDialog.Builder alert = new AlertDialog.Builder(this); // this에서 만든다?
                alert.setTitle("알림");
                alert.setMessage("알립니다.");
                alert.setPositiveButton("예", null); // 버튼에 들어갈 텍스트와 작업내용 (작업이 없으면 null)
                alert.show();
                break;
            case R.id.btn2:
                alert = new AlertDialog.Builder(this);
                alert.setTitle("질의");
                alert.setMessage("저장하시겠습니까?");
                alert.setPositiveButton("예", new DialogInterface.OnClickListener() { // 오른쪽
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { // "예" 버튼이 클릭되었을 때
                        Toast.makeText(MainActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("아니오", new DialogInterface.OnClickListener() { // 왼쪽
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
                break;
            case R.id.btn3:
                final String foods[] = new String[ ]{"짜장면", "우동", "짬뽕", "탕수육"}; // 배열선언
                alert = new AlertDialog.Builder(this);
                alert.setTitle("음식을 선택하세요!");
                alert.setItems(foods, new DialogInterface.OnClickListener() { // foods 배열의 항목을 클릭했을때
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { // i는 몇 번째 item인지
                        Toast.makeText(MainActivity.this, foods[i], Toast.LENGTH_SHORT).show();
                        TextView text = (TextView)findViewById(R.id.text); // xml의 TextView 형의 text의 아이디를 TextView형으로 형변환하여 받아옴. (TextView)는 생략가능 (자동으로 해줌)
                        text.setText("선택한 음식 = " + foods[i]); // 형변환하여 받은 text의 문자열을 setText로 지정해줌
                    }
                });
                alert.setPositiveButton("닫기", null);
                alert.show();
                break;
            case R.id.btn4: // order.xml을 띄워야되는데 생성이 안되었으므로 생성작업을 먼저 해줌
                final LinearLayout order = (LinearLayout)v.inflate(this, R.layout.order, null); // this에 R.layout.order를 null 작업방식으로 넣음
                alert =  new AlertDialog.Builder(this);
                alert.setTitle("주문정보");
                alert.setView(order);
                alert.setPositiveButton("주문", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText name = (EditText) order.findViewById(R.id.name); // order.xml에서 가져옴
                        String strName = name.getText().toString(); // 입력할 것이므로 String 형으로 받아옴
                        EditText num = order.findViewById(R.id.num); // 형변환 생략가능 (프로그램 상에서 자동변환)
                        String strNum = num.getText().toString();
                        CheckBox chk = order.findViewById(R.id.chk);
                        String strChk = chk.isChecked() ? "유" : "무"; // 삼항연산자 (chk에서 체크되었나?, 맞다면 "유"을 strChk에 저장, 아니면 "무"를 strChk에 저장)

                        Toast.makeText(MainActivity.this, "제품명 : " + strName + "\n수량 : " + strNum + "\n착불 결제 : " + strChk, Toast.LENGTH_LONG).show();
                    }
                });
                alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
        }
    }
}
