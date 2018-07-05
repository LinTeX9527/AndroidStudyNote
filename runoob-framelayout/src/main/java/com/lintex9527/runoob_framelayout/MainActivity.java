package com.lintex9527.runoob_framelayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


/**
 * 随手指移动的妹子图
 * 教程链接：
 * http://www.runoob.com/w3cnote/android-tutorial-framelayout.html
 *
 * 需要注意，原来分配资源时，申请一张很大的图片作为背景可能会导致内存溢出。
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.mylayout);
        final MeiziView meizi = new MeiziView(MainActivity.this);
        // 为View 对象添加触摸事件监听器
        meizi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 设置对象显示的位置
                meizi.setBitmapX(event.getX() - 20);
                meizi.setBitmapY(event.getY() - 20);
                // 调用重绘方法
                meizi.invalidate();
                return true;
            }
        });

        frameLayout.addView(meizi);

        Button btnNextPage = (Button) findViewById(R.id.btn_nextPage);
        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), XiaojiejieActivity.class);
                startActivity(intent);
            }
        });
    }
}
