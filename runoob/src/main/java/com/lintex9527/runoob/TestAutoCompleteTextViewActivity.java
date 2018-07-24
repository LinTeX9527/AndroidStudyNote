package com.lintex9527.runoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

/**
 * AutoCompleteTextView 自动补全输入框控件
 * http://www.runoob.com/w3cnote/android-tutorial-autocompletetextview.html
 *
 * 这个控件和输入框类似，但是可以预先添加词库，用户输入时可以匹配并弹出合适的词
 */
public class TestAutoCompleteTextViewActivity extends AppCompatActivity {

    private AutoCompleteTextView actv_content;
    private MultiAutoCompleteTextView matv_content;

    private static final String[] data = new String[] {"小猪猪", "小狗狗", "小鸡鸡", "小猫猫", "小咪咪"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_auto_complete_text_view);

        actv_content = findViewById(R.id.actv_content);
        matv_content = findViewById(R.id.matv_content);

        // 通常一个适配器的构造方法需要传递以下3个参数：
        // 上下文
        // 单个项目的布局资源
        // 数据集合
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TestAutoCompleteTextViewActivity.this,
                android.R.layout.simple_dropdown_item_1line, data);
        actv_content.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_dropdown_item_1line, data);
        matv_content.setAdapter(adapter2);
        matv_content.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
