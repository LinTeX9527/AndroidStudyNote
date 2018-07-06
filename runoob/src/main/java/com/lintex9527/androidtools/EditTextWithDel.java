package com.lintex9527.androidtools;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.lintex9527.runoob.R;

/**
 * 一个带有删除图标的EditText
 *
 * 参见教程：
 * http://www.runoob.com/w3cnote/android-tutorial-edittext.html
 */

public class EditTextWithDel extends android.support.v7.widget.AppCompatEditText {

    private static final String TAG = EditTextWithDel.class.getName();

    private Drawable imageDisable;
    private Context mContext;

    public EditTextWithDel(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public EditTextWithDel (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        imageDisable = mContext.getResources().getDrawable(R.drawable.xx);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged()");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged()");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged()");
                setDrawable();
            }
        });

        setDrawable();
    }

    // 设置删除图片
    private void setDrawable() {
        Log.d(TAG, "setDrawable()");
        if (length() < 1) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, imageDisable, null);
        }
    }

    // 处理删除事件

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imageDisable != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Log.d(TAG, "eventX = " + eventX + "; eventY = " + eventY);
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 100;
            if (rect.contains(eventX, eventY)) {
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        Log.d(TAG, "performClick()");
        return super.performClick();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
