package com.lintex9527.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * 基于回调的事件处理机制
 * http://www.runoob.com/w3cnote/android-tutorial-callback-event-handle.html
 *
 * 下面3个事件回调函数的返回值都是boolean类型的，返回值是用来标志这个方法是否已经完全处理该事件了，
 * 如果为false就说明没处理完，就会继续传播事件，触发组件所在的Activity的相关回调方法；
 * 为true就不会继续传播了。
 */


@SuppressLint("AppCompatCustomView")
public class MyButton extends Button {

    private static String TAG = MyButton.class.getName();

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //TODO: 为什么 onKeyDown() 和 onKeyUp() 没有发生？
    /**
     * 重写键盘按下触发的时间
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        Log.d(TAG, "onKeyDown() 调用，" + "keyCode: " + keyCode + ", event: " + event.getSource());
        return false; // 返回true就不会传递事件，返回false就会继续传递
    }


    /**
     * 键盘弹起时触发
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
        Log.d(TAG, "onKeyUp() 调用，" + "keyCode: " + keyCode + ", event: " + event.getSource());
        return true;
    }


    /**
     * 组件被触摸时触发
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        Log.d(TAG, "onTouchEvent() 调用，" + " event: " + event.getSource());
        return false; // TODO:这个 true 改为 false ，感觉button弹起来响应很慢。
    }
}
