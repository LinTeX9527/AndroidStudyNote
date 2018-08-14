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
 * Created by lintex9527@yeah.net on 2018/8/14 16:05
 */
public class AddCountryActivity extends Activity implements View.OnClickListener {

    private static final String TAG = AddCountryActivity.class.getSimpleName();

    private Button addTodoBtn;
    private EditText subjectEditText;
    private EditText descEditText;

    private DBManager mDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");
        setContentView(R.layout.activity_add_record);
        Log.d(TAG, "onCreate() 新建活动");

        subjectEditText = (EditText) findViewById(R.id.subject_edittext);
        descEditText = (EditText) findViewById(R.id.description_edittext);

        addTodoBtn = (Button) findViewById(R.id.add_record);

        mDBManager = new DBManager(this);
        mDBManager.open();
        addTodoBtn.setOnClickListener(this);
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
            case R.id.add_record:
                final String name = subjectEditText.getText().toString();
                final String desc = descEditText.getText().toString();
                mDBManager.insert(name, desc);

                Intent main = new Intent(AddCountryActivity.this, CountryListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }
    }
}
