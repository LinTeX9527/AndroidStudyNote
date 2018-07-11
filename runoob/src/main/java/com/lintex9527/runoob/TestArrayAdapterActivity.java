package com.lintex9527.runoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Adapter 是MVC 模型中必不可少的一环，处在Controller这一部分，装载数据Model并且显示到View视图中。
 * http://www.runoob.com/w3cnote/android-tutorial-adapter.html
 *
 * 这个案例展示最基本的ArrayAdapter用法。
 */
public class TestArrayAdapterActivity extends AppCompatActivity {


    private ListView mListView;
    private ArrayList<String> myarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_array_adapter);

        initData();
        initView();
    }

    /**
     * 构建数据Model
     */
    private void initData() {
        // 第一步：构造数据，获取数据（Model）
        myarray = new ArrayList<String>();
        myarray.add("Apple");
        myarray.add("Banana");
        myarray.add("Cherry");
        myarray.add("Date");
        myarray.add("Hazel");
    }

    private void initView(){

        // 第二步，用合适的adapter来装载数据（Controller）
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, myarray);

        // 第三步，用具体的ListView来显示adapter （View）
        mListView = findViewById(R.id.listview01);
        mListView.setAdapter(adapter);
    }
}
