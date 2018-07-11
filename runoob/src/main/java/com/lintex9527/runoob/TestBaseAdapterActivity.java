package com.lintex9527.runoob;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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
        mListViewAnimal.setAdapter(mAdapter);
    }
}
