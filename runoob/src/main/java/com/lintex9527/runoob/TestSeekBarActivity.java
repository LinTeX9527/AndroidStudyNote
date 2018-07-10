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
 * SeekBar 简单定制
 * 第一步：滑块状态Drawable
 * 这里新建文件 seekbar_thumb.xml ，里面指定滑块按下和不按下的两个图标
 *
 * 第二步：条形栏Bar的Drawable
 * 这里新建文件 seekbar_bar.xml，里面指定android资源 background, secondaryProgress, progress 的形态
 *
 * 第三步：在布局文件中指定某一个SeekBar 使用前两部定义的资源
 *
 * android:progressDrawable="@drawable/seekbar_bar"
 * android:thumb="@drawable/seekbar_thumb"
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

