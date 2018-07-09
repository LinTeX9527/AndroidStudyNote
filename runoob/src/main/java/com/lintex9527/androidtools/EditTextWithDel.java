package com.lintex9527.androidtools;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.lintex9527.runoob.R;

/**
 * 一个带有删除图标的EditText
 *
 * 参见教程：（这个教程实现的EditTextWithDel有问题，一加载就崩溃了。）
 * http://www.runoob.com/w3cnote/android-tutorial-edittext.html
 *
 * 新的教程：（还是有问题，虽然能显示EditText右边图标，但是焦点事件处理的不正确，也不能实现动画，不能弹出Toast）
 * https://blog.csdn.net/xiaanming/article/details/11066685
 *
 * 原理：
 * 可以在输入框的上下左右设置图片，可以利用属性android:drawableRight 设置删除小图标。
 * 点击右边的图片清除输入框的内容并且隐藏删除图标。
 * 但是Anroid并没有允许我们给右边小图标添加监听的功能，这时我们可以模拟点击事件，用输入框的onTouchEvent() 方法来模拟。
 * 当我们触摸抬起（ACTION_UP）的范围 大于数据框左侧到清除图标左侧的距离，并且小于输入框左侧到清除图标右侧的距离，就
 * 认为是点击清除图标。
 *
 *
 */

@SuppressLint("AppCompatCustomView")
public class EditTextWithDel extends EditText implements View.OnFocusChangeListener, TextWatcher {

    private static final String TAG = EditTextWithDel.class.getName();

    // 删除按钮的应用
    private Drawable mClearDrawable;

    // 控件是否有焦点
    private boolean hasFocus;

    public EditTextWithDel(Context context) {
        super(context, null);
    }

    public EditTextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.editTextStyle);
    }

    public EditTextWithDel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(R.drawable.xx);
        }

        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        // 默认设置隐藏图标
        setClearIconVisible(false);
        // 设置焦点改变的监听器
        setOnFocusChangeListener(this);
        // 设置输入框里面内容发生改变的监听器
        addTextChangedListener(this);

        Log.d(TAG, "init()");
    }


    /**
     * 因为我们不能直接给EditText 设置点击事件，所以我们用按下的位置来模拟点击事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            Log.d(TAG, "onTouchEvent() -- ACTION_UP");
            if (getCompoundDrawables()[2] != null) { // 右边的图标
                boolean touchable = (event.getX() > (getWidth() - getTotalPaddingRight())) && ( event.getX() < (getWidth() - getPaddingRight()));
                if (touchable) { // 清空输入框
                    Log.d(TAG, "onTouchEvent() -- clear");
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /**
     * 当输入框里面内容发生变化的时候的回调方法
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (hasFocus) {
            Log.d(TAG, "onTextChanged() -- ClearIcon visiable");
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 当输入框焦点发生变化时，判断里面字符长度设置清除图标的显示与隐藏
     * @param v
     * @param hasFocus
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;

        Log.d(TAG, "hasFocus --> " + hasFocus);

        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                            getCompoundDrawables()[1],
                            right,
                            getCompoundDrawables()[3]);
    }


    /**
     * 设置晃动动画
     */
    public void setShakeAnimation() {
        this.setAnimation(shakeAnimation(5));
    }

    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }
}
