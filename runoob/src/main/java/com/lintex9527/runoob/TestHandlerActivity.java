package com.lintex9527.runoob;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Handler 相关
 * http://www.runoob.com/w3cnote/android-tutorial-handler-message.html
 *
 * Handler 使用示例---Handler 写在主线程中：
 * 在主线程中，因为系统已经初始化了一个Looper对象，所谓我们直接创建Handler对象，就可以进行信息的发送和处理了。
 *
 * 代码实例：
 * 简单的一个定时切换图片的程序，通过Timer定时器，定时修改ImageView显示的内容，从而形成帧动画。
 */
public class TestHandlerActivity extends AppCompatActivity {

    // ImageView 更新图片的代码
    private static final int CODE_UPDATE = 0x123;

    // 图片集合，分解一个GIF图片为多个图片
    int imgids[] = new int[] {
            R.mipmap.xueyou_cat_01,
            R.mipmap.xueyou_cat_02,
            R.mipmap.xueyou_cat_03,
            R.mipmap.xueyou_cat_04,
            R.mipmap.xueyou_cat_05,
            R.mipmap.xueyou_cat_06,
            R.mipmap.xueyou_cat_07,
            R.mipmap.xueyou_cat_08,
            R.mipmap.xueyou_cat_09,
            R.mipmap.xueyou_cat_10,
            R.mipmap.xueyou_cat_11,
            R.mipmap.xueyou_cat_12,
            R.mipmap.xueyou_cat_13,
            R.mipmap.xueyou_cat_14,
            R.mipmap.xueyou_cat_15,
            R.mipmap.xueyou_cat_16,
            R.mipmap.xueyou_cat_17,
            R.mipmap.xueyou_cat_18,
            R.mipmap.xueyou_cat_19,
            R.mipmap.xueyou_cat_20,
            R.mipmap.xueyou_cat_21
    };

    int imgstart = 0;

    private ImageView imagechange;

    // 在 Handler 中更新ImageView
    final Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CODE_UPDATE){
                imagechange.setImageResource(imgids[imgstart++ % 21]);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);

        imagechange = (ImageView) findViewById(R.id.imgchange);

        // 定时器，每隔200毫秒发送一个信息
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.sendEmptyMessage(CODE_UPDATE);
            }
        }, 0, 200);
    }


    /**
     * 在新的Activity 中演示子线程中使用Handler
     * 这个按钮的作用是启动一个新的Activity
     * @param view
     */
    public void startTest2(View view){
        if (view.getId() == R.id.btn_test_handler02) {
            Intent intent = new Intent(TestHandlerActivity.this, CalculatePrimeActivity.class);
            startActivity(intent);
        }
    }
}
