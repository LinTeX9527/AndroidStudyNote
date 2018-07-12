package com.lintex9527.runoob;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        listViewPersons = (ListView) findViewById(R.id.listviewPersons);
        listViewPersons.setAdapter(mPersonAdapter);


        // 给按钮添加事件监听器
        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < MAX_NUM_OF_PERSON) {
                    count++;

                    if (count == 1) {
                        mPersonAdapter.addPerson(new Person(R.drawable.caocao, "曹操", "对酒当歌，人生几何"));
                    }
                    if (count == 2) {
                        mPersonAdapter.addPerson(new Person(R.drawable.wangyangming, "王阳明", "致良知"));
                    }
                    if (count == 3) {
                        mPersonAdapter.addPerson(new Person(R.drawable.jinyong, "金庸", "飞雪连天射白鹿，笑书神侠倚碧鸳"));
                    }
                } else {
                    Toast.makeText(getBaseContext(), "超过上限，不太会再添加", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
