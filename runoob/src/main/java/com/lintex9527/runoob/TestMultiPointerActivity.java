package com.lintex9527.runoob;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * 多点触碰
 * http://www.runoob.com/w3cnote/android-tutorial-touchlistener-ontouchevent.html
 */
public class TestMultiPointerActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView img_touch;

    // 缩放控制
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();

    // 不同状态的表示
    private static final int MODE_NONE = 0;
    private static final int MODE_DRAG = 1;
    private static final int MODE_ZOOM = 2;
    private int mode = MODE_NONE;

    // 定义第一个按下的点，两个接触点的中点，以及初始的两指按下的距离
    private PointF startPoint = new PointF();
    private PointF midPoint = new PointF();
    private float oriDis = 1f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_multi_pointer);

        img_touch = (ImageView) findViewById(R.id.img_touch);
        img_touch.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ImageView view = (ImageView) v;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN: // 单指触碰
                matrix.set(view.getImageMatrix());
                savedMatrix.set(matrix);
                startPoint.set(event.getX(), event.getY());
                mode = MODE_DRAG;
                break;

            case MotionEvent.ACTION_POINTER_DOWN: // 双指触碰
                oriDis = distance(event);
                if (oriDis > 10f) {
                    savedMatrix.set(matrix);
                    midPoint = middle(event);
                    mode = MODE_ZOOM;
                }
                break;

            case MotionEvent.ACTION_UP: // 最后一个手指放开
            case MotionEvent.ACTION_POINTER_UP: // 有一个手指离开
                mode = MODE_NONE;
                break;

            case MotionEvent.ACTION_MOVE: // 单指滑动
                if (mode == MODE_DRAG) {
                    // 一个手指滑动
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - startPoint.x, event.getY() - startPoint.y);
                } else if (mode == MODE_ZOOM){
                    // 两个手指滑动
                    float newDist = distance(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = newDist / oriDis;
                        matrix.postScale(scale, scale, midPoint.x, midPoint.y);
                    }
                }
                break;
        }

        // 设置ImageView 的Matrix
        view.setImageMatrix(matrix);
        return true;
    }


    /**
     * 计算两个触摸点之间的距离
     * @param event
     * @return
     */
    private float distance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x*x + y*y);
    }


    /**
     * 计算两个触摸点的中点
     * @param event
     * @return
     */
    private PointF middle(MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        return new PointF(x / 2, y / 2);
    }
}
