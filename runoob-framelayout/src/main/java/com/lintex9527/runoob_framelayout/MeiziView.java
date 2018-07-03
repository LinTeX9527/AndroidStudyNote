package com.lintex9527.runoob_framelayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class MeiziView extends View {

    private  static final String TAG = MeiziView.class.getName();

    // 对象的位置，左上角的坐标
    private float bitmapX;
    private float bitmapY;

    public MeiziView(Context context) {
        super(context);

        // 设置起始坐标
        bitmapY = 0;
        bitmapY = 200;
    }

    public float getBitmapX() {
        return bitmapX;
    }

    public void setBitmapX(float bitmapX) {
        this.bitmapX = bitmapX;
    }

    public float getBitmapY() {
        return bitmapY;
    }

    public void setBitmapY(float bitmapY) {
        this.bitmapY = bitmapY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG, "onDraw()");

        // 创建并且实例化 Paint 对象
        Paint paint = new Paint();

        // 根据图片生成位图对象
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.meizitu5467);

        // 绘制图片
        canvas.drawBitmap(bitmap, bitmapX, bitmapY, paint);

        // 判断图片是否回收，没有回收的话则强制回收图片
        if (bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
