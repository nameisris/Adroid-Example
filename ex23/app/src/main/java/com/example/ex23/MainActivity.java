package com.example.ex23;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Database db;
    SQLiteDatabase sql;
    MyAdapter ad;
    Cursor cur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionCheck(); // 어플이 시작히기 이전에 EXTERNAL_STORAGE를 쓸 건지 확인함

        getSupportActionBar().setTitle("상품관리");

        db = new Database(this); // this에 데이터베이스 객체? 생성
        sql = db.getReadableDatabase();
        cur = sql.rawQuery("select * from product", null); // product에 있는 모든 데이터를 select하여 cur에 넣어줌
        ad = new MyAdapter(this, cur); // ad는 cur과 adapt된 어댑터

        ListView list = findViewById(R.id.list);
        list.setAdapter(ad);
        registerForContextMenu(list); // list에 Context Menu 등록 (list 롱클릭 시 Context Menu 생성)

        // Floating Action 버튼에 대한 클릭 이벤트
        FloatingActionButton add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class); // MainActivity에서 AddActivity로 이동
                startActivityForResult(intent, 1); // intent에 대한 이동을 한 뒤, 결과값을 가져옴. requestCode에는 인자값을 채워주기 위해 그냥 1 넣음. 여러 액티비티가 있는 경우 구분해주기 위해 requestCode라는 인자값으로 구분
            }
        });
    }

    // 어댑터정의
    class MyAdapter extends CursorAdapter{

        public MyAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) { // item.xml을 생성하여 return해주는 역할
            View view = getLayoutInflater().inflate(R.layout.item, null);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) { // cur에 있는 product의 데이터를 ListView인 list와 연결시켜주어 리스트뷰로 출력?
            TextView name = view.findViewById(R.id.name);
            name.setText(cursor.getString(1)); // 데이터베이스의 데이터가 들어있는 cur의 항목의 1번째 데이터(name)을 setText해줌
            TextView price = view.findViewById(R.id.price);
            price.setText(cursor.getString(2)+"만원"); // 데이터베이스의 데이터가 들어있는 cur의 항목의 2번째 데이터(price)을 setText해줌

            String strFile = cursor.getString(3); // 데이터베이스의 이미지파일을 strFile에 넣어줌
            ImageView image = view.findViewById(R.id.image); // item.xml의 image에 대한 ImageView image
            if(strFile.equals("")|| strFile == "null"){ // 데이터베이스의 이미지파일이 없다면 (공백이라면)
                image.setImageResource(R.drawable.ic_block);
            }
            else{ // 아니라면 (이미지 파일이 있다면(strFile이 있다면))
                Bitmap img = BitmapFactory.decodeFile(strFile);
                image.setImageBitmap(img);
            }

            final int id = cursor.getInt(0);
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
                            String str = "delete from product where _id=" + id;
                            sql.execSQL(str);
                            cur = sql.rawQuery("select * from product", null);
                            ad.changeCursor(cur);
                        }
                    });
                    box.setNegativeButton("아니오", null);
                    box.show();
                }
            });
        }
    }

    // main.xml에서 만든 메뉴 등록
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu); // R.menu.main 메뉴를 생성하고 매개변수로 menu를 줌

        // SearchView에서 글자를 입력할때마다 바뀌는 이벤트
        MenuItem search = menu.findItem(R.id.search); // search를 누르면 searchView가 보이도록 (main.xml)
        SearchView searchView = (SearchView)search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) { // 바뀐 글자가 들어가는 매개변수 s
                String str = "select * from product where name like '%" + s + "%'"; // 부분검색
                cur = sql.rawQuery(str, null);
                ad.changeCursor((cur)); // 상품명을 검색해서 어댑터에 체인지 (상품명을 검색한 순간에 검색된 상품들의 리스트가 보이도록)
                return false; // 검색하여 리스트들을 보여준 뒤 검색창에서 글자를 지우면 다시 원래 리스트뷰로 출력됨
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    // 리스트뷰 롱클릭
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("정렬방식"); // 생성할 메뉴의
        menu.add(0, 1, 0, "상품명 정렬"); // id 같은걸? 줌
        menu.add(0, 2, 0, "가격 정렬(높은 순)");
        menu.add(0, 3, 0, "가격 정렬(낮은 순)");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    // Context Menu 항목 선택 이벤트
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1: // 1번째 항목 선택시
                cur = sql.rawQuery("select * from product order by name", null);
                break;
            case 2:
                cur = sql.rawQuery("select * from product order by price desc", null);
                break;
            case 3:
                cur = sql.rawQuery("select * from product order by price asc", null); // asc 생략한 것과 같음
                break;
        }
        ad.changeCursor(cur);
        return super.onContextItemSelected(item);
    }

    // 액티비티 결과 처리하기
    // AddActivity에서 finish()와 같은 방법으로 MainActivity로 돌아왔을 때의 수행 메소드
    // 데이터베이스의 항목 저장을 한 후 MainActivity로 돌아올 때
    // 저장하지 않고 취소한 후에도 실행됨(아마)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            cur = sql.rawQuery("select * from product", null);
            ad.changeCursor(cur);
            Toast.makeText(MainActivity.this, "등록", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this, "취소", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void permissionCheck(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }
}
