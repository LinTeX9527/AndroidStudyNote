package com.lintex9527.runoob;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

public class TestEditTextActivity extends Activity {

    private static final String TAG = TestEditTextActivity.class.getName();

    private EditText editUsername;

    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_edit_text);

        initGUI();
    }

    /**
     * 初始化GUI
     * 设置两个EditText的inputtype，但是Java代码并没有生效，反倒是 XML 文件能生效，为什么？
     * textPersonName 数字为 0x61
     * textPassword 数值为 0x81
     */
    private void initGUI() {
        // 输入框：用户名
        editUsername = (EditText) findViewById(R.id.edit_username);
        {
            Drawable[] drawables = editUsername.getCompoundDrawables();
            drawables[0].setBounds(0, 0, 80, 80);
            editUsername.setCompoundDrawables(drawables[0], null, null, null);
            Log.d(TAG, "editUsername inputtype --> " + editUsername.getInputType());
            Log.d(TAG, "InputType.TYPE_TEXT_VARIATION_PERSON_NAME --> " + InputType.TYPE_TEXT_VARIATION_PERSON_NAME); // 0x60
        }


        // 输入框：密码
        editPassword = (EditText) findViewById(R.id.edit_password);
        {
            Drawable[] drawables = editPassword.getCompoundDrawables();
            drawables[0].setBounds(0, 0, 80, 80);
            editPassword.setCompoundDrawables(drawables[0], null, null, null);
            Log.d(TAG, "editPassword inputtype --> " + editPassword.getInputType());
            Log.d(TAG, "InputType.TYPE_TEXT_VARIATION_PASSWORD --> " + InputType.TYPE_TEXT_VARIATION_PASSWORD); // 0x80
        }
    }
}
