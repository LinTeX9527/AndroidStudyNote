package com.lintex9527.runoob;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lintex9527.controller.AnimalAdapter;
import com.lintex9527.model.Animal;

import java.util.LinkedList;
import java.util.List;

/**
 * BaseAdapter 教程
 * http://www.runoob.com/w3cnote/android-tutorial-listview.html
 *
 * 总结：
 * 1、为事物建一个类，例如这里的Animal类；
 * 2、Animal 类对象如何显示的，即ListView中每一个子项的显示方式，需要定义一个 animal_list_item.xml 视图；
 * 3、一个Animal 对象对应一个 animal_list_item 视图，映射关系由 AnimalAdapter 类完成；
 * 4、为ListView 设置 AnimalAdapter 类实例。
 */
public class TestBaseAdapterActivity extends AppCompatActivity {

    private static final String TAG = TestBaseAdapterActivity.class.getName();

    private List<Animal> mData = null;
    private Context mContext;
    private AnimalAdapter mAdapter = null;
    private ListView mListViewAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_base_adapter);

        initGUI();
    }


    private void initGUI() {
        mContext = TestBaseAdapterActivity.this;
        mListViewAnimal = (ListView) findViewById(R.id.listview03);
        mData = new LinkedList<Animal>();
        mData.add(new Animal("狗", "二郎神犬", R.drawable.dog));
        mData.add(new Animal("牛", "牛魔王", R.drawable.cow));
        mData.add(new Animal("鸭", "唐老鸭", R.drawable.duck));
        mData.add(new Animal("鱼", "大鱼海棠", R.drawable.fish));
        mData.add(new Animal("马", "白龙马", R.drawable.horse));

        mAdapter = new AnimalAdapter((LinkedList<Animal>) mData, mContext);

        // 动态加载顶部View
        final LayoutInflater inflater = LayoutInflater.from(this);
        View headerView = inflater.inflate(R.layout.animal_list_header, null, false);
        // 动态加载顶部View 必须在 setAdapter 之前，否则出现问题。
        mListViewAnimal.addHeaderView(headerView);
        mListViewAnimal.setAdapter(mAdapter);

        mListViewAnimal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * 添加了表头，这里的点击事件在表头上也能响应，所以这里的view, position, id 有可能表示表头信息
             * 总结如下：
             *         position      id
             * 表头       0           -1
             * item1      1           0
             * item2      2           1
             * item3      3           2
             *
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(TAG, "[AdapterView id] = " + Long.toHexString(parent.getId()));
                Log.d(TAG, "[AdapterView class] = " + parent.getClass().getName());
                Log.d(TAG, "view id = " + Long.toHexString(view.getId()) + ",position = " + position + ", id = " + id);
                Log.d(TAG, view.getClass().toString());

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("点击了第" + position + "项,id=" + id);
                Toast.makeText(mContext, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
