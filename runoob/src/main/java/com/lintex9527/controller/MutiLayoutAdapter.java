package com.lintex9527.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lintex9527.model.App;
import com.lintex9527.model.Book;
import com.lintex9527.runoob.R;

import java.util.ArrayList;

/**
 * ListView Item 多布局的实现
 * 需要adapter 支持多个布局，重点在于 adapter 中的两个方法：
 * getViewTypeCount()  返回总共多少个类别
 * getViewType() 对应的View 是哪个类别
 * http://www.runoob.com/w3cnote/android-tutorial-listview-item.html
 *
 */
public class MutiLayoutAdapter extends BaseAdapter {

    // 定义两个类别标志
    public static final int TYPE_BOOK = 0;
    public static final int TYPE_APP = 1;

    private Context mContext;
    private ArrayList<Object> mData = null;


    /**
     * 构造函数
     * @param context 上下文
     * @param data 适配器需要管理的数据集合
     */
    public MutiLayoutAdapter(Context context, ArrayList<Object> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * 多布局的核心，通过这个判断类别
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) instanceof App) {
            return TYPE_APP;
        } else if (mData.get(position) instanceof Book) {
            return TYPE_BOOK;
        } else {
            return super.getItemViewType(position);
        }
    }


    /**
     * 返回多布局的类别个数
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolderApp holderApp = null;
        ViewHolderBook holderBook = null;
        if (convertView == null) {
            switch (type) {
                case TYPE_APP:
                    holderApp = new ViewHolderApp();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_app, parent, false);
                    holderApp.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
                    holderApp.txt_name = (TextView) convertView.findViewById(R.id.txt_name);
                    convertView.setTag(R.id.TAG_APP, holderApp);
                    break;
                case TYPE_BOOK:
                    holderBook = new ViewHolderBook();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_book, parent, false);
                    holderBook.txt_bookname = (TextView) convertView.findViewById(R.id.txt_bookname);
                    holderBook.txt_bookauthor = (TextView) convertView.findViewById(R.id.txt_bookauthor);
                    convertView.setTag(R.id.TAG_BOOK, holderBook);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_APP:
                    holderApp = (ViewHolderApp) convertView.getTag(R.id.TAG_APP);
                    break;
                case TYPE_BOOK:
                    holderBook = (ViewHolderBook) convertView.getTag(R.id.TAG_BOOK);
                    break;
            }
        }

        Object obj = mData.get(position);
        // 设置控件的值
        switch (type) {
            case TYPE_APP:
                App app = (App) obj;
                if (app != null) {
                    holderApp.img_icon.setImageResource(app.getIcon());
                    holderApp.txt_name.setText(app.getName());
                }
                break;
            case TYPE_BOOK:
                Book book = (Book) obj;
                if (book != null) {
                    holderBook.txt_bookname.setText(book.getName());
                    holderBook.txt_bookauthor.setText(book.getAuthor());
                }
                break;
        }

        return convertView;
    }


    /**
     * 这里必须要使用两种不同类型的ViewHolder，每一种对应一种类别
     */
    private static class ViewHolderApp {
        ImageView img_icon;
        TextView txt_name;
    }

    private static class ViewHolderBook {
        TextView txt_bookauthor;
        TextView txt_bookname;
    }
}
