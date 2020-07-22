package com.example.ex20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DataBase db;
    SQLiteDatabase sql;
    Cursor cursor;
    MyAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 액션바
        getSupportActionBar().setTitle("상품관리");

        // 데이터베이스 생성
        db = new DataBase(this); // 생성
        sql = db.getReadableDatabase(); // 오픈 (생성된 데이터베이스인 db의 값을 열어서 sql에 넣어줌)
        cursor = sql.rawQuery("select * from product", null);
        // 데이터베이스를 생성한 db를 오픈한 sql에서 필요한 정보를 읽어와 cursor에 넣어줌
        // (product 테이블로부터 select 해와서 cursor에 넣어줌)

        // 즉, cursor는 데이터베이스의 정보에 대해 list와 adapt하여 화면상에 리스트뷰로 출력해주기 위함
        // 데이터베이스는 이미 DataBase.java 클래스에서 준비하여 위쪽의 db 행에서 이미 생성된 것임

        // 어댑터 생성
        ad = new MyAdapter(this, cursor); // cusor와 adapt해줌

        // ListView <- 어댑터
        ListView list = findViewById(R.id.list);
        list.setAdapter(ad); // 화면의 리스트와 adapt 해줌
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 액션바에 + 메뉴 생성
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // 액션바의 add 아이콘 입력 이벤트로 add.xml의 UI로 창? 생성
        final LinearLayout layout = (LinearLayout)getLayoutInflater().inflate(R.layout.add, null); // R.layout.add를 LinaerLayout으로 형변환하여 layout에 펼처넣어줌
        switch (item.getItemId()) {
            case R.id.add:
                AlertDialog.Builder box = new AlertDialog.Builder(this); // this에 box 만듬?
                box.setTitle("상품정보입력");
                box.setView(layout);
                box.setPositiveButton("저장", new DialogInterface.OnClickListener() { // 값 입력 후 저장 버튼을 눌렀을 때
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText name = layout.findViewById(R.id.name); // add.xml에서 가져오기 위해 add.xml을 받아온 layout을 사용 (현재 액티비티는 activity_main.xml을 사용하기에)
                        String strName = name.getText().toString();
                        EditText price = layout.findViewById(R.id.price);
                        String strPrice = price.getText().toString();
                        int intPrice = Integer.parseInt(strPrice);

                        String str = "insert into product(name, price) values('";
                        str += strName + "', " + intPrice + ")"; // += : 위 문장과 연결하라
                        // String str = "insert into product(name, price) values('" + strName + "', " + intPrice + ")";
                        // 그냥 외워야되는 문법임

                        sql.execSQL(str); // str 문장을 실행하라 (저장 실행)
                        cursor = sql.rawQuery("select * from product", null); // 입력한 값이 다시 select 되어
                        ad.changeCursor(cursor); // 어댑터를 change된 cursor와 다시 adapt
                        // 데이터베이스에서는 이미 저장이 되었지만 화면상에서는 저장이 안되었으므로
                        // 화면의 리스트뷰와 adapt된 cursor에 대하여
                        // change된 cursor를 다시 adapt
                    }
                });
                box.setNegativeButton("취소", null);
                box.show();
        }
        return super.onOptionsItemSelected(item);
    }

    // 어댑터 정의
    class MyAdapter extends CursorAdapter{ // cursor의 사이즈만큼 넘어감. size 정해주지 않아도 됨.

        public MyAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View view = getLayoutInflater().inflate(R.layout.item_main, viewGroup, false); // item_main.xml의 UI를 받아와서 return
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor c) { //
            TextView name = view.findViewById(R.id.name); // 위의 newView 메소드에서 반환되는 view가 들어감
            name.setText(c.getString(1)); // 데이터베이스를 읽어온 cursor에서의 1번째 String을 get해와서 name에 set해줌
            TextView price = view.findViewById(R.id.price);
            price.setText(c.getString(2) + "원"); // 가격이라 "원" 붙여줌
            // 각 항목의 아이디를 int형으로 받아오기
            final int id = c.getInt(0); // c(cursor)의 0번째는 id (1, 2번째는 아마 name과 price)

            ImageView btn = view.findViewById(R.id.btn); // 휴지통 아이콘
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder box = new AlertDialog.Builder(MainActivity.this);
                    box.setTitle("질의" + id);
                    box.setMessage("삭제하실래요?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String str = "delete from product where _id" + id; // 현재 선택한 항목의 idd에 대해 delete
                            sql.execSQL(str); // str에서 설정한 삭제 문법을 실행

                            cursor = sql.rawQuery("select * from product", null); // 바뀐 항목들로 다시 select
                            ad.changeCursor(cursor); // 어댑터를 change된 cursor와 다시 adapt
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
