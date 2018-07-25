package com.lintex9527.runoob;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

/**
 * ViewFlipper 翻转视图
 * http://www.runoob.com/w3cnote/android-tutorial-viewflipper.html
 *
 * 动态图片轮播，支持手势滑动视图
 */
public class TestViewFlipper2Activity extends AppCompatActivity {

    private Context mContext;
    private ViewFlipper mViewFlipper;
    private int[] resId = {R.mipmap.ic_help_view_1,
                            R.mipmap.ic_help_view_2,
                            R.mipmap.ic_help_view_3,
                            R.mipmap.ic_help_view_4
                            };

    private final static int MIN_MOVE = 200; // 最小距离
    private MyGestureListener mgListener;
    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_flipper2);

        mContext = TestViewFlipper2Activity.this;
        // 实例化SimpleOnGestureListener 与 GestureDetector 对象
        mgListener = new MyGestureListener();
        mDetector = new GestureDetector(this, mgListener);
        mViewFlipper = (ViewFlipper)findViewById(R.id.vflp_help);
        // 动态导入添加子View
        for (int i = 0; i < resId.length; i++) {
            mViewFlipper.addView(getImageView(resId[i]));
        }
    }


    /**
     * 重写onTouchEvent触发MyGestureListener里的方法
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > MIN_MOVE) {
                mViewFlipper.setInAnimation(mContext, R.anim.right_in);
                mViewFlipper.setOutAnimation(mContext, R.anim.right_out);
                mViewFlipper.showNext();
            } else if (e2.getX() - e1.getX() > MIN_MOVE) {
                mViewFlipper.setInAnimation(mContext, R.anim.left_in);
                mViewFlipper.setOutAnimation(mContext, R.anim.left_out);
                mViewFlipper.showPrevious();
            }
            return true;
        }
    }

    private ImageView getImageView(int resId) {
        ImageView img = new ImageView(this);
        img.setBackgroundResource(resId);
        return img;
    }
}
