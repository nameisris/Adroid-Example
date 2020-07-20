package com.example.ex09;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn, btn1; // MainActivity 내에서 전역변수로서 사용하기 위해

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn); // btn과 btn1에 대한 id를 읽어들임
        btn1 = findViewById(R.id.btn1);
        registerForContextMenu(btn); // context menu를 btn에 등록
        registerForContextMenu(btn1); // context menu를 btn1에 등록
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { // context 메뉴 생성
        if (v == btn) { // v가 btn이면 크기변경하는 메뉴를 생성
            menu.setHeaderTitle("크기변경"); // 메뉴 생성
            menu.add(0, 1, 0, "50px"); // 메뉴의 옵션 생성
            menu.add(0, 2, 0, "100px");
            menu.add(0, 3, 0, "150px");
        }
        if (v == btn1) { // v가 btn1이면 크기변경하는 메뉴를 생성
            menu.setHeaderTitle("색상변경"); // 메뉴 생성
            menu.add(0, 4, 0, "Black"); // 메뉴의 옵션 생성
            menu.add(0, 5, 0, "Red");
            menu.add(0, 6, 0, "Blue");
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) { // context 메뉴에 대한 선택 이벤트
        switch (item.getItemId()) { // onCreateContextMenu 메소드에서 menu.add()의 괄호 안의 두번째 요소인 i1에 대한 id를 가져옴 (item.getItemId())
            case 1: // 가져온 id가 1인 경우
                btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50);
                break;
            case 2:
                btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, 100);
                break;
            case 3:
                btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, 150);
                break;
            case 4:
                btn1.setTextColor(Color.BLACK);
                break;
            case 5:
                btn1.setTextColor(Color.RED);
                break;
            case 6:
                btn1.setTextColor(Color.BLUE);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 메뉴를 MainActivity 상에 생성
        getMenuInflater().inflate(R.menu.main, menu); // 두 번째의 menu는 매개변수의 menu (inflate는 전개한다는 뜻)
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // 메뉴에서 어떤 아이템(옵션)을 받는지가 매개변수인 item을 통해서 받아옴.
        switch (item.getItemId()) {
            case R.id.size1: // 50px
                btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50); // 50px라 적지 않고 이와 같이 앞쪽에서 지정
                break;
            case R.id.size2: // 100px
                btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, 100);
                break;
            case R.id.size3: // 150px
                btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, 150);
                break;
            case R.id.color1: // black
                btn.setTextColor(Color.BLACK);
                break;
            case R.id.color2: // red
                btn.setTextColor(Color.RED);
                break;
            case R.id.color3: // blue
                btn.setTextColor(Color.BLUE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) { // 메뉴에서 선택한 옵션에 대해, 다시 메뉴를 열었을 때 바뀐 옵션으로 나타내기 위해
        int size = (int)btn.getTextSize(); // size 변수로 btn의 글자크기를 받아옴 (int로 형변환하여 받아옴)
        switch (size) { // int형으로 형변환된 size에 대한 switch문
            case 50:
                menu.findItem(R.id.size1).setChecked(true);
                break;
            case 100:
                menu.findItem(R.id.size2).setChecked(true);
                break;
            case 150:
                menu.findItem(R.id.size3).setChecked(true);
                break;
        }
        int color = btn.getTextColors().getDefaultColor(); // 디폴트 색상으로 바꿔줌
        switch (color) {
            case Color.BLACK:
                menu.findItem(R.id.color1).setChecked(true);
                break;
            case Color.RED:
                menu.findItem(R.id.color2).setChecked(true);
                break;
            case Color.BLUE:
                menu.findItem(R.id.color3).setChecked(true);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }
}
