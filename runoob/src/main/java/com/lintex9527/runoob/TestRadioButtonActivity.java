package com.lintex9527.runoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

    CheckBox cb01;
    CheckBox cb02;
    CheckBox cb03;

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


        // 下面是复选框 CheckBox 的案例
        cb01 = (CheckBox) findViewById(R.id.chk_apple);
        cb02 = (CheckBox) findViewById(R.id.chk_banana);
        cb03 = (CheckBox) findViewById(R.id.chk_pineapple);

        cb01.setOnCheckedChangeListener(fruit_chk_listener);
        cb02.setOnCheckedChangeListener(fruit_chk_listener);
        cb03.setOnCheckedChangeListener(fruit_chk_listener);


        Button btnCommit = (Button) findViewById(R.id.btnCommit);
        btnCommit.setOnClickListener(new View.OnClickListener() {
            /**
             * 单击按钮之后，来遍历每一个复选框，得到最终的选择值
             * @param v
             */
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder = new StringBuilder();
                if (cb01.isChecked()) stringBuilder.append(cb01.getText().toString() + " ");
                if (cb02.isChecked()) stringBuilder.append(cb02.getText().toString() + " ");
                if (cb03.isChecked()) stringBuilder.append(cb03.getText().toString() + " ");
                Toast.makeText(getApplicationContext(), stringBuilder.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 复选框单击的监听器
     */
    CompoundButton.OnCheckedChangeListener fruit_chk_listener = new CompoundButton.OnCheckedChangeListener() {
        /**
         * 这里的buttonView代表此刻被单击选中的复选框
         * @param buttonView
         * @param isChecked
         */
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.isChecked()) {
                Toast.makeText(getApplicationContext(), buttonView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    };
}
