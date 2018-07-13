package com.lintex9527.model;

/**
 * 为了验证自定义适配器MyCustomAdapter类而新建的类
 * 第一个是Book类
 * 第二个是App类
 */
public class App {
    private int mIcon;
    private String mName;

    public App() {
    }

    public App(int icon, String name) {
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
