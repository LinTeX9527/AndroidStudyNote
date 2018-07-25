package com.lintex9527.runoob;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lintex9527.androidtools.EditTextWithDel;

/**
 *
 *
 * 参见教程：
 * http://www.runoob.com/w3cnote/android-tutorial-edittext.html
 *
 * 自定义Toast布局，参见
 * http://www.runoob.com/w3cnote/android-tutorial-toast.html
 *
 */
public class TestEditTextActivity extends Activity {

    private static final String TAG = TestEditTextActivity.class.getName();

    private Context mContext;

    private EditTextWithDel editUsername;

    private EditTextWithDel editPassword;

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_edit_text);
        mContext = TestEditTextActivity.this;

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

                if (editUsername.getText().equals("") || editUsername.getText().length() < 1) {
                    editUsername.setShakeAnimation();
                    showToast("用户名不能为空");
                    return;
                }

                if (editPassword.getText().equals("") || editPassword.getText().length() < 1) {
                    editPassword.setShakeAnimation();
                    showToast("密码不能为空");
                    return;
                }
            }
        });
    }


    /**
     * 显示自定义的Toast
     * @param msg 提示信息
     */
    private void showToast(String msg) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.view_toast_custom, (ViewGroup) findViewById(R.id.lly_toast));
        ImageView img_logo = (ImageView) view.findViewById(R.id.img_logo);
        TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg);
        tv_msg.setText(msg);

        Toast toast = new Toast(mContext);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
}
