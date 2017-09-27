package com.lintex9527.study;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;

import com.imooc.fragments.GiftFragment;
import com.imooc.fragments.OrderFragment;
import com.imooc.fragments.ShareFragment;
import com.imooc.fragments.ShopRankFragment;

/**
 * FragmentTransaction 执行 commit() 过程，如果某一个事务出错了，那么所有的事务都不能产生执行的效果，如果
 * 都不出错，那么所有的事务都可能执行。
 *
 * 教程地址：
 * http://www.imooc.com/learn/894
 * Created by LinTeX9527 on 2017/9/27.
 */

public class FragmentTestActivity extends Activity implements View.OnClickListener{

    private String TAG = FragmentTestActivity.class.getSimpleName();

    private android.app.FragmentManager mFragmentManager;
    private android.app.FragmentTransaction mTransaction;

    private RadioButton rb_shoprank, rb_share, rb_gift, rb_order;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fragment_test);


        mFragmentManager = getFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();

        // 初始化的时候添加某一个 fragment 到首页
        // 第一个参数是 containerViewid，
        // 第二个参数是 fragment 实例
        // mTransaction.add(R.id.content_layout, new ShopRankFragment());
        mTransaction.add(R.id.content_layout, new ShopRankFragment());
        mTransaction.commit();

        initView();

    }

    /**
     * 初始化视图
     */
    public void initView(){
        rb_shoprank = (RadioButton) findViewById(R.id.rb_shoprank);
        rb_share = (RadioButton) findViewById(R.id.rb_share);
        rb_gift = (RadioButton) findViewById(R.id.rb_gift);
        rb_order = (RadioButton) findViewById(R.id.rb_order);



        rb_shoprank.setOnClickListener(this);
        rb_order.setOnClickListener(this);
        rb_gift.setOnClickListener(this);
        rb_share.setOnClickListener(this);
    }

    /**
     * 点击按钮切换 Fragment
     * @param v
     */
    @Override
    public void onClick(View v) {
        mTransaction = mFragmentManager.beginTransaction();
        Log.d(TAG, "开始事务");
        switch (v.getId()){
            case R.id.rb_shoprank:
                mTransaction.replace(R.id.content_layout, new ShopRankFragment());
                break;
            case R.id.rb_share:
                mTransaction.replace(R.id.content_layout, new ShareFragment());
                break;
            case R.id.rb_gift:
                mTransaction.replace(R.id.content_layout, new GiftFragment());
                break;
            case R.id.rb_order:
                mTransaction.replace(R.id.content_layout, new OrderFragment());
                break;
        }
        mTransaction.commit();
        Log.d(TAG, "提交完毕");
    }
}
