package com.imooc.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lintex9527.study.R;


/**
 * Created by LinTeX9527 on 2017/9/27.
 */

/**
 * 自定义的Fragment 的初始化过程主要在 onCreateView()
 * 在这里我们初始化资源，绑定视图
 */
public class ShopRankFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shoprank, null);
    }
}
