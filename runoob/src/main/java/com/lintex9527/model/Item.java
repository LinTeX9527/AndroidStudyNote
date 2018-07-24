package com.lintex9527.model;

/**
 * 为了演示 ExpandableListView 用法，描述其中列表成员对象，包含图片资源ID和名称
 */
public class Item {
    private int mId;
    private String mName;

    public Item(int id, String name) {
        mId = id;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
