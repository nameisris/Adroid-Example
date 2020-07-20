package com.example.ex14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void mClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                intent = new Intent(MenuActivity.this, MainActivity3.class);
                startActivity(intent);
        }
    }
}
