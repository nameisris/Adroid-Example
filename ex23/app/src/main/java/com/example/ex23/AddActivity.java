package com.example.ex23;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    Database db; // 액티비티가 분리되어있으므로 (MainActivity와) 다시 선언
    SQLiteDatabase sql;
    ImageView image;
    String strFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // ImageView image에 대한 클릭 이벤트 (이미지 클릭시 갤러리에서 이미지 가져오기)
        image = findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //
                startActivityForResult(intent, 1); // 이미지 선택 후 onActivityResult로 돌아오게 하기 위해 startActivityForResult 사용
            }
        });

        db = new Database(this);
        sql = db.getWritableDatabase();

        getSupportActionBar().setTitle("상품등록");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        // 저장 버튼 클릭 이벤트
        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder box = new AlertDialog.Builder(AddActivity.this);
                box.setTitle("질의");
                box.setMessage("저장하시겠습니까?");
                box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { // EditText에 입력한 name, price값 데이터베이스에 추가
                        EditText name = findViewById(R.id.name);
                        EditText price = findViewById(R.id.price);
                        String strName = name.getText().toString();
                        String strPrice = price.getText().toString();
                        String str = "insert into product(name, price, image) values('" + strName +"', " + strPrice + ",'" + strFile + "')";
                        // 입력된 strName, strPrice 값 insert 실행문
                        //  onActivityResult에서 초기화된 이미지가 들어있는 전역변수 strFile을 데이터베이스에 넣어줌
                        sql.execSQL(str); // str 실행문 실행
                        // Toast.makeText(AddActivity.this, "등록완료", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK); // RESULT_OK는 안드로이드에서 정해진 상수
                        finish(); // 새로운 상품 등록 후 현재 액티비티를 finish (MainActivity로 돌아가 onActivityResult 메소드를 수행)
                    }
                });
                box.setNegativeButton("아니오", null);
                box.show();
            }
        });

        // 취소 버튼 클릭 이벤트
        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED); // MainActivity의 onActivityResult메소드에서 if문의 조건으로 사용 (MainActivity로 돌아가 onActivityResult 메소드를 수행)
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: // 안드로이드에서 제공하는 id (백버튼에 대한 아이디 home)을 눌렀을 때
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 이미지 파일 가져오는 코드 (교수님이 구글링해오심)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(data.getData(), projection, null, null, null);
            cursor.moveToFirst();
            strFile = cursor.getString(cursor.getColumnIndex(projection[0])); // 이미지 데이터가 저장되는 strFile
            cursor.close();

            Bitmap img = BitmapFactory.decodeFile(strFile);
            image.setImageBitmap(img);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
