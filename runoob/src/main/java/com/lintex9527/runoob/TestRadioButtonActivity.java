package com.lintex9527.runoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * 参见教程：
 * http://www.runoob.com/w3cnote/android-tutorial-radiobutton-checkbox.html
 *
 * RadioButton 需要放到 RadioGroup 组中，才能达到只能选择一个的效果。
 */
public class TestRadioButtonActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonMan;
    private RadioButton mRadioButtonWoman;
    private Button mButtonPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_radio_button);

        /**
         * 第一种获取RadioButton选中状态的方式：通过RadioGroup的监听器 onCheckedChangeListener
         */
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                Toast.makeText(getApplicationContext(), "选择：" + radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        mButtonPost = (Button) findViewById(R.id.btnPost);
        mButtonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 第二种方法：通过RadioGroup遍历每一个子RadioButton
                 */
                for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
                    RadioButton rd = (RadioButton) mRadioGroup.getChildAt(i);
                    if (rd.isChecked()) {
                        Toast.makeText(getApplicationContext(), "选择：" + rd.getText(), Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });

    }
}
