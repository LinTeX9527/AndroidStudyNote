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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.animal_list_item, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.imgIcon = (ImageView) convertView.findViewById(R.id.img_animal);
        holder.tvName = (TextView) convertView.findViewById(R.id.tv_animal_name);
        holder.tvSpeak = (TextView) convertView.findViewById(R.id.tv_animal_speak);
        holder.position = position;

        convertView.setTag(holder);

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
