package com.example.ex11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    ListView list;
    ArrayList<Address> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // 데이터 생성
        data = new ArrayList<>();
        Address address = new Address(R.drawable.ic_launcher_background, "홍길동", "010-0123-4567", "천안시 서북구 백석동");
        data.add(address);
        address = new Address(R.drawable.ic_launcher_background, "김수한무", "010-9876-5432", "천안시 동남구 청수동");
        data.add(address);

        // 어댑터 생성
        AddAdapter ad = new AddAdapter();

        // ListView에 어댑터 Set
        list = findViewById(R.id.list);
        list.setAdapter(ad);
    }

    class AddAdapter extends BaseAdapter {

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
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.item_add, viewGroup, false); // view 생성?

            ImageView img = view.findViewById(R.id.img);
            // 생성해낸 view의 아이디 '주소'를 변수 img에 줌 (img에 view의 img주소를 가리키는 값이 들어있으므로, img에 값을 지정해주는 것이 곧 view의 img의 값을 지정해주는 것과 같음)
            img.setImageResource(data.get(i).img); // data에 들어있는 i번째 img를 가져옴

            TextView name = view.findViewById(R.id.name);
            name.setText(data.get(i).name);

            TextView tel = view.findViewById(R.id.tel);
            tel.setText(data.get(i).tel);

            TextView add = view.findViewById(R.id.add);
            add.setText(data.get(i).add);

            return view;
        }
    }
}
