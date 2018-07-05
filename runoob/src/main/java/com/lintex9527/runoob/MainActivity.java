package com.lintex9527.runoob;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 主页通过列表展示所有的案例项目。
 *
 * 和主页相关的几个类分别是：
 * ActivityCollector
 * BaseActivity
 *
 */

public class MainActivity extends AppCompatActivity {

    public final static String ITEM_DESCRIPTION = "DESCRIPTION";
    public final static String ITEM_ACTION = "ACTION";

    private List<HashMap<String, Object>> data = null;
    private static int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initData();

        String[] from = new String[]{ITEM_DESCRIPTION};
        int[] to = new int[]{R.id.tvDesc};

        ListView lvAllItem = (ListView) findViewById(R.id.lv_alltest);
        lvAllItem.setAdapter(new SimpleAdapter(this, data, R.layout.testpage_list_item, from, to));
        // 给ListView 添加分割线
        lvAllItem.setDivider(new ColorDrawable(Color.GRAY));
        lvAllItem.setDividerHeight(2);

        lvAllItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                startTestItem(position);
            }
        });
    }

    protected void initData(){

        data = new ArrayList<>();

        index = 0;

        addTestItem(R.string.textview_attr, "com.lintex9527.runoob.TestTextViewActivity");

    }

    protected void addTestItem(int idDescription, String action){
        HashMap<String, Object> mapx = new HashMap<>();
        mapx.put(ITEM_DESCRIPTION, "" + (++index) +  ": " +getResources().getString(idDescription));
        mapx.put(ITEM_ACTION, action);
        data.add(mapx);
    }


    protected void startTestItem(int itemindex){
        if (itemindex >= 0 && itemindex < data.size()){
            startActivity(new Intent(data.get(itemindex).get(ITEM_ACTION).toString()));
        }
    }
}
