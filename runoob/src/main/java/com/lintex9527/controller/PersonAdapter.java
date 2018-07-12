package com.lintex9527.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lintex9527.model.Person;
import com.lintex9527.runoob.R;

import java.util.LinkedList;

/**
 * 适配器处在Controller这一层，关联View 和 Model，所以成员变量包括两个部分。
 */
public class PersonAdapter extends BaseAdapter {

    // 表示数据集合
    private LinkedList<Person> mData;
    private Context mContext;

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
     * View 和 Model 关联的部分，需要加载视图，并且把一个对象显示到这个视图上。
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

            holder = new ViewHolder();
            holder.imgAvatar = (ImageView) convertView.findViewById(R.id.image_avatar);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvWisdom = (TextView) convertView.findViewById(R.id.tv_saying);
            holder.position = position;
            convertView.setTag(holder); // 将 holder 存储到 convertView 中
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imgAvatar.setImageResource(mData.get(position).getAvatar());
        holder.tvName.setText(mData.get(position).getName());
        holder.tvWisdom.setText(mData.get(position).getWisdom());

        return convertView;
    }



    /**
     * 构造函数，关联数据 -- Model
     * @param data
     * @param context
     */
    public PersonAdapter(LinkedList<Person> data, Context context) {
        mData = data;
        mContext = context;
    }

    /**
     * 动态添加数据。
     * 注意最后的 notifyDataSetChanged() 让视图更新显示。
     * @param person
     */
    public void addPerson(Person person) {
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.add(person);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        ImageView imgAvatar;
        TextView tvName;
        TextView tvWisdom;
        int position;
    }
}
