package com.example.ex11;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ListView list; // ListView의 id를 저장할 list 선언
    ArrayList<Product> data; // Product형이 들어가는 ArrayList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 데이터 생성
        data = new ArrayList<Product>(); // Product 생략 가능
        Product product = new Product(R.drawable.sonata, "소나타", 3000);
        data.add(product);
        product = new Product(R.drawable.carnival, "카니발", 5000);
        data.add(product);
        product = new Product(R.drawable.grandeur, "그랜저", 8000);
        data.add(product);

        // 어댑터 생성
        // 값이 하나가 아니므로 ArrayAdapter 사용 X
        MyAdapter ad = new MyAdapter();

        // ListView에 어댑터를 Set
        list = findViewById(R.id.list);
        list.setAdapter(ad);
    }

    // MyAdapter 정의
    class MyAdapter extends BaseAdapter{ // android 상에서 기본 제공하는 BaseAdapter를 상속받아 사용
        @Override
        public int getCount() {
            return data.size(); // data의 size를 반환 (3개이므로 3 반환)
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
        public View getView(int i, View view, ViewGroup viewGroup) { // data의 size만큼 View 만듬
            view = getLayoutInflater().inflate(R.layout.item, viewGroup, false); // view에 item형태?를 넣어줌

            ImageView img = view.findViewById(R.id.img); // 참조 (새로 선언한 img에 R.id.img의 주소를 줌(가리키게 함))
            img.setImageResource(data.get(i).img); // data에 들어있는 i번째 img를 가져옴

            final TextView name = view.findViewById(R.id.name);
            name.setText(data.get(i).name);

            TextView price = view.findViewById(R.id.price);
            price.setText(data.get(i).price + "만원"); // price가 int형이므로 문자열을 만들어주기 위해 "만원"을 더해줌

            Button btn = view.findViewById(R.id.btn); // btn이 여러 개라 어떤 btn인지 알아야하므로 view.findViewById(R.id.btn);
            btn.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity2.this, name.getText()+ " 주문하셨습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            return view;
        }
    }
}
