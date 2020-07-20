package com.example.ex04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView image = findViewById(R.id.image);
                TextView name = findViewById(R.id.name);
                if(image.getVisibility() == View.VISIBLE){ // 만약 image가 보이는 것을 가져왔을 때 보인다면
                    image.setVisibility(View.INVISIBLE); // 안보이게 함
                    name.setVisibility(View.INVISIBLE);
                }else{ // 보이지 않는다면
                    image.setVisibility(View.VISIBLE); // 보이게 함
                    name.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
