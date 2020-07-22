package com.example.ex21;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Database db; // 데이터베이스
    SQLiteDatabase sql; // 데이터베이스에 대한 수행?
    Cursor cur; // 데이터베이스의 정보를 어댑터와 연결하여 액티비티에 출력하기 위한 변수
    MyAdapter ad;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 데이터베이스 생성
        db = new Database(this); // 여기서 데이터베이스 생성
        sql = db.getReadableDatabase(); // 데이터베이스를 sql에 오픈
        cur = sql.rawQuery("select * from address order by name", null); // address의 모든 데이터만큼(*) select
        // cur = sql.rawQuery("select * from address", null); 에서 address 뒤에 'order by name'이라는 옵션을 추가해주면 이름순으로 정렬

        // 어댑터 생성
        ad = new MyAdapter(this, cur); // this에서 cur에 adapt

        // 리스트 생성
        list = findViewById(R.id.list); // list 아이디 받아옴
        list.setAdapter(ad); // 리스트뷰 list와 어댑터 ad를 adapt

        Button add = findViewById(R.id.add); // activity_main의 add 버튼
        add.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) { // add 버튼을 눌렀을 때 알림창 생성
                final LinearLayout layout = (LinearLayout)getLayoutInflater().inflate(R.layout.add, null); // 레이아웃이 클릭할 때마다 생성되도록 입력버튼 안에 넣어줌
                AlertDialog.Builder box = new AlertDialog.Builder(MainActivity.this); // this에 box 창 생성
                box.setTitle("주소입력");
                box.setView(layout); // add를 받아온 layout을 보여지도록 set
                box.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText name = layout.findViewById(R.id.name); // 알림창을 위한 xml인 add.xml을 받아온 layout
                        EditText address = layout.findViewById(R.id.address);
                        String strName = name.getText().toString();
                        String strAddress = address.getText().toString();

                        String str = "insert into address(name, address) values(";
                        str += "'" + strName + "',"; // 기존의 값과 연결 (str +=)
                        str += "'" + strName + "')";

                        sql.execSQL(str); // str 문장을 실행하라 (저장 실행)
                        cur = sql.rawQuery("select * from address", null); // 입력한 값이 다시 select 되어
                        ad.changeCursor(cur); // 어댑터를 change된 cursor와 다시 adapt
                        // 데이터베이스에서는 이미 저장이 되었지만 화면상에서는 저장이 안되었으므로
                        // 화면의 리스트뷰와 adapt된 cursor에 대하여
                        // change된 cursor를 다시 adapt
                    }
                });
                box.setNegativeButton("취소", null);
                box.show();
            }
        });
    }

    // 어댑터 정의
    class MyAdapter extends CursorAdapter{

        public MyAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View view = getLayoutInflater().inflate(R.layout.item, viewGroup, false); // 커서의 데이터 개수만큼 view로 보내줌
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) { // 리스트뷰의 각 항목에 대한 설정 (데이터베이스 정보 넣어주는 거랑 삭제 버튼 이벤트)
            TextView name = view.findViewById(R.id.name);
            TextView address = view.findViewById(R.id.address);
            name.setText(cursor.getString(1)); // name에는 데이터베이스 정보가 들어있는 cursor의 1번째 항목인 name을 줌
            address.setText(cursor.getString(2)); // address에는 데이터베이스 정보가 들어있는 cursor의 2번째 항목인 address를 줌

            final int id = cursor.getInt(0); // cursor의 0번째 값인 id를 받아옴
            Button btn = view.findViewById(R.id.btn); // view에 들어있는
            btn.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder box = new AlertDialog.Builder(MainActivity.this);
                    box.setTitle("삭제" + id);
                    box.setMessage("삭제하실래요?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String str = "delete from address where _id=" + id; // 삭제할 아이디가 현재 항목에서 받아온 id
                            sql.execSQL(str);

                            cur = sql.rawQuery("select * from address", null); // 바뀐 항목들로 다시 select
                            ad.changeCursor(cur); // 어댑터를 change된 cur과 다시 adapt
                            // 데이터베이스에서는 이미 삭제가 되었지만 화면상에서는 삭제가 안되었으므로
                            // 화면의 리스트뷰와 adapt된 cursor에 대하여
                            // change된 cursor를 다시 adapt
                        }
                    });
                    box.setNegativeButton("아니오", null);
                    box.show();
                }
            });
        }
    }
}
