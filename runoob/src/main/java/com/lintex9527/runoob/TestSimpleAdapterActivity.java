package com.lintex9527.runoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSimpleAdapterActivity extends AppCompatActivity {

    private static final String KEY_AVATAR = "avatar";
    private static final String KEY_NAME = "name";
    private static final String KEY_SAYS = "says";

    private String[] names = new String[] {"曹操", "王阳明", "金庸"};
    private String[] says = new String[] {"对酒当歌，人生几何", "山近月远觉月小，便道此山大于月", "飞雪连天射白鹿，笑书神侠倚碧鸳"};
    private int[] imageIDs = new int[]{R.drawable.caocao, R.drawable.wangyangming, R.drawable.jinyong};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_simple_adapter);

        initGUI();
    }

    private void initGUI() {
        // 第一步：获得数据 -- Model
        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put(KEY_AVATAR, imageIDs[i]);
            item.put(KEY_NAME, names[i]);
            item.put(KEY_SAYS, says[i]);

            listitem.add(item);
        }

        // 第二步：为数据设定显示方式 -- Controller
        // 这里的from, to 定义数据中的某个关键部分如何显示到视图中对应点
        String[] from = new String[] {KEY_AVATAR, KEY_NAME, KEY_SAYS};
        int[] to = new int[] {R.id.image_avatar, R.id.tv_name, R.id.tv_saying};

        // 创建一个 SimpleAdapter
        SimpleAdapter myAdapter = new SimpleAdapter(getBaseContext(), listitem, R.layout.list_item, from, to);

        // 第三步：视图中显示数据 -- View
        ListView listView = (ListView) findViewById(R.id.listview02);
        listView.setAdapter(myAdapter);

        // 为ListView 添加子项的单击响应事件监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StringBuilder stringBuilder = new StringBuilder();
                // 找到子项中的某个布局对象
                TextView tv = view.findViewById(R.id.tv_saying);
                stringBuilder.append(tv.getText().toString());
                Toast.makeText(getBaseContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
