package com.lintex9527.android.broadcastreceiverapp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Context mContext = null;
    private Button btnDynamic = null;
    private Button btnStatic = null;
    private Button btnRestrict = null;


    private WeatherBcReceiver mWeatherBcReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        btnDynamic = (Button) findViewById(R.id.btn_dynamic);
        btnStatic = (Button) findViewById(R.id.btn_static);
        btnRestrict = (Button) findViewById(R.id.btn_restrict_sender);

        btnDynamic.setOnClickListener(this);
        btnStatic.setOnClickListener(this);
        btnRestrict.setOnClickListener(this);


        mWeatherBcReceiver = new WeatherBcReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.lintex9527.android.action.WEATHER_CHANGED");
        // 没有限制发送广播的APP具有的权限
        registerReceiver(mWeatherBcReceiver, intentFilter);
        // 限制发送者必须具备的权限
//        registerReceiver(mWeatherBcReceiver, intentFilter, "com.lintex9527.android.permission.WEATHER_STATE", null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dynamic:
                startActivity(new Intent(mContext, DynamicActivity.class));
                break;
            case R.id.btn_static:
                startActivity(new Intent(mContext, StaticActivity.class));
                break;
            case R.id.btn_restrict_sender:
                startActivity(new Intent(mContext, RestrictSenderActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mWeatherBcReceiver);
        super.onDestroy();
    }
}
