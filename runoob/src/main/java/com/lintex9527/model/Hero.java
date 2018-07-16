package com.lintex9527.model;

/**
 * 为了测试单选列表控件Spinner而新建的类
 */
public class Hero {
    private int mIcon;
    private String mName;

    public Hero() {
    }

    public Hero(int icon, String name) {
        mIcon = icon;
        mName = name;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        mIcon = icon;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
