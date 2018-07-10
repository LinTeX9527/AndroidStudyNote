package com.lintex9527.runoob;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

/**
 *
 * Chronometer
 * 最好用做定时器，定时到未来的某一个时刻。
 * 不适合作为倒计数的秒表。
 * http://www.runoob.com/w3cnote/android-tutorial-date-time-1.html
 *
 */
public class TestTextClockActivity extends AppCompatActivity {

    private static final String TAG = TestTextClockActivity.class.getName();

    private Chronometer mChronometer;
    private Button btnStart, btnStop, btnReset, btnFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_text_clock);

        initGUI();
    }

    private void initGUI() {
        mChronometer = findViewById(R.id.chronometer);
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
        btnReset = findViewById(R.id.btn_reset);
        btnFormat = findViewById(R.id.btn_format);

        mChronometer.setOnChronometerTickListener(myChronometerClickListener);
        btnStart.setOnClickListener(myButtonClickListener);
        btnStop.setOnClickListener(myButtonClickListener);
        btnReset.setOnClickListener(myButtonClickListener);
        btnFormat.setOnClickListener(myButtonClickListener);



        findViewById(R.id.btnDatePicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.lintex9527.runoob.TestDateTimeActivity"));
            }
        });

        findViewById(R.id.btnTimePicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.lintex9527.runoob.TestTimePickerActivity"));
            }
        });

    }


    View.OnClickListener myButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_start:
                    mChronometer.start(); // 开始计时
                    break;
                case R.id.btn_stop:
                    mChronometer.stop(); // 停止计时
                    break;
                case R.id.btn_reset:
                    mChronometer.setBase(SystemClock.elapsedRealtime() + 10); // 复位
                    mChronometer.start();
                    break;
                case R.id.btn_format:
                    mChronometer.setFormat("Time:%s"); // 更改时间显示格式
                    break;
            }
        }
    };

    /**
     * 基本上可以确定这个 OnChronometerTickListener 是每隔一秒钟响应一次。
     */
    Chronometer.OnChronometerTickListener myChronometerClickListener = new Chronometer.OnChronometerTickListener() {
        @Override
        public void onChronometerTick(Chronometer chronometer) {
            Log.d(TAG, "onChronometerTick()");
            String time = mChronometer.getText().toString();
            if (time.equals("00:10") || time.equals("Time:00:10")) { // 时间到了
                Toast.makeText(getBaseContext(), "时间到了", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
