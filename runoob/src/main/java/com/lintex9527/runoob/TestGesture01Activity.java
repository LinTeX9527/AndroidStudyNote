package com.lintex9527.runoob;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Gesture 手势
 * http://www.runoob.com/w3cnote/android-tutorial-gestures.html
 *
 * 实现手势检测的步骤如下：
 * 1、创建GestureDetector对象，创建时需要实现 GestureDetector.OnGestureListener 并传入；
 * 2、将Activity 或者某个组件上的 onTouchEvent() 时间交给 GestureDetector 处理即可。
 *
 * 其实识别手势的重点是实现 GestureDetector.OnGestureListener 中的多个抽象方法。
 *
 * TODO:有待详细了解 GestureListener 原理
 * 这个示例代码在小米5手机上运行的和作者的有如下几点区别：
 * 1、大量打印onTouchEvent()
 * 2、手指在屏幕移动时会打印大量的 onTouchEvent() 和 onScroll()
 * 3、滑动的时候能打印 onFling() 提示，但是没有了onSingleTapUp() 提示。
 */
public class TestGesture01Activity extends AppCompatActivity {

    private static final String TAG = TestGesture01Activity.class.getName();

    // 判断移动距离的最小值
    private static final int MOVE_MIN_Y = 200;

    private UpDownGestureListener mgListener;
    private GestureDetector mgDetector;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_gesture01);

        mContext = TestGesture01Activity.this;

        // 实例化GestureListener 和 GestureDetector 对象
        mgListener = new UpDownGestureListener();
        mgDetector = new GestureDetector(this, mgListener);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent()");
        return mgDetector.onTouchEvent(event); // 通过mgDetector 转发 MotionEvent 给 GestureListener ，进行手势识别
    }


    /**
     * 自定义一个GestureListener，这个是View类下的.
     * 在这里进行手势识别。
     */
    private class MyGestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(TAG, "onDown() 按下");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.d(TAG, "onShowPress() 手指按下一段时间，不过还没有长按");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d(TAG, "onSingleTapUp() 手指离开屏幕的一瞬间");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(TAG, "onScroll() 在触摸屏上移动");
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.d(TAG, "onLongPress() 长按且没有松开");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d(TAG, "onFling() 迅速滑动并松开");
            return false;
        }
    }

    /**
     * 自定义一个手势识别的类，继承自 GestureDetector.SimpleOnGestureListener
     * 这里实现快速滑动的检测方法 onFling()
     * 手指上滑启动新的Activity
     * 手指下滑关闭当前的Activity
     */
    private class UpDownGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getY() - e2.getY() > MOVE_MIN_Y) { // 上滑
                startActivity(new Intent(TestGesture01Activity.this, TestGesture01Activity.class));// 启动新的Activity
                Toast.makeText(mContext, "上滑启动Activity", Toast.LENGTH_SHORT).show();
            } else if (e2.getY() - e1.getY() > MOVE_MIN_Y) { // 下滑
                finish(); // 关闭当前Activity
                Toast.makeText(mContext, "下滑关闭当前Activity", Toast.LENGTH_SHORT).show();
            } else if (e1.getX() - e2.getX() > MOVE_MIN_Y) { // 左滑
                Toast.makeText(mContext, "向左滑", Toast.LENGTH_SHORT).show();
            } else if (e2.getX() - e1.getX() > MOVE_MIN_Y) { // 右滑
                Toast.makeText(mContext, "向右滑", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }
}
