package com.lintex9527.runoob;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.lintex9527.controller.MyCustomAdapter;
import com.lintex9527.model.App;
import com.lintex9527.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Adapter抽象、优化，参见：
 * http://www.runoob.com/w3cnote/android-tutorial-customer-baseadapter.html
 */
public class TestCustomAdapterActivity extends AppCompatActivity {

    private Context mContext;
    private ListView mListView_book;
    private ListView mListView_app;

    private MyCustomAdapter<App> myAdapterApp = null;
    private MyCustomAdapter<Book> myAdapterBook = null;
    private List<App> mDataApp = null;
    private List<Book> mDataBook = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_custom_adapter);

        init();
    }

    private void init(){
        mListView_book = (ListView) findViewById(R.id.listview_book);
        mListView_app = (ListView) findViewById(R.id.listview_app);

        // 数据初始化
        mDataApp = new ArrayList<App>();
        mDataApp.add(new App(R.drawable.iv_icon_baidu, "百度"));
        mDataApp.add(new App(R.drawable.iv_icon_douban, "豆瓣"));
        mDataApp.add(new App(R.drawable.iv_icon_zhifubao, "支付宝"));

        mDataBook = new ArrayList<Book>();
        mDataBook.add(new Book("《第一行代码Android》", "郭霖"));
        mDataBook.add(new Book("《Android群英传》", "徐宜生"));
        mDataBook.add(new Book("《Android开发艺术探索》", "任玉刚"));

        // Adapter 初始化
        myAdapterApp = new MyCustomAdapter<App>((ArrayList<App>) mDataApp, R.layout.item_app) {
            @Override
            public void bindView(ViewHolder holder, App obj) {
                holder.setImageResource(R.id.img_icon, obj.getIcon());
                holder.setText(R.id.txt_name, obj.getName());
            }
        };

        myAdapterBook = new MyCustomAdapter<Book>((ArrayList<Book>) mDataBook, R.layout.item_book) {
            @Override
            public void bindView(ViewHolder holder, Book obj) {
                holder.setText(R.id.txt_bookname, obj.getName());
                holder.setText(R.id.txt_bookauthor, obj.getAuthor());
            }
        };


        // 分别为ListView设置adapter
        mListView_book.setAdapter(myAdapterBook);
        mListView_app.setAdapter(myAdapterApp);
    }
}
