package com.lintex9527.controller;

import android.content.Context;
import android.util.Log;
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
 *
 * Adapter 中修改数据，然后必须调用 notifyDataSetChanged() 来通知 ListView 更新视图。
 * 例如这里的方法 addPerson(), deletePerson(), clearPerson()
 */
public class PersonAdapter extends BaseAdapter {

    private static final String TAG = PersonAdapter.class.getName();

    // 表示数据集合
    private LinkedList<Person> mData;
    private Context mContext;

    @Override
    public int getCount() {
        Log.d(TAG, "getCount() = " + mData.size());
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        Log.d(TAG, "getItem(), position = " + position);
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.d(TAG, "getItemId(), item Id = " + position);
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

    /**
     * TODO: 通过序号删除一个Person
     * @param
     */
    public void deletePerson(int position){
        if (mData != null){
            mData.remove(position);
        }
        notifyDataSetChanged();
    }


    /**
     * 清空所有数据
     */
    public void clearPerson() {
        Log.d(TAG, "clearPerson() in");
        if (mData != null) {
            mData.clear();
            notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        ImageView imgAvatar;
        TextView tvName;
        TextView tvWisdom;
        int position;
    }
}
