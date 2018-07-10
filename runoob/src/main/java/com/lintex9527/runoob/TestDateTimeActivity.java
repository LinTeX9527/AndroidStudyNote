package com.lintex9527.runoob;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TestDateTimeActivity extends AppCompatActivity {


    private DatePicker mDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_date_time);

        initGUI();
    }

    private void initGUI() {
        mDatePicker = findViewById(R.id.datepicker);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        // 初始化DatePicker，并且设置监听器 OnDateChangeListener()
        mDatePicker.init(year, monthOfYear, dayOfMonth, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("选择的日期是：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
                Toast.makeText(getBaseContext(), stringBuilder.toString() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
