package com.lintex9527.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lintex9527.model.Animal;
import com.lintex9527.runoob.R;

import java.util.LinkedList;

/**
 * 自定义的BaseAdapter
 * http://www.runoob.com/w3cnote/android-tutorial-baseadapter.html
 *
 * 最重要的两个方法是 getCount() 和 getView()
 * 界面上有多少列就会调用多少次 getView()。
 * 每一次使用 inflate 一个View 就会对 listview item 的视图XML重新解析，这样很浪费资源。
 *
 * 需要优化。
 * 优化 getView() 方法，重点在于 ViewHolder 的使用。
 */
public class AnimalAdapter extends BaseAdapter {

    private LinkedList<Animal> mData;
    private Context mContext;

    public AnimalAdapter(LinkedList<Animal> data, Context context) {
        mData = data;
        mContext = context;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * 重点在于这个 getView() 消耗资源，每一次给ListView添加一个项目，都会调用这个方法。
     * @param position
     * @param convertView 可用的View的缓存对象
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.animal_list_item, parent, false);

            holder = new ViewHolder();
            holder.imgIcon = (ImageView) convertView.findViewById(R.id.img_animal);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_animal_name);
            holder.tvSpeak = (TextView) convertView.findViewById(R.id.tv_animal_speak);
            holder.position = position;
            convertView.setTag(holder); // 将holder存储到convertView中
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imgIcon.setImageResource(mData.get(position).getIcon());
        holder.tvName.setText(mData.get(position).getName());
        holder.tvSpeak.setText(mData.get(position).getSpeak());
        return convertView;
    }


    /**
     * TODO: 为什么要使用ViewHolder
     * https://developer.android.com/training/improving-layouts/smooth-scrolling#ViewHolder
     */
    static class ViewHolder {
        ImageView imgIcon;
        TextView tvName;
        TextView tvSpeak;
        int position;
    }
}
