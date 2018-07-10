package com.lintex9527.runoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * 教程：
 * http://www.runoob.com/w3cnote/android-tutorial-togglebutton-switch.html
 *
 * TODO:
 * 按照教程，Switch滑动时会闪屏，为什么？
 */
public class TestToggleButtonActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private ToggleButton togglebtn_open;
    private Switch switch_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle_button);

        togglebtn_open = (ToggleButton) findViewById(R.id.togglebtn_open);
        switch_status = (Switch) findViewById(R.id.switch_status);

        togglebtn_open.setOnCheckedChangeListener(this);
        switch_status.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.togglebtn_open:
                if(buttonView.isChecked()) {
                    Toast.makeText(getApplicationContext(), "打开声音", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "关闭声音", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.switch_status:
                if (buttonView.isChecked()) {
                    Toast.makeText(getApplicationContext(), "开关：ON", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "开关：OFF", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
