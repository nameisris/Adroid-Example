package com.example.ex12;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list;
    ArrayList<Address> data;
    AddAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);

        // 데이터 생성
        data = new ArrayList<Address>();
        Address address = new Address("홍길동", "인천 서구"); // Adress 클래스의 생성자를 이용해 Adress형 필드 생성
        data.add(address);
        address = new Address("심청이", "서울 강남구"); // adress의 재사용
        data.add(address);
        address = new Address("강감찬", "충남 아산시");
        data.add(address);
        address = new Address("이몽룡", "경기도 부천시");
        data.add(address);
        address = new Address("김아무개", "강원도 춘천시");
        data.add(address);
        address = new Address("김익명", "충남 천안시");
        data.add(address);

        // 어댑터 생성
        ad = new AddAdapter();

        // ListView에 어댑터를 Set
        list.setAdapter(ad);
    }
    // AddAdapter 정의
    public class AddAdapter extends BaseAdapter { // BaseAdapter를 상속받은 사용자 지정 어댑터
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View v, ViewGroup viewGroup) { // 생성된 여러 개의 리스트뷰에 data에 입력된 여러 정보들을 지정해줌(adapter)
            v = getLayoutInflater().inflate(R.layout.item, viewGroup, false);
            final TextView name = v.findViewById(R.id.name);
            name.setText(data.get(i).name);

            TextView add = v.findViewById(R.id.add);
            add.setText(data.get(i).add);

            Button btn = v.findViewById(R.id.btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("질의");
                    String strName = name.getText().toString();
                    alert.setMessage(strName + "을(를) 삭제하시겠습니까?");
                    alert.setMessage("삭제하시겠습니까?");
                    alert.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i1) {
                            data.remove(i); // i번째 삭제 (i를 final 선언. i는 받아만 오는거고 onClick메소드 안에서는 변경 불가하게 하려고)
                            ad.notifyDataSetChanged(); // Data가 Changed된 것을 알려줌
                        }
                    });
                    alert.setNegativeButton("아니오", null);
                    alert.show();
                }
            });
            return v;
        }
    }
}
