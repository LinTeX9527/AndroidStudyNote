package com.lintex9527.runoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 了解到 ImageView 中 src 和 background 的区别：
 * 1、src 指定的是内容，而 background 指的是背景；
 * 2、当宽高都是 wrap_content时，指定 src 和 background 效果都是一样的；
 * 3、但是限定宽或者高时， background 会拉伸填充，而 src 不会，会缩放填充，不会改变原图比例。
 * 4、设置透明度 setAlpha 只有 src 才有效。
 *
 * 在Java代码中设置 background 和 src 属性
 * src -- setImageDrawable()
 * background -- setBackgroundDrawable()
 *
 * 方法 adjustViewBounds() 设置缩放是否保存原图长宽比
 * 必须和 maxHeight, maxWidth 一起使用
 */
public class TestImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_image_view);
    }
}
