package com.lintex9527.runoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * 拖动条
 * http://www.runoob.com/w3cnote/android-tutorial-seekbar.html
 *
 *
 */
public class TestSeekBarActivity extends AppCompatActivity {


    private SeekBar mSeekBar;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_seek_bar);

        initGUI();
    }


    private void initGUI() {
        mSeekBar = findViewById(R.id.seekbar);
        mTextView = findViewById(R.id.tv_seekbar);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTextView.setText("当前进度值：" + progress + " / 100");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

