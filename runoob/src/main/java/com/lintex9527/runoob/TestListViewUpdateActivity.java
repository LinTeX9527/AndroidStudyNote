package com.lintex9527.runoob;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lintex9527.controller.PersonAdapter;
import com.lintex9527.model.Person;

import java.util.LinkedList;

/**
 * 应用页面，这里显示数据，并通过Controller获得数据。
 */
public class TestListViewUpdateActivity extends AppCompatActivity {

    private static final String TAG = TestListViewUpdateActivity.class.getName();

    private static final int MAX_NUM_OF_PERSON = 3;
    private int count = 0;
    // View
    private Button btnAddPerson;
    private Button btnDelPerson;
    private Button btnClearPerson;
    private ListView listViewPersons;

    // Model
    private PersonAdapter mPersonAdapter = null;
    private LinkedList<Person> mData = null;
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list_view_update);

        init();
    }


    private void init() {


        mContext = TestListViewUpdateActivity.this;
        mData = new LinkedList<Person>();
        mPersonAdapter = new PersonAdapter(mData, mContext);


        // View
        btnAddPerson = (Button) findViewById(R.id.btnAddPerson);
        btnDelPerson = (Button) findViewById(R.id.btnDelPerson);
        btnClearPerson = (Button) findViewById(R.id.btnClearPerson);
        listViewPersons = (ListView) findViewById(R.id.listviewPersons);
        listViewPersons.setAdapter(mPersonAdapter);
        listViewPersons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onItemClick(), position = " + position + ", id = " + id);
                Log.d(TAG, stringBuilder.toString());
                Toast.makeText(getBaseContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        listViewPersons.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onItemLongClick(), position = " + position + ", id = " + id);
                Log.d(TAG, stringBuilder.toString());
                Toast.makeText(getBaseContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        listViewPersons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onItemSelected(), position = " + position + ", id = " + id);
                Log.d(TAG, stringBuilder.toString());
                Toast.makeText(getBaseContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onNothingSelected()");
                Log.d(TAG, stringBuilder.toString());
                Toast.makeText(getBaseContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();

            }
        });


        // 给按钮添加事件监听器
        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < MAX_NUM_OF_PERSON) {


                    if (count == 0) {
                        mPersonAdapter.addPerson(new Person(R.drawable.caocao, "曹操", "对酒当歌，人生几何"));
                    }
                    if (count == 1) {
                        mPersonAdapter.addPerson(new Person(R.drawable.wangyangming, "王阳明", "致良知"));
                    }
                    if (count == 2) {
                        mPersonAdapter.addPerson(new Person(R.drawable.jinyong, "金庸", "飞雪连天射白鹿，笑书神侠倚碧鸳"));
                    }
                    count++;
                } else {
                    Toast.makeText(getBaseContext(), "超过上限，不太会再添加", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( (count > 0) && (count <= MAX_NUM_OF_PERSON) ) {
                    mPersonAdapter.deletePerson(0);
                    count--;
                } else {
                    Toast.makeText(getBaseContext(), "无效的id", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClearPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPersonAdapter.clearPerson();
                count = 0;
            }
        });
    }
}
