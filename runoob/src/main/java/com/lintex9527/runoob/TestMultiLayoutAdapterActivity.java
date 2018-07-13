package com.lintex9527.runoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.lintex9527.controller.MutiLayoutAdapter;
import com.lintex9527.model.App;
import com.lintex9527.model.Book;

import java.util.ArrayList;

import static com.lintex9527.controller.MutiLayoutAdapter.TYPE_APP;
import static com.lintex9527.controller.MutiLayoutAdapter.TYPE_BOOK;

/**
 * 子项多布局的Adapter，参见
 * http://www.runoob.com/w3cnote/android-tutorial-listview-item.html
 *
 * 多布局，最重要的还是 adapter 的实现
 */
public class TestMultiLayoutAdapterActivity extends AppCompatActivity {

    private ListView mListView_content;
    // 因为这里的 mData 是Object 支持所有的对象类型，所以可以在 MultiLayoutAdapter 中支持多种类
    private ArrayList<Object> mData = null;
    private MutiLayoutAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_multi_layout_adapter);

        mData = new ArrayList<Object>();
        for(int i = 0; i < 20; i++) {
            switch ((int) (Math.random() * 2)) {
                case TYPE_BOOK:
                    mData.add(new Book("《第一行代码》", "郭霖"));
                    break;
                case TYPE_APP:
                    mData.add(new App(R.drawable.iv_icon_baidu, "百度"));
                    break;
            }
        }

        mListView_content = (ListView) findViewById(R.id.listview_content);
        myAdapter = new MutiLayoutAdapter(TestMultiLayoutAdapterActivity.this, mData);
        mListView_content.setAdapter(myAdapter);
    }
}
