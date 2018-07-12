package com.lintex9527.model;

/**
 * Person 属性包括：
 * mAvatar 头像，或者图片
 * mName   名字
 * mWisdom 名言
 *
 * 这里的成员只用表示对象自身的属性。
 */
public class Person {
    // 头像
    private int mAvatar;
    // 名字
    private String mName;
    // 名言
    private String mWisdom;

    public Person(int avatar, String name, String wisdom) {
        mAvatar = avatar;
        mName = name;
        mWisdom = wisdom;
    }

    public int getAvatar() {
        return mAvatar;
    }

    public void setAvatar(int avatar) {
        mAvatar = avatar;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getWisdom() {
        return mWisdom;
    }

    public void setWisdom(String wisdom) {
        mWisdom = wisdom;
    }
}
