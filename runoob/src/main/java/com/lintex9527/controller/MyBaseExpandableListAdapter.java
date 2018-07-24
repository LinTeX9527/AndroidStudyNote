package com.lintex9527.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lintex9527.model.Group;
import com.lintex9527.model.Item;
import com.lintex9527.runoob.R;

import java.util.ArrayList;

public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {

    // 表示组对象数据集合
    private ArrayList<Group> gData;
    // 表示列表子项目对象的数据集合
    private ArrayList<ArrayList<Item>> iData;
    private Context mContext;

    public MyBaseExpandableListAdapter(ArrayList<Group> gData, ArrayList<ArrayList<Item>> iData, Context context) {
        this.gData = gData;
        this.iData = iData;
        mContext = context;
    }


    /**
     * 查询组对象个数
     * @return 组对象个数
     */
    @Override
    public int getGroupCount() {
        return gData.size();
    }


    /**
     * 查询某个组中子列表对象的个数
     * @param groupPosition 组对象下标
     * @return 组对象中的子列表对象总个数
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return iData.get(groupPosition).size();
    }


    /**
     * 根据下标返回对应的组对象
     * @param groupPosition 组对象的下标
     * @return 对应的组对象
     */
    @Override
    public Group getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }


    /**
     * 根据下标返回某个组中的某个子列表项目
     * @param groupPosition 组下标
     * @param childPosition 子列表项目的下标
     * @return 子列表项目对象
     */
    @Override
    public Item getChild(int groupPosition, int childPosition) {
        return iData.get(groupPosition).get(childPosition);
    }


    /**
     * 返回组对象的下标
     * @param groupPosition 组对象下标
     * @return 组对象下标
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    /**
     * 返回子列表项目的下标
     * @param groupPosition 组对象下标
     * @param childPosition 子列表项目的下标
     * @return 子列表项目的下标
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    /**
     * TODO: hasStableIds() 有什么用？
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }


    /**
     * 取得用于显示给定分组的视图，这个方法仅返回分组的视图对象
     * @param groupPosition 组对象下标
     * @param isExpanded 是否折叠
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolderGroup groupHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_exlist_group, parent, false);
            groupHolder = new ViewHolderGroup();
            groupHolder.tv_group_name = (TextView) convertView.findViewById(R.id.tv_group_name);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }

        groupHolder.tv_group_name.setText(gData.get(groupPosition).getName());
        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ViewHolderItem itemHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_exlist_item, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
            itemHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
        itemHolder.img_icon.setImageResource(iData.get(groupPosition).get(childPosition).getId());
        itemHolder.tv_name.setText(iData.get(groupPosition).get(childPosition).getName());
        return convertView;
    }

    /**
     * 设置子列表是否可以选中
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class ViewHolderGroup {
        private TextView tv_group_name;
    }

    private static class ViewHolderItem {
        private ImageView img_icon;
        private TextView tv_name;
    }
}
