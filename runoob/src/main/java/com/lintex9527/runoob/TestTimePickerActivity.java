package com.lintex9527.runoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * http://www.runoob.com/w3cnote/android-tutorial-date-time-2.html
 *
 * 可以自行设定外观：
 * android:timePickerMode="clock" 或者 "spinner"
 */

public class TestTimePickerActivity extends AppCompatActivity {

    private TimePicker mTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_time_picker);

        initGUI();
    }

    private void initGUI() {
        mTimePicker = findViewById(R.id.timepicker);
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("选择的时间是：" + hourOfDay + "时" + minute + "分");
                Toast.makeText(getBaseContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
