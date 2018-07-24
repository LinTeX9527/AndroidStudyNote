package com.lintex9527.model;

/**
 * 为了演示 ExpandableListView 用法，描述其中组对象而创建的类，只有一个成员表示组名称
 */
public class Group {
    private String mName;

    public Group(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
