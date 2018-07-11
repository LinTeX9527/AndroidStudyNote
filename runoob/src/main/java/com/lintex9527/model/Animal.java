package com.lintex9527.model;

/**
 * 为了演示BaseAdapter的用法，设计的一个类
 */
public class Animal {
    private String mName;
    private String mSpeak;
    private int mIcon;

    public Animal() {
    }

    public Animal(String name, String speak, int icon) {
        mName = name;
        mSpeak = speak;
        mIcon = icon;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSpeak() {
        return mSpeak;
    }

    public void setSpeak(String speak) {
        mSpeak = speak;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        mIcon = icon;
    }
}
