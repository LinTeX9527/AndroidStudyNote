package com.lintex9527.android.broadcastreceiverapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class StaticActivity extends AppCompatActivity {

    private static final String TAG = StaticActivity.class.getSimpleName();
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);
        mContext = StaticActivity.this;
    }

    /**
     * 点击返回按钮回到应用主界面
     * @param view
     */
    public void backHome(View view) {
        if (view.getId() == R.id.btn_backhome) {
            Log.d(TAG, "返回主页面");
            finish(); // 在启动新的Activity之前最好新销毁当前这个Activity，否则不会调用本身的onDestroy()方法
            startActivity(new Intent(mContext, MainActivity.class));
        }
    }


    @Override
    protected void onDestroy() {
        Log.d(TAG, "活动被销毁");
        super.onDestroy();
    }
}
