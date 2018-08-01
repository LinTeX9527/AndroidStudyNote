package com.lintex9527.custom;


import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 自定义一个AsyncTask
 */
public class MyAsyncTask extends AsyncTask<Integer, Integer, String> {

    private TextView txt;
    private ProgressBar pgbar;

    public MyAsyncTask(TextView txt, ProgressBar pgbar) {
        super();
        this.txt = txt;
        this.pgbar = pgbar;
    }


    /**
     * 该方法不在UI线程中，主要用于异步操作，通过调用 publishProgress() 方法
     * 触发 onProgressUpdate() 对UI进行操作
     * @param integers
     * @return
     */
    @Override
    protected String doInBackground(Integer... integers) {
        DelayOperator dop = new DelayOperator();
        int i = 0;
        for (i = 0; i <= 100; i += 10) {
            dop.delay();
            publishProgress(i);
        }
        return i + integers[0].intValue() + "";
    }


    /**
     * 该方法在UI线程中运行，可以对UI控件进行设置
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        txt.setText("开始执行异步线程");
        txt.setTextColor(Color.GREEN);
    }


    /**
     * 在 doInBackground() 方法中每次调用 publishProgress() 方法都会触发该方法
     * 它运行在UI线程中，可以对UI控件进行操作
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int value = values[0];
        pgbar.setProgress(value); // 更新进度条进度
    }


    /**
     * 等方法doInBackground()执行完毕，就运行这个方法
     * @param s
     */
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        txt.setText("异步任务执行结束");
        txt.setTextColor(Color.BLUE);
    }
}
