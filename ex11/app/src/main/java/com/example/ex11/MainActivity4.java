package com.example.ex11;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity4 extends AppCompatActivity {
    ProgressBar pro1, pro2;
    TextView date, time;
    int mYear, mMonth, mDay; // 현재 년, 월, 일
    int mHour, mMinute; // 시, 분

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        pro1 = findViewById(R.id.pro1);
        pro2 = findViewById(R.id.pro2);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);

        Calendar cal = new GregorianCalendar(); // Calendar 객체를 이용
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH); // 해당 달의 일자
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);
        updateNow(); // 년, 월, 일을 구한 후 TextView인 date에 찍음
    }

    // 날짜출력 메소드
    private void updateNow() {
        date.setText(String.format("%d-%d-%d", mYear, mMonth + 1, mDay)); // 월이 0월부터 시작해서 정상적인 출력을 위해 mMonth + 1을 해줌
        time.setText(String.format("%d시 %d분", mHour, mMinute));
    }

    public void mClick(View v) {
        switch (v.getId()) {
            case R.id.btn1: // 감소
                pro1.incrementProgressBy(-10); // incrementProgressBy(int diff) : 현재 설정된 프로그레스바의 값을 기준으로 더해주거나 뺌
                break;
            case R.id.btn2: // 증가
                pro1.incrementProgressBy(+10);
                break;
            case R.id.btn3: // 시작
                pro2.setVisibility(View.VISIBLE);
                break;
            case R.id.btn4: // 멈춤
                pro2.setVisibility(View.INVISIBLE);
                break;
            case R.id.date: // 현재 날짜
                new DatePickerDialog(MainActivity4.this, mDateSet, mYear, mMonth, mDay).show();
                break;
            case R.id.time:
                new TimePickerDialog(MainActivity4.this, mTimeSet, mHour, mMinute, false).show();
        }
    }

    // 날짜변경 리스너
    DatePickerDialog.OnDateSetListener mDateSet = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1;
            mDay = i2;
            updateNow();
        }
    };

    // 시간변경 리스너
    TimePickerDialog.OnTimeSetListener mTimeSet = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            mHour = i;
            mMinute = i1;
            updateNow();
        }
    };
}
