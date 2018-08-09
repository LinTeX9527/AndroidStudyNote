package com.lintex9527.android.broadcastreceiverapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Context mContext = null;
    private Button btnDynamic = null;
    private Button btnStatic = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        btnDynamic = (Button) findViewById(R.id.btn_dynamic);
        btnStatic = (Button) findViewById(R.id.btn_static);

        btnDynamic.setOnClickListener(this);
        btnStatic.setOnClickListener(this);
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
        }
    }
}
