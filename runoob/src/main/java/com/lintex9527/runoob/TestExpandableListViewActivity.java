package com.lintex9527.runoob;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.lintex9527.controller.MyBaseExpandableListAdapter;
import com.lintex9527.model.Group;
import com.lintex9527.model.Item;

import java.util.ArrayList;

/**
 * ExpandableListView 可折叠列表
 * http://www.runoob.com/w3cnote/android-tutorial-expandablelistview.html
 *
 * 是ListVIew的子类，在ListView的基础上它把应用中的列表项分为几组，每组里又包含多个列表项。
 * 参见的应用场景是QQ联系人列表，用法和ListView非常相似，只不过ExpandableListView显示的列表项需要由
 * ExpandableAdapter提供。
 *
 * ExpandableAdapter 有3种实现方式：
 * 1、扩展 BaseExpandableListAdapter
 * 2、使用SimpleExpandableListAdapter 将两个List集合包装成ExpandableAdapter
 * 3、使用SimpleCursorTreeAdapter 将Cursor 中的数据包装成 SimpleCursorTreeAdapter
 */
public class TestExpandableListViewActivity extends AppCompatActivity {

    // 组对象
    private ArrayList<Group> gData = null;
    // 列表对象，注意其中的每一个元素其实又是一个列表对象
    private ArrayList<ArrayList<Item>> iData = null;
    // 这个是临时的一组列表对象，对应着某一个组中的所有子列表对象
    private ArrayList<Item> lData = null;
    private Context mContext;

    private ExpandableListView exlist_lol;
    private MyBaseExpandableListAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_expandable_list_view);

        initGUI();;
    }

    private void initGUI(){
        mContext = TestExpandableListViewActivity.this;
        exlist_lol = (ExpandableListView) findViewById(R.id.exlist_lol);

        // 数据准备
        gData = new ArrayList<Group>();
        iData = new ArrayList<ArrayList<Item>>();

        // 组对象初始化完毕
        gData.add(new Group("AD"));
        gData.add(new Group("AP"));
        gData.add(new Group("TANK"));


        // AD 组
        lData = new ArrayList<Item>();
        lData.add(new Item(R.mipmap.iv_lol_icon3, "剑圣"));
        lData.add(new Item(R.mipmap.iv_lol_icon4, "德莱文"));
        lData.add(new Item(R.mipmap.iv_lol_icon13, "男枪"));
        lData.add(new Item(R.mipmap.iv_lol_icon14, "韦鲁斯"));
        iData.add(lData);

        // AP 组
        lData = new ArrayList<Item>();
        lData.add(new Item(R.mipmap.iv_lol_icon1, "提莫"));
        lData.add(new Item(R.mipmap.iv_lol_icon7, "安妮"));
        lData.add(new Item(R.mipmap.iv_lol_icon8, "天使"));
        lData.add(new Item(R.mipmap.iv_lol_icon9, "泽拉斯"));
        lData.add(new Item(R.mipmap.iv_lol_icon11, "狐狸"));
        iData.add(lData);

        // TANK 组
        lData = new ArrayList<>();
        lData.add(new Item(R.mipmap.iv_lol_icon2, "诺手"));
        lData.add(new Item(R.mipmap.iv_lol_icon5, "德邦"));
        lData.add(new Item(R.mipmap.iv_lol_icon6, "奥拉夫"));
        lData.add(new Item(R.mipmap.iv_lol_icon10, "龙女"));
        lData.add(new Item(R.mipmap.iv_lol_icon12, "狗熊"));
        iData.add(lData);

        myAdapter = new MyBaseExpandableListAdapter(gData, iData, mContext);
        exlist_lol.setAdapter(myAdapter);

        // 为列表设置点击事件
        exlist_lol.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mContext, "你点击了" + iData.get(groupPosition).get(childPosition).getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
