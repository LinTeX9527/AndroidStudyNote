package com.lintex9527.runoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Button 按下、弹起、禁止时设置不同的背景图，参见教程：
 * http://www.runoob.com/w3cnote/android-tutorial-button-imagebutton.html
 *
 * 现在 res/drawable 目录下新建一个根元素为 selector 的XML文件
 * 然后再给某一个按钮的背景图设置为刚才的XML文件
 *
 * 按这篇博文为什么没有效果呢？
 *
 * 一个复杂的Button按钮状态，状态定义见文件 button_danger_rounded.xml ，其中应用了 color, dimens
 * 
 */
public class TestButtonActivity extends AppCompatActivity {

    private Button btnOne, btnTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_button);

        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnOne.getText().toString().equals("不可用")) {
                    btnOne.setText("可用");

                    btnTwo.setEnabled(false);
                } else {
                    btnOne.setText("不可用");

                    btnTwo.setEnabled(true);
                }
            }
        });
    }
}
