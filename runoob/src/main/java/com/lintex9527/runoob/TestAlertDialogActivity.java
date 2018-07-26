package com.lintex9527.runoob;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TestAlertDialogActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = TestAlertDialogActivity.class.getName();

    private Button btn_dialog_one;
    private Button btn_dialog_two;
    private Button btn_dialog_three;
    private Button btn_dialog_four;

    private Context mContext;
    private boolean[] checkItems;
    private String yourchoice;

    private AlertDialog alert = null;
    private AlertDialog.Builder mBuilder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_alert_dialog);

        mContext = TestAlertDialogActivity.this;
        bindViews();
    }

    private void bindViews() {
        btn_dialog_one = (Button) findViewById(R.id.btn_dialog_one);
        btn_dialog_two = (Button) findViewById(R.id.btn_dialog_two);
        btn_dialog_three = (Button) findViewById(R.id.btn_dialog_three);
        btn_dialog_four = (Button) findViewById(R.id.btn_dialog_four);

        btn_dialog_one.setOnClickListener(this);
        btn_dialog_two.setOnClickListener(this);
        btn_dialog_three.setOnClickListener(this);
        btn_dialog_four.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_one:
                // 普通对话框
                alert = null;
                mBuilder = new AlertDialog.Builder(mContext);
                alert = mBuilder.setIcon(R.mipmap.ic_icon_fish)
                        .setTitle("系统提示")
                        .setMessage("这是一个普通的AlertDialog\n带有3个按钮，分别是取消，中立和确定")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "点击了[取消]按钮", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "点击了[确定]按钮", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("中立", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "点击了[中立]按钮", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create(); // 通过 mBuilder.create() 方法才能创建一个AlertDialog

                alert.show(); // 显式对话框
                break;

            case R.id.btn_dialog_two:
                // 普通列表对话框
                final String[] lesson = new String[]{"语文", "数学", "英语", "化学", "生物", "物理", "体育"};
                alert = null;
                mBuilder = new AlertDialog.Builder(mContext);
                alert = mBuilder.setIcon(R.mipmap.ic_icon_fish)
                        .setTitle("选择你喜欢的课程")
                        // 注意 setItems
                        .setItems(lesson, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "你选择了" + lesson[which], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alert.show();
                Log.d(TAG, "普通列表对话框，其实只能单击，也就是单选");
                break;

            case R.id.btn_dialog_three:
                // 单选列表对话框
                final String[] fruits = new String[] {"苹果", "雪梨", "香蕉", "葡萄", "西瓜"};
                alert = null;
                mBuilder = new AlertDialog.Builder(mContext);
                alert = mBuilder.setIcon(R.mipmap.ic_icon_fish)
                        .setTitle("选择你最喜欢的水果，只能单选")
                        // 注意 setSingleChoiceItems
                        .setSingleChoiceItems(fruits, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "你选择了" + fruits[which], Toast.LENGTH_SHORT).show();
                                yourchoice = fruits[which];
                            }
                        })
                        // 即便是单选列表也可以添加PositiveButton 和 NegativeButton
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d(TAG, "确定按钮的which = " + which);
                                Toast.makeText(mContext, "最终的选择是：" + yourchoice, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alert.show();
                break;
            case R.id.btn_dialog_four:
                // 多选列表对话框
                final String[] menu = new String[]{"水煮豆腐", "萝卜牛腩", "酱油鸡", "胡椒猪肚鸡"};
                checkItems = new boolean[] {false, false, false, false};
                alert = null;
                mBuilder = new AlertDialog.Builder(mContext);
                alert = mBuilder.setIcon(R.mipmap.ic_icon_fish)
                        // 注意 setMultiChoiceItems
                        .setMultiChoiceItems(menu, checkItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checkItems[which] = isChecked;
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String result = "";
                                for (int i = 0; i < checkItems.length; i++){
                                    if (checkItems[i]) {
                                        result += menu[i] + " ";
                                    }
                                }
                                Toast.makeText(mContext, "你选了:\n" + result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alert.show();
                break;
        }
    }
}
