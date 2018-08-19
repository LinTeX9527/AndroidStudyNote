package com.lintex9527.android.gamecenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.elvishew.xlog.Logger;
import com.elvishew.xlog.XLog;
import com.lintex9527.android.database.DBManager;

/**
 * 模拟一个登录界面，有以下几点要求：
 * 1、注册时要求用户名长度>=3，密码长度>=8；(OK)
 * 2、注册时检查是否已经存在同名用户，如果存在则提示已存在，注册失败，否则提示注册成功；(OK)
 * 3、注册成功的用户名和密码都保存到本应用相关的SQLite数据库中；(OK)
 * 4、注册成功跳到“欢迎新用户界面”；(OK)
 * 5、记住密码功能说明：只针对用户名生效。开启时，如果登录成功，则下一次回到这个界面直接加载这个用户名，
 *    没有开启时，进入这个界面用户输入框是空的。(OK)
 * 6、忘记密码：To be continued.
 * 7、登录功能：验证用户名和对应的密码是否存在，二者都匹配则跳转到“欢迎老用户界面”；
 *    失败则提示密码和用户名不匹配（需要检查是否为空）；(OK)
 */
public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    // XLog的调试器
    Logger logger = null;

    // SharedPreferences 文件的名称
    private static final String SETTINGS_FILENAME = "settings";
    // ("check_state", boolean isChecked)记住用户名按钮的状态，根据状态来选择是否加载上一次的用户名
    private static final String KEY_CHECK_STATE = "check_state";
    // ("last_username", String username) 记住上一次输入的用户名字
    private static final String KEY_LAST_USERNAME = "last_username";
    // 合法的用户名长度大于等于3，这个NA是用来表示上一次保存的是无效的用户名
    private static final String USERNAME_INVALED = "NA";
    // 约束用户名和密码的长度
    private static final int USERNAME_MIN_LENGTH = 3;
    private static final int PASSWORD_MIN_LENGTH = 8;

    // 跳转页面时传递的用户名字，对应的KEY
    public static final String KEY_USERNAME = "username";

    private Context mContext = null;
    private DBManager mDBManager = null;
    private SharedPreferences mSharedPreferences = null;
    private SharedPreferences.Editor mEditor = null;
    // 控件
    private EditText editUsername = null;
    private EditText editPassword = null;
    private CheckBox chkRemUsername = null;
    private TextView tvForgetPassword = null;
    private Button btnLogin = null;
    private Button btnRegister = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化数据
        logger = XLog.tag("main-UI").build();
        logger.d("onCreate() 主界面初始化");

        mContext = MainActivity.this;

        mDBManager = new DBManager(mContext);
        mDBManager.open();

        mSharedPreferences = mContext.getSharedPreferences(SETTINGS_FILENAME, MODE_PRIVATE);

        // 初始化UI
        initGUI();
    }


    /**
     * 初始化界面，绑定控件
     */
    private void initGUI(){
        editUsername = findViewById(R.id.edit_username);
        editPassword = findViewById(R.id.edit_password);
        chkRemUsername = findViewById(R.id.chk_remember_username);
        tvForgetPassword = findViewById(R.id.tv_forget_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

        chkRemUsername.setOnCheckedChangeListener(this);
        tvForgetPassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);


        // 根据按钮状态来决定是否加载上一次的用户名
        boolean isChecked = mSharedPreferences.getBoolean(KEY_CHECK_STATE, false);
        chkRemUsername.setChecked(isChecked);

        // 用户名编辑框发生变化时的监听事件--根据需要保存用户名
        editUsername.addTextChangedListener(new EditUsernameWahter());
    }


    /**
     * 清理工作
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 另外一种局部使用XLog的方式
        Logger logger = XLog.tag("destroy").build();
        logger.d("onDestory() 销毁活动");

        // 清理资源
        mDBManager.close();
    }


    /**
     * 单选按钮选中状态发生了变化
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.chk_remember_username: // 记住用户名，把用户名保存到SharedPreferences中
                saveCheckRemUsername(isChecked);
                logger.d("恢复上一次的单选按钮状态：" + isChecked);
                if (isChecked){ // 记住用户名
                    // 先获得上一次保存的用户名
                    String username = mSharedPreferences.getString(KEY_LAST_USERNAME, "");
                    if (USERNAME_INVALED.equals(username)) { // 上一次用户名长度小于3，不应该直接显示到控件上，而应该先保存
                        saveLastUsername(editUsername.getText().toString());
                    } else { // 上一次保存的用户名长度大于等于3，可以加载并显示
                        editUsername.setText(username);
                    }
                } else { // 取消选择，不记住用户名
                    saveLastUsername(USERNAME_INVALED);
                }
                break;
        }
    }


    /**
     * 根据选项框的状态来更新SharedPreferences中的值
     * @param isChecked 选项框是否选中
     */
    private void saveCheckRemUsername(boolean isChecked){
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(KEY_CHECK_STATE, isChecked);
        mEditor.apply();
    }


    /**
     * 保存输入的用户名字到SharedPreferences中，当用户名字长度大于等于3合法时才保存对应的名字，否则保存
     * 一个无效的占位符， NA
     * @param username 用户名字
     */
    private void saveLastUsername(String username) {
        logger.d("保存的用户名是：" + username);
        String save = username;
        if (save.length() < USERNAME_MIN_LENGTH) {
            save = USERNAME_INVALED;
        }
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_LAST_USERNAME, save);
        mEditor.apply();
    }

    /**
     * 控件发生单击时的事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_forget_pwd: // 忘记了用户密码，需要申请验证，修改密码
                to_be_continued(getString(R.string.module_forget_password));
                break;
            case R.id.btn_login:     // 登录按钮
                if (checkInputValidation()) { // 检验输入合法，可以登录
                    String username = editUsername.getText().toString();
                    String password = editPassword.getText().toString();
                    if (mDBManager.match(username, password)) { // 检验是否是一个已经注册成功的用户
                        showMessage("身份认证通过");
                        // TODO: 登录成功，跳转到应用界面
                        Intent intent = new Intent(this, LoginSuccessActivity.class);
                        intent.putExtra(KEY_USERNAME, username);
                        startActivity(intent);
                    } else {
                        showMessage("身份认证失败");
                    }
                }
                break;
            case R.id.btn_register:         // 注册
                if (checkInputValidation()) { // 检查输入合法性
                    String username = editUsername.getText().toString();
                    String password = editPassword.getText().toString();
                    if (mDBManager.match(username)) { // 如果已经存在了同名用户，则不允许注册
                        showMessage("用户名已存在，请修改用户名");
                    } else {
                        mDBManager.insert(username, password);
                        showMessage("注册成功");
                        Intent intent = new Intent(this, RegisterSuccessActivity.class);
                        intent.putExtra(KEY_USERNAME, username);
                        startActivity(intent);
                    }
                }
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
        if (username.isEmpty() || username.length() < USERNAME_MIN_LENGTH) {
            showMessage("用户名不能为空或长度小于" + USERNAME_MIN_LENGTH + "个字符");
            return false;
        } else if (password.isEmpty() || password.length() < PASSWORD_MIN_LENGTH){
            showMessage("密码不能为空或长度小于" + PASSWORD_MIN_LENGTH + "个字符");
            return false;
        } else {
            XLog.d("用户名：" + username + ", 密码：" + password);
            return true;
        }
    }


    /**
     * 功能暂未实现的提示
     */
    private void to_be_continued(String modulename) {
        showMessage(modulename + getString(R.string.to_be_continued));
    }

    /**
     * 通过Toast弹出提示消息
     * @param msg 需要显示的消息
     */
    private void showMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }


    class EditUsernameWahter implements TextWatcher {
        /**
         * 编辑框的文本发生改变前，原本显示的字符串是 charSequence,从位置start（首地址为0）开始的
         * count个字符被替换，替换后的长度为 after。
         * @param charSequence 原始的字符串
         * @param start 开始发生替换的其实下标，从0开始计算。
         * @param count 发生改变的字符的个数。
         * @param after 被替换的字符的个数。
         */
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            logger.d("发生变化前: " + charSequence + ", start = " + start + ", count = " + count + ", after = " + after);
        }


        /**
         * 编辑框的文本发生了变化，当前显示的文本charSequence中从start开始的before个字符
         * 是被替换了的，替换的长度为 count 个字符。
         * @param charSequence 界面当前显示的字符串
         * @param start 发生变化开始的下标
         * @param before 被替换的字符串的长度
         * @param count 用来替换的字符串长度
         */
        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            logger.d("文本正在变化: " + charSequence + ", start = " + start + ", before = " + before + ", count = " + count);
        }


        /**
         * 编辑框最终显示的字符。
         * 根据需要把输入的用户名保存到SharedPreferences中。
         * @param editable
         */
        @Override
        public void afterTextChanged(Editable editable) {
            String username = editable.toString();
            logger.d("文本编辑结束: " + editable.toString());
            if (chkRemUsername.isChecked() && username.length() >= USERNAME_MIN_LENGTH) {
                saveLastUsername(editable.toString());
            } else {
                saveLastUsername(USERNAME_INVALED);
            }
        }
    }
}

