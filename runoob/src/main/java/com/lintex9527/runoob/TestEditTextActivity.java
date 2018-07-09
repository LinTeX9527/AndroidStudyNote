package com.lintex9527.runoob;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lintex9527.androidtools.EditTextWithDel;

/**
 *
 *
 * 参见教程：
 * http://www.runoob.com/w3cnote/android-tutorial-edittext.html
 *
 */
public class TestEditTextActivity extends Activity {

    private static final String TAG = TestEditTextActivity.class.getName();

    private EditTextWithDel editUsername;

    private EditTextWithDel editPassword;

    private Button btnLogin;

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
        editUsername = (EditTextWithDel) findViewById(R.id.edit_username);
        {
            Drawable[] drawables = editUsername.getCompoundDrawables();
            if (drawables.length > 0) {
                drawables[0].setBounds(0, 0, 80, 80);
                editUsername.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
            }
            Log.d(TAG, "editUsername inputtype --> " + editUsername.getInputType());
            Log.d(TAG, "InputType.TYPE_TEXT_VARIATION_PERSON_NAME --> " + InputType.TYPE_TEXT_VARIATION_PERSON_NAME); // 0x60
        }


        // 输入框：密码
        editPassword = (EditTextWithDel) findViewById(R.id.edit_password);
        {
            Drawable[] drawables = editPassword.getCompoundDrawables();
            if (drawables.length > 0) {
                drawables[0].setBounds(0, 0, 80, 80);
                editPassword.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
            }
            Log.d(TAG, "editPassword inputtype --> " + editPassword.getInputType());
            Log.d(TAG, "InputType.TYPE_TEXT_VARIATION_PASSWORD --> " + InputType.TYPE_TEXT_VARIATION_PASSWORD); // 0x80
        }

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "username --> " + editUsername.getText() + ", password --> " + editPassword.getText());

                if (editUsername.getText().equals("")) {
                    editUsername.setShakeAnimation();
                    showToast("用户名不能为空");
                    return;
                }

                if (editPassword.getText().equals("")) {
                    editPassword.setShakeAnimation();
                    showToast("密码不能为空");
                    return;
                }
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(TestEditTextActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
