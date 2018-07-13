package com.lintex9527.model;

/**
 * 为了验证自定义适配器MyCustomAdapter类而新建的类
 * 第一个是Book类
 * 第二个是App类
 */
public class Book {
    private String mName;
    private String mAuthor;

    public Book() {
    }

    public Book(String name, String author) {
        mName = name;
        mAuthor = author;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }
}
