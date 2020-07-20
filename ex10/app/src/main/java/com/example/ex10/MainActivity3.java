package com.example.ex10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    EditText edit; // 불러올 id를 먼저 선언해 두는 것이 편함
    ListView list;
    Button btn1, btn2;
    ArrayList<String> data; // 전역변수로 사용
    ArrayAdapter ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        edit = findViewById(R.id.edit); // xml 상의 id를 읽어옴
        list = findViewById(R.id.list);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        // 데이터 생성
        data = new ArrayList<>(); // ArrayList로 data를 선언해줌. ArrayList 안에는 String이 들어감.
        data.add("포도"); // ArrayList인 data 안에 String을 더해줌 (넣어줌)
        data.add("수박");
        data.add("딸기");

        // 어댑터 생성
        ad = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, data); // 매개변수로 어디에 생성할지, 어댑터의 모양, 어댑터와 연결할 데이터 (리스트)

        // ListView 어댑터 set
        list.setAdapter(ad); // 어댑터 ad는 데이터인 data와 연결되어 있음. 따라서 ListView인 list는 데이터 data와 연결됨.
        // 위 코드까지 코딩 후 실행하면 선택형 리스트가 나오지만, 선택은 불가함. 아래의 코드를 추가해줘야 선택이 가능.
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() { // list 안에 있는 item을 클릭할 때마다 발생하는 이벤트
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = data.get(i); // data에 있는 i번째 데이터를 가져옴.
                Toast.makeText(MainActivity3.this, str, Toast.LENGTH_SHORT).show();
            }
        });

        // btn1을 클릭했을때 (입력 버튼)
        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = edit.getText().toString(); // EditText인 edit에 입력한 내용을 리스트에 추가하게 할 것이므로, edit의 id를 가져옴.
                // EditText가 빈칸이어도 리스트에 여백이 추가되는 문제 (아래 코드)
                if (str.length() == 0) { // str은 문자열 변수이므로 (str == "")와 같은 비교는 안됨. (str.equals(""))으로도 비교 가능.
                    Toast.makeText(MainActivity3.this, "내용을 입력하세요!", Toast.LENGTH_SHORT).show();
                }
                else {
                    data.add(str); // 전역변수인 data를 사용해 리스트에 str에 들어간 문자열 추가
                    ad.notifyDataSetChanged(); // 어댑터에 데이터가 바뀐 것에 대해 알려줌
                    // 아래의 코드로 EditText창에 문자열이 남아있는 문제를 해결
                    edit.setText(""); // EditText에 문자열 입력하고 '입력'버튼을 눌러 리스트에 추가한 이후에도 EditText 창에 문자열이 남아있는 것을 해결.
                }
            }
        });

        // btn2를 클릭했을때 (삭제 버튼)
        btn2.setOnClickListener(new Button.OnClickListener() { // 삭제 버튼인 btn2를 클릭했을때의 메소드
            @Override
            public void onClick(View view) {
                int check = list.getCheckedItemCount(); // list에서 체크된 항목의 개수를 가져옴
                if (check == 0) { // 체크된 항목이 없을 때
                    Toast.makeText(MainActivity3.this, "항목을 선택하세요!", Toast.LENGTH_SHORT).show(); // 토스트로 오류문구 출력
                }
                else { // 체크된 항목이 없을 때를 제외한 경우
                    int position = list.getCheckedItemPosition(); // list에서 체크된 아이템의 포지션을 가져옴.
                    data.remove(position); // 리스트에서 선택된 아이템을 삭제
                    ad.notifyDataSetChanged(); // 어댑터에 데이터가 바뀐 것에 대해 알려줌
                }
            }
        });
    }
}
