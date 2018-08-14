package com.lintex9527.android.sqlitedemo01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by lintex9527@yeah.net on 2018/8/14 16:13
 */
public class ModifyCountryActivity extends Activity implements View.OnClickListener {

    private static final String TAG = ModifyCountryActivity.class.getSimpleName();

    private EditText titleText;
    private Button updateBtn, deleteBtn;
    private EditText descText;

    private long _id;

    private DBManager mDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.activity_modify_record);
        Log.d(TAG, "onCreate() 新建活动");

        mDBManager = new DBManager(this);
        mDBManager.open();

        titleText = (EditText) findViewById(R.id.subject_edittext);
        descText = (EditText) findViewById(R.id.description_edittext);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");

        _id = Long.parseLong(id);
        titleText.setText(name);
        descText.setText(desc);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() 销毁活动");
        mDBManager.close();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String title = titleText.getText().toString();
                String desc = descText.getText().toString();

                mDBManager.update(_id, title, desc);
                this.returnHome();
                return;
            case R.id.btn_delete:
                mDBManager.delete(_id);
                this.returnHome();
                return;
        }
    }


    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), CountryListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
