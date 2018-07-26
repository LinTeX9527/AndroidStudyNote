package com.lintex9527.runoob;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * 其他几种常用的对话框
 * http://www.runoob.com/w3cnote/android-tutorial-dialog.html
 */
public class MiscDialogActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_one;
    private Button btn_two;
    private Button btn_three;
    private Button btn_datepick;
    private Button btn_timepick;
    private String message = "";

    private ProgressDialog pd1 = null;
    private ProgressDialog pd2 = null;
    private static final int MAX_VALUE = 100;
    private static final int MSG_CODE = 123;
    private int progressStart = 0;
    private int add = 0;
    private Context mContext = null;


    /**
     * 定义一个用于更新进度的Handler，因为只能由主线程更新界面，所以要用Handler传递信息
     */
    private final Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_CODE) {
                pd2.setProgress(progressStart);
            }

            if (progressStart >= MAX_VALUE) {
                pd2.dismiss();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misc_dialog);

        mContext = MiscDialogActivity.this;
        bindViews();
    }

    private void bindViews() {
        btn_one = (Button) findViewById(R.id.btn_pgdialog_one);
        btn_two = (Button) findViewById(R.id.btn_pgdialog_two);
        btn_three = (Button) findViewById(R.id.btn_pgdialog_three);
        btn_datepick = (Button) findViewById(R.id.btn_datepick);
        btn_timepick = (Button) findViewById(R.id.btn_timepick);

        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_datepick.setOnClickListener(this);
        btn_timepick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pgdialog_one:
                // 普通进度条对话框
                // 参数依次是：上下文，标题，内容，是否显示进度，是否可以用取消按钮关闭
                // 这个是常见的进度条，没有进度百分比，也没有按钮，点击屏幕其他位置可以取消显示
                ProgressDialog.show(mContext, "资源加载中", "资源加载中，请稍后。。。", false, true);
                break;


            case R.id.btn_pgdialog_two:
                //
                pd1 = new ProgressDialog(mContext);
                // 依次设置标题，内容，是否用取消按钮关闭，是否显示进度
                pd1.setTitle("软件更新中");
                pd1.setMessage("软件正在更新，请稍后...");
                pd1.setCancelable(true);
                // 这里设置进度条的风格， HORIZONTAL 是水平进度条，SPINNER 是圆形进度条
                pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                // 设置进度条是有明确进度的还是没有明确进度的，例如圆形进度条就没有明确的进度
                pd1.setIndeterminate(true);
                pd1.show();
                break;

            case R.id.btn_pgdialog_three:
                // 可更新进度的进度条对话框
                progressStart = 0;
                add = 0;
                // 依次设置一些属性
                pd2 = new ProgressDialog(mContext);
                pd2.setMax(MAX_VALUE);
                pd2.setTitle("文件读取中");
                pd2.setMessage("文件加载中，请稍候...");
                // 这里设置为不可以通过取消按钮关闭进度条
                pd2.setCancelable(false);
                pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                // 这里设置的是否显示进度，设为false才是显示进度
                pd2.setIndeterminate(false);
                pd2.show();
                // 这里新建一个线程，重写run() 方法
                new Thread(){
                    @Override
                    public void run() {
                        while (progressStart < MAX_VALUE) {
                            // 这里的算法是决定进度条变化的，可以按照需要写
                            progressStart = 2 * usetime();
                            // 把信息码发送给handler
                            mHandler.sendEmptyMessage(MSG_CODE);

                        }
                    }
                }.start();
                break;

            case R.id.btn_datepick:
                // 日期选择对话框
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(mContext,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // 这里获取到的月份需要加上1
                                message = "";
                                message += "你选择的是：" + year + "年" + (monthOfYear+1) + "月" + dayOfMonth + "日";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show();
                break;

            case R.id.btn_timepick:
                // 时间选择对话框
                Calendar calendar1 = Calendar.getInstance();
                new TimePickerDialog(mContext,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                message = "";
                                message += "你选择的时间是：" + hourOfDay + "时" + minute + "分";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                        },
                        calendar1.get(Calendar.HOUR_OF_DAY),
                        calendar1.get(Calendar.MINUTE),
                        true
                ).show();
                break;
        }
    }


    /**
     * 这里设置一个耗时的方法
     * @return
     */
    private int usetime() {
        add ++;
        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return add;
    }
}
