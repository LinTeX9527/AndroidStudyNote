package com.lintex9527.runoob;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * PopupWindow 悬浮框
 * http://www.runoob.com/w3cnote/android-tutorial-popupwindow.html
 *
 * 类似于一个上下文菜单，可以自定义视图
 */
public class TestPopupWindowActivity extends AppCompatActivity {

    private static final String TAG = TestPopupWindowActivity.class.getName();
    private Button btn_popup;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_popup_window);

        mContext = TestPopupWindowActivity.this;
        btn_popup = (Button) findViewById(R.id.btn_popup);
        btn_popup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG, "准备弹出悬浮框");
                initPopupWindow(v);
            }
        });
    }


    private void initPopupWindow(View v) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_popup, null, false);
        Button btn_xixi = (Button) view.findViewById(R.id.btn_xixi);
        Button btn_hehe = (Button) view.findViewById(R.id.btn_hehe);

        // 构造一个PopupWindow，参数依次是要加载的View，宽度，高度，是否获得焦点
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setAnimationStyle(R.anim.anim_pop); // 设置加载动画

        //这些是为了点击非PopupWindow区域，PopupWindow会小时的。如果没有下面的代码，无论按多少次后退按键
        // PopupWindow 都不会关闭，而且无法退出程序。加了下面的代码就可以解决这个问题。
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "触摸事件");
                return false;
                // 如果返回true的话，touch 时间将被拦截
                // 拦截后 PopupWindow 的 OnTouchEvent 不被调用，这样点击外部区域无法 dismiss
            }
        });

        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000)); // 需要为 PopupWindow 设置一个背景才有效

        // 设置 PopupWindow 显示的位置，参数依次是参照View， X轴的偏移量，Y轴的偏移量
        popupWindow.showAsDropDown(v, 50, 0);

        // 设置 PopupWindow 里的按钮的事件监听器
        btn_xixi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "你点击了嘻嘻", Toast.LENGTH_SHORT).show();
            }
        });

        btn_hehe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "你点击了呵呵", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
