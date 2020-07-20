package com.example.ex10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView img1, img2; // ImageView 를 저장할 ImageView 형의 변수 2개를 전역 변수로 선언 (MainActivity 전체에서 사용 가능하게)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.img1); // Create 될 때 id를 읽어들임
        img2 = findViewById(R.id.img2);
        registerForContextMenu(img1); // img1과 img2에 대해 생성된 context 메뉴와 연결해줌
        registerForContextMenu(img2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { // context 메뉴 생성. id값이 View의 v로 들어옴.
        // (메뉴는 생성만 되고 연결되진 않으므로 onCreate에서 연결해줌)
        if (v == img1) {
            menu.setHeaderTitle("현대자동차"); // 헤더 설정 (메뉴의 타이틀 설정)
            menu.add(0, 1, 0, "그랜져"); // i는 인덱스 번호
            menu.add(0, 2, 0, "소나타");
            menu.add(0, 3, 0, "제네시스");
        }
        if (v == img2) {
            menu.setHeaderTitle("기아자동차"); // 헤더 설정 (메뉴의 타이틀 설정)
            menu.add(0, 4, 0, "K7");
            menu.add(0, 5, 0, "K5");
            menu.add(0, 6, 0, "카니발");
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) { // context 메뉴에 대한 선택 이벤트
        switch (item.getItemId()) { // onCreateContextMenu 메소드에서 menu.add()의 괄호 안의 두번째 요소인 i1에 대한 id를 가져옴 (item.getItemId())
            case 1:
                img1.setImageResource(R.drawable.grandeur);
                break;
            case 2:
                img1.setImageResource(R.drawable.sonata);
                break;
            case 3:
                img1.setImageResource(R.drawable.genesis);
                break;
            case 4:
                img2.setImageResource(R.drawable.k7);
                break;
            case 5:
                img2.setImageResource(R.drawable.k5);
                break;
            case 6:
                img2.setImageResource(R.drawable.carnival);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
