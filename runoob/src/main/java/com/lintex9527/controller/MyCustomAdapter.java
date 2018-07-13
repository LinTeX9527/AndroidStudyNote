package com.lintex9527.controller;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 参见： http://www.runoob.com/w3cnote/android-tutorial-customer-baseadapter.html
 * 
 * 支持数据类型为泛型的通用适配器
 * @param <T> 泛型数据类型
 */
public abstract class MyCustomAdapter<T> extends BaseAdapter {

    // 数据集合，类型为泛型
    private ArrayList<T> mData;

    // 布局资源ID
    private int mLayoutRes;


    public MyCustomAdapter() {
    }

    public MyCustomAdapter(ArrayList<T> mData, int mLayoutRes) {
        this.mData = mData;
        this.mLayoutRes = mLayoutRes;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * TODO:  getView() 很难理解
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.bind(parent.getContext(), convertView, parent, mLayoutRes, position);
        bindView(holder, getItem(position));
        return holder.getItemView();
    }

    /**
     * 定义一个抽象方法，完成 ViewHolder 与 Data 数据集的绑定
     * 需要在新建的BaseAdapter 的时候实现这个方法就好。
     * 因为这个方法为 abstract ，所以 MyCustomAdapter 也必须定义为 abstract 类。
     * @param holder
     * @param obj
     */
    public abstract void bindView(ViewHolder holder, T obj);

    /**
     * 适配器管理的数据集合中添加一个元素
     * @param data 一个泛型数据对象
     */
    public void add(T data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(data);
        // 注意使用 notifyDataSetChanged() 方法来通知GridView 或者 ListView 来更新视图
        notifyDataSetChanged();
    }


    /**
     * 往适配器管理的数据集合中特定位置添加一个元素
     * @param position 集合中的位置
     * @param data 一个泛型数据对象
     */
    public void add(int position, T data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(position, data);
        notifyDataSetChanged();
    }


    /**
     * 适配器管理的数据集合中删除某个元素
     * @param data 将要删除的泛型数据对象
     */
    public void remove(T data) {
        if (mData != null) {
            mData.remove(data);
        }
        notifyDataSetChanged();
    }


    /**
     * 适配器管理的数据集合中删除某个元素
     * @param position 将要删除的泛型数据对象的位置，即下标
     */
    public void remove(int position) {
        if (mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }


    /**
     * 清空适配器管理的数据集合
     */
    public void clear() {
        if (mData != null) {
            mData.clear();
        }
        notifyDataSetChanged();
    }


    /**
     * ViewHolder 类是干什么的？
     * 答：findViewById()，设置控件状态。
     * 优化后的实现，把Adapter中的getView()方法大部分的逻辑写到 ViewHolder 类中，这个ViewHolder 要做的事：
     * 1、定义一个查找控件的方法，思路是通过暴露公共的方法，调用方法时传递过来控件ID，以及设置的内容，
     * 比如 TextView 设置文本：
     * public ViewHolder setText(int id, CharSequence text) { 文本设置}
     *
     * 2、将 convertView 复用部分搬到这里，那就需要传递一个 context 对象
     *
     * 3、写一堆设置方法，比如设置文字大小、颜色，图片、背景等等。
     */
    public static class ViewHolder {

        // 存放 ListView 的 item 对应的 View
        private SparseArray<View> mViews;

        // 存放 convertView
        private View item;

        // 游标
        private int position;

        // Context
        private Context context;

        /**
         * 构造方法，完成相关初始化
         * @param context 上下文
         * @param parent 父布局
         * @param layoutRes 布局资源ID
         */
        private ViewHolder(Context context, ViewGroup parent, int layoutRes) {
            mViews = new SparseArray<>();
            this.context = context;
            View convertView = LayoutInflater.from(context).inflate(layoutRes, parent, false);
            convertView.setTag(this);
            // TODO:似乎最终目的就是要执行最后一句
            item = convertView;
        }

        //绑定ViewHolder与item

        /**
         * 绑定 ViewHolder 和 item
         * @param context 上下文
         * @param convertView  就得视图
         * @param parent 父布局
         * @param layoutRes 布局资源ID
         * @param position 适配器中当前要展示的元素的位置
         * @return
         */
        public static ViewHolder bind(Context context, View convertView, ViewGroup parent, int layoutRes, int position) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder(context, parent, layoutRes);
            } else {
                holder = (ViewHolder) convertView.getTag();
                holder.item = convertView;
            }
            holder.position = position;
            return holder;
        }


        /**
         * 根据 id 获取集合中保存的控件
         * @param id
         * @param <T>
         * @return
         */
        @SuppressWarnings("unchecked")
        public <T extends View> T getView(int id) {
            T t = (T) mViews.get(id);
            if (t == null) {
                t = (T) item.findViewById(id);
                mViews.put(id, t);
            }
            return t;
        }


        /**
         * 获取当前条目
         */
        public View getItemView() {
            return item;
        }

        /**
         * 获取条目位置
         */
        public int getItemPosition() {
            return position;
        }

        /**
         * 设置文字
         */
        public ViewHolder setText(int id, CharSequence text) {
            View view = getView(id);
            if (view instanceof TextView) {
                ((TextView) view).setText(text);
            }
            return this;
        }

        /**
         * 设置图片
         */
        public ViewHolder setImageResource(int id, int drawableRes) {
            View view = getView(id);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(drawableRes);
            } else {
                view.setBackgroundResource(drawableRes);
            }
            return this;
        }


        /**
         * 设置点击监听
         */
        public ViewHolder setOnClickListener(int id, View.OnClickListener listener) {
            getView(id).setOnClickListener(listener);
            return this;
        }

        /**
         * 设置可见
         */
        public ViewHolder setVisibility(int id, int visible) {
            getView(id).setVisibility(visible);
            return this;
        }

        /**
         * 设置标签
         */
        public ViewHolder setTag(int id, Object obj) {
            getView(id).setTag(obj);
            return this;
        }

        //其他方法可自行扩展

    }

}

