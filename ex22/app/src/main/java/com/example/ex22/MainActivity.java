package com.example.ex22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Database db;
    SQLiteDatabase sql;
    Cursor cur;
    MyAdapter ad;
    String str = "select * from address order by name"; // address로부터의 모든 내용을 name의 이름순으로 나열
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 메인 액티비티
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("주소관리"); // 상단 액션바에 Title 출력

        db = new Database(this);
        sql = db.getReadableDatabase();
        cur = sql.rawQuery(str, null); // 결과가 cur에 들어감

        // 어댑터 생성
        ad = new MyAdapter(this, cur); // 어댑터 ad와 데이터베이스의 결과 cur을 adapt

        // ListView -> Adapter
        list = findViewById(R.id.list);
        list.setAdapter(ad);

        // 액티비티 내에서 메뉴메소드 생성하고 onCreate내에서 register해줌
        registerForContextMenu(list);
    }

    // 어댑터 정의
    class MyAdapter extends CursorAdapter{

        public MyAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) { // newView 즉, 새로운 View를 생성 (item.xml)
            View view = getLayoutInflater().inflate(R.layout.item, viewGroup, false);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView name = view.findViewById(R.id.name); // 현재 view인 item.xml에서 name의 id를 받아옴
            TextView tel = view.findViewById(R.id.tel);
            final TextView address = view.findViewById(R.id.address);

            // 미리 받아옴
            final int id = cursor.getInt(0); // 버튼 이벤트를 위해 id를 판별하기 위한 id값을 받아옴
            final String strName = cursor.getString(1);
            final String strTel = cursor.getString(2);
            final String strAddress = cursor.getString(3);

            name.setText(cursor.getString(1)); // TextView로 받아온 name의 Text를 데이터베이스의 결과가 들어있는 cursor의 1번째 데이터의 String을 가져와서 Set
            tel.setText(cursor.getString(2));
            address.setText(cursor.getString(3));
            // 위 작업을 하면 데이터베이스-어댑터-리스트뷰가 adapt되어 액티비티에 데이터베이스의 결과가 들어간 리스트뷰 출력

            // 연락처 전화걸기
            ImageView btnTel = view.findViewById(R.id.btnTel);
            btnTel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // call 아이콘 클릭 이벤트
                    // 다이얼 패드로 이동한 후 전화걸기
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + strTel)); // 현재 전화번호인 strTel로 전화번호가 찍히면서 다이얼로 이동할 intent
                    startActivity(intent); // intent 실행
                }
            });

            // 연락처 삭제하기
            ImageView delete = view.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder box = new AlertDialog.Builder(MainActivity.this);
                    box.setTitle("질의" + id);
                    box.setMessage("삭제하시겠습니까?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String strSql = "delete from address where _id=" + id;
                            sql.execSQL(strSql);

                            // cursor값 새로 읽어들이고 어댑터 체인지
                            cur = sql.rawQuery(str, null);
                            ad.changeCursor(cur);
                            Toast.makeText(MainActivity.this, "삭제", Toast.LENGTH_SHORT).show();
                        }
                    });
                    box.setNegativeButton("아니오", null);
                    box.show();
                }
            });

            // 연락처 수정하기
            ImageView btnUpdate = view.findViewById(R.id.btnUpdate); // btnUpdate 버튼 아이디 받아옴 (item.xml)
            btnUpdate.setOnClickListener(new View.OnClickListener() { // btnUpdate 버튼 클릭했을 때
                @Override
                public void onClick(View view) { // btnUpdate 버튼 클릭 이벤트
                    View viewUpdate = getLayoutInflater().inflate(R.layout.add, null); // add.xml을 View로 받아옴
                    final EditText uname = viewUpdate.findViewById(R.id.name); // 새로 받아온 viewUpdate의 name에 대한 id를 받아온 uname
                    final EditText utel = viewUpdate.findViewById(R.id.tel);
                    final EditText uaddress = viewUpdate.findViewById(R.id.address);

                    // 수정할 때 선택한 연락처의 정보가 이미 입력된 상황에서 수정을 하기 위해
                    // 다이얼로그 창에 수정할 값으로 들어가야 됨
                    uname.setText(strName); // viewUpdate의 name을 가리키는 uname을 선택한 연락처에 대한 name인 strName으로 set
                    utel.setText(strTel);
                    uaddress.setText(strAddress);

                    AlertDialog.Builder box = new AlertDialog.Builder(MainActivity.this);
                    box.setTitle("주소 수정");
                    box.setView(viewUpdate); // 다이얼로그 box의 View를 viewUpdate로 set
                    box.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String strName = uname.getText().toString();
                            String strTel = utel.getText().toString();
                            String strAddress = uaddress.getText().toString();

                            String unameSql = "update address set name = '"+ strName + "' where _id = '" + id +"'";
                            String utelSql = "update address set tel = '"+ strTel + "' where _id = '" + id + "'";
                            String uaddressSql = "update address set address = '" + strAddress + "' where _id = '" + id + "'";

                            sql.execSQL(unameSql);
                            sql.execSQL(utelSql);
                            sql.execSQL(uaddressSql);

                            // 한 문자열로 하는 방법
                            /*
                            String uSql = "update address set name = '" + strName + "',";
                            uSql += "tel = '" + strTel + "', address = '" + strAddress + "'where _id = " + id;
                            sql.execSQL(uSql);
                            */

                            // cursor값 새로 읽어들이고 어댑터 체인지
                            cur = sql.rawQuery(str, null);
                            ad.changeCursor(cur);

                            Toast.makeText(MainActivity.this, "수정", Toast.LENGTH_SHORT).show();
                        }
                    });
                    box.setNegativeButton("취소", null);
                    box.show();
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // main.xml로 다이얼로그 입력 아이콘 생성 ('+' 아이콘)
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //
        final View view = getLayoutInflater().inflate(R.layout.add, null); // add.xml을 View로 받아옴

        switch (item.getItemId()){ // item에서 가져온(클릭한) id가 무엇인지
            case R.id.add: // add인 경우 ('+' 아이콘 클릭한 경우)
                AlertDialog.Builder box = new AlertDialog.Builder(this); // this에 box라는 다이얼로그 생성
                // box 다이얼로그 설정
                box.setTitle("주소입력");
                box.setView(view);
                box.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { // 저장 버튼 클릭했을 때
                        EditText name = view.findViewById(R.id.name);
                        EditText tel = view.findViewById(R.id.tel);
                        EditText address = view.findViewById(R.id.address);

                        // 아래의 String 변수들을 데이터베이스에 추가하고 새로운 리스트뷰로 생성
                        String strName = name.getText().toString();
                        String strTel = tel.getText().toString();
                        String strAddress = address.getText().toString();

                        String strSql = "insert into address(name, tel, address) values('" + strName +"', '" + strTel +"', '" + strAddress +"')";
                        // 주소 대신 입력받은 변수의 '값'을 준다
                        sql.execSQL(strSql); // 실행 (데이터를 insert)

                        // cursor값 새로 읽어들이고 어댑터 체인지
                        cur = sql.rawQuery(str, null);
                        ad.changeCursor(cur);
                        Toast.makeText(MainActivity.this, "추가", Toast.LENGTH_SHORT).show();
                    }
                });
                box.setNegativeButton("취소", null);
                box.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 컨택스메뉴
    // 롱클릭
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("정렬방식");
        menu.add(0, 1, 0, "이름순 정렬");
        menu.add(0, 2, 0, "전화번호순 정렬");
        menu.add(0, 3, 0, "주소순 정렬");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    // 컨택스메뉴를 선택했을때 이벤트
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                str = "select * from address order by name"; // 이름순 정렬
                break;
            case 2:
                str = "select * from address order by tel"; // 번호순 정렬
                break;
            case 3:
                str = "select * from address order by address"; // 주소순 정렬
                break;
        }
        // 재설정된 데이터베이스 값에 대한 리스트뷰의 출력에 대한 어댑터 재설정?
        cur = sql.rawQuery(str, null);
        ad.changeCursor(cur);
        return super.onContextItemSelected(item);
    }
}
