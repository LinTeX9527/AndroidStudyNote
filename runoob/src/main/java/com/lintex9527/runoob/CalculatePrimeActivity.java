package com.lintex9527.runoob;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * 子线程中使用Handler 示例
 * http://www.runoob.com/w3cnote/android-tutorial-handler-message.html
 *
 */
public class CalculatePrimeActivity extends AppCompatActivity {

    private static final String KEY_UPPER_NUM = "upper";
    private static final int CODE_CALC = 0x123;
    private Context mContext;
    EditText editNum;
    CalThread mCalThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_prime);
        mContext = CalculatePrimeActivity.this;

        editNum = (EditText) findViewById(R.id.editNum);
        mCalThread = new CalThread();
        // 启动子线程
        mCalThread.start();
    }


    /**
     * 按钮单击事件，通过Handler发送一个消息
     * @param source
     */
    public void calculate(View source) {
        // 创建消息
        Message msg = new Message();
        msg.what = CODE_CALC;
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_UPPER_NUM, Integer.parseInt(editNum.getText().toString()));
        msg.setData(bundle);

        // 通过Handler发送一个消息
        mCalThread.mHandler.sendMessage(msg);
    }


    /**
     * 定义一个线程，在子线程中使用Handler
     */
    class CalThread extends Thread {
        Handler mHandler;

        @Override
        public void run() {
            Looper.prepare(); // 新建一个Looper，自动新建MessageQueue

            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == CODE_CALC) {
                        int upper = msg.getData().getInt(KEY_UPPER_NUM);
                        List<Integer> nums = new ArrayList<Integer>();
                        // 计算从2开始到upper的所有质数
                        outer:
                        for (int i = 2; i <= upper; i++){
                            // 用 i 除以从2开始、到i 的所有平方根的所有数
                            for (int j = 2; j <= Math.sqrt(i); j++) {
                                // 如果可以整除，表明这个数不是质数
                                if (i != 2 && (i%j == 0)) {
                                    continue outer;
                                }
                            }
                            nums.add(i);
                        }

                        // 使用Toast显示统计出来的所有质数
                        Toast.makeText(mContext, nums.toString(), Toast.LENGTH_SHORT).show();
                    }
                } // handleMessage() 结束
            };

            Looper.loop(); // 开始Looper 循环
        }
    }
}
