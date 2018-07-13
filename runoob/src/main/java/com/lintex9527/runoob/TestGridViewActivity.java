package com.lintex9527.runoob;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.lintex9527.controller.MyCustomAdapter;
import com.lintex9527.model.Icon;

import java.util.ArrayList;

/**
 * GridView 的用法和 ListView 的用法大致相同:
 * http://www.runoob.com/w3cnote/android-tutorial-gridview.html
 * 
 */
public class TestGridViewActivity extends AppCompatActivity {

    private Context mContext;
    private GridView grid_photo;
    private MyCustomAdapter<Icon> myAdapter = null;
    private ArrayList<Icon> mData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_grid_view);

        mContext = TestGridViewActivity.this;
        grid_photo = (GridView) findViewById(R.id.grid_photo);

        mData = new ArrayList<Icon>();
        mData.add(new Icon(R.drawable.iv_icon_baidu, "百度"));
        mData.add(new Icon(R.drawable.iv_icon_zhifubao, "支付宝"));
        mData.add(new Icon(R.drawable.iv_icon_douban, "豆瓣"));
        mData.add(new Icon(R.drawable.dog, "狗"));
        mData.add(new Icon(R.drawable.duck, "鸭子"));
        mData.add(new Icon(R.drawable.cow, "牛"));

        myAdapter = new MyCustomAdapter<Icon>(mData, R.layout.item_grid_icon) {
            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                holder.setImageResource(R.id.img_icon, obj.getIconId());
                holder.setText(R.id.txt_icon, obj.getIconName());
            }
        };

        grid_photo.setAdapter(myAdapter);

        grid_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "你点击了 " + ((TextView)view.findViewById(R.id.txt_icon)).getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
