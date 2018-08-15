package com.lintex9527.android.gamecenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
