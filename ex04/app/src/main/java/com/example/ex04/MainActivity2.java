package com.example.ex04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout page1 = findViewById(R.id.page1);
                LinearLayout page2 = findViewById(R.id.page2);
                LinearLayout page3 = findViewById(R.id.page3);
                if(page2.getVisibility() == View.VISIBLE){
                    page2.setVisibility(View.INVISIBLE);
                    page1.setVisibility(View.VISIBLE);
                }
                else if(page3.getVisibility() == View.VISIBLE){
                    page3.setVisibility(View.INVISIBLE);
                    page1.setVisibility(View.VISIBLE);
                }
            }
        });

        btn2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout page1 = findViewById(R.id.page1);
                LinearLayout page2 = findViewById(R.id.page2);
                LinearLayout page3 = findViewById(R.id.page3);
                if(page1.getVisibility() == View.VISIBLE){
                    page1.setVisibility(View.INVISIBLE);
                    page2.setVisibility(View.VISIBLE);
                }
                else if(page3.getVisibility() == View.VISIBLE){
                    page3.setVisibility(View.INVISIBLE);
                    page2.setVisibility(View.VISIBLE);
                }
            }
        });

        btn3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout page1 = findViewById(R.id.page1);
                LinearLayout page2 = findViewById(R.id.page2);
                LinearLayout page3 = findViewById(R.id.page3);
                if(page1.getVisibility() == View.VISIBLE){
                    page1.setVisibility(View.INVISIBLE);
                    page3.setVisibility(View.VISIBLE);
                }
                else if(page2.getVisibility() == View.VISIBLE){
                    page2.setVisibility(View.INVISIBLE);
                    page3.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
