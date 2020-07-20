package com.example.ex10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    ArrayList<Item> data;
    MyAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // 데이터 생성
        data = new ArrayList<Item>();
        Item item = new Item(R.drawable.grandeur, "그랜저", "80,000,000"); // img와 name을 지정
        data.add(item); // 생성된 item을 data에 넣어줌
        item = new Item(R.drawable.sonata, "소나타", "40,000,000"); // 2행 위에서 생성한 객체를 재사용
        data.add(item);
        item = new Item(R.drawable.carnival, "카니발", "60,000,000");
        data.add(item);

        // 어댑터 생성
        ad = new MyAdapter();
        // ListView에 어댑터를 끼워넣기
        ListView list = findViewById(R.id.list);
        list.setAdapter(ad);
    }

    // 어댑터 정의
    class MyAdapter extends BaseAdapter { // 기본 제공하는 BaseAdapter 클래스를 상속받아 MyAdapter 클래스 생성
        @Override
        public int getCount() {
            return data.size(); // data의 크기 반환
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
        public View getView(int i, View view, ViewGroup viewGroup) { // item1.xml을 inflate하여 return
            view = getLayoutInflater().inflate(R.layout.item1, viewGroup, false);

            ImageView img = view.findViewById(R.id.img); // item1.xml의 id를 가져와 ImageView형 변수 img에 넣어줌
            img.setImageResource(data.get(i).img); // data의 i번째에 있는 img를 가져와서 위에서 만든 img에 set해줌

            TextView name = view.findViewById(R.id.name);
            name.setText(data.get(i).name);
            final String strName = data.get(i).name;

            TextView price = view.findViewById(R.id.price);
            price.setText(data.get(i).price + "원");

            Button btn = view.findViewById(R.id.btn);
            btn.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity4.this, strName + " 주문하셨습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            return view; // item1.xml 형태에 img와 name이 차례대로 들어간 view를 반환하여 리스트뷰에 넣어줌
        } // 안드로이드 스튜디오에서 제공하는 BaseAdapter를 상속받아서 어댑터 클래스 생성
    }

    // Item 클래스
    class Item { // item을 생성해주는 Item 클래스 (붕어빵 틀), ArrayList에 넣어줄 것임. (클래스 이름은 보통 대문자로 시작)
        int img;
        String name;
        String price;
        // 마우스 우클릭 - Generate - Constructor를 통해 생성자 자동 생성
        public Item(int img, String name, String price) {
            this.img = img;
            this.name = name;
            this.price = price;
        }
    }
}
