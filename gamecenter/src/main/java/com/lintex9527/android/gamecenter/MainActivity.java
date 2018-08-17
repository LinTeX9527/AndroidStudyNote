package com.lintex9527.android.gamecenter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.elvishew.xlog.Logger;
import com.elvishew.xlog.XLog;

/**
 * 模拟一个登录界面，有以下几点要求：
 * 1、注册时只要求用户名和密码不为空即可；
 * 2、注册时检查是否已经存在同名用户，如果存在则提示已存在，注册失败，否则提示注册成功；
 * 3、注册成功的用户名和密码都保存到本应用相关的SharedPreferences中；
 * 4、注册成功跳到“欢迎新用户界面”；
 * 5、记住密码功能说明：只针对用户名生效。开启时，如果登录成功，则下一次回到这个界面直接加载这个用户名，
 *    没有开启时，进入这个界面用户输入框是空的。
 * 6、忘记密码：To be continued.
 * 7、登录功能：验证用户名和对应的密码是否存在，二者都匹配则跳转到“欢迎老用户界面”；失败则提示密码和用户名不匹配（需要检查是否为空）；
 */
public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private Context mContext = null;

    // 控件
    private EditText editUsername = null;
    private EditText editPassword = null;
    private RadioButton rdbtnRemUsername = null;
    private TextView tvForgetPassword = null;
    private Button btnLogin = null;
    private Button btnRegister = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XLog.d("onCreate() 主界面初始化");
        mContext = MainActivity.this;
        bindViews();
    }


    /**
     * 绑定控件
     */
    private void bindViews(){
        editUsername = findViewById(R.id.edit_username);
        editPassword = findViewById(R.id.edit_password);
        rdbtnRemUsername = findViewById(R.id.rdbtn_remember_username);
        tvForgetPassword = findViewById(R.id.tv_forget_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

        rdbtnRemUsername.setOnCheckedChangeListener(this);
        tvForgetPassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 另外一种局部使用XLog的方式
        Logger logger = XLog.tag("destroy").build();
        logger.d("onDestory() 销毁活动");

    }


    /**
     * 单选按钮选中状态发生了变化
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }


    /**
     * 控件发生单击时的事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_forget_pwd: // 忘记了用户密码，需要申请验证，修改密码
                to_be_continued();
                break;
            case R.id.btn_login:     // 登录按钮
                if (checkInputValidation()) { // 检验输入合法，可以登录

                } else { // 检验失败，不允许登录
//                    showMessage("请重新输入用户名和密码");
                }
                break;
            case R.id.btn_register:
                break;
        }
    }


    /**
     * 检查EditText中用户名和密码是否有效。
     * @return 如果两者都不为空，则返回true；如果某一个为空则返回false，同时弹出为空的提示。
     */
    private boolean checkInputValidation() {
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        if (username.isEmpty()) {
            showMessage("用户名不能为空");
            return false;
        } else if (password.isEmpty()){
            showMessage("密码不能为空");
            return false;
        } else {
            XLog.d("用户名：" + username + ", 密码：" + password);
            return true;
        }
    }


    /**
     * 功能暂未实现的提示
     */
    private void to_be_continued() {
        showMessage(getString(R.string.to_be_continued));
    }

    /**
     * 通过Toast弹出提示消息
     * @param msg 需要显示的消息
     */
    private void showMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
