package com.lintex9527.runoob;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 *
 * TextView 参见属性，参见教程：
 * http://www.runoob.com/w3cnote/android-tutorial-textview.html
 *
 * 如何通过XML代码设置一个ShapeDrawable参见：
 * https://blog.csdn.net/dangnianmingyue_gg/article/details/51675453
 */
public class TestTextViewActivity extends AppCompatActivity {

    private static final String TAG = TestTextViewActivity.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_textview);

        Log.d(TAG, "onCreate()");

        initGUI();
    }


    /**
     * 初始化GUI
     */
    private void initGUI (){

        // 重点在下面的代码可以设置TextView 控件中4个方向的图片
        TextView txtThree = (TextView) findViewById(R.id.txtThree);
        Drawable[] drawables = txtThree.getCompoundDrawables();
        // drawables 数组下标0~3，分别表示左上右下

        drawables[0].setBounds(0, 0, 240, 180);

        // 设置上面的图标的左下角坐标为 (20, 0) 右上角坐标为 (180, 160)
        // 坐标起始点是TextView 内容的起始点坐标
        drawables[1].setBounds(0, 0, 180, 180);

        drawables[2].setBounds(0, 0, 160, 160);
        // 重新设置可绘制对象
        // txtThree.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
        // 设置 null 可以取消图片
        txtThree.setCompoundDrawables(drawables[0] , drawables[1], drawables[2], null);

    }
}
