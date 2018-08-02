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
        int step = 10;
        String retval = "";
        boolean flag_cancelled = false;
        int i = 0;
        for (i = 0; i <= 100; i += step) {
            // 加入 isCancelled() 检测，尽快从doInBackground() 中返回
            if (isCancelled()) {
                flag_cancelled = true;
                retval = retval + "任务被取消";
                break;
            } else {
                dop.delay();
                publishProgress(i);
            }
        }
        // 决定返回值
        if (!flag_cancelled) {
            retval = "入口参数:" + integers[0].intValue() + "，结果：" + (i-step);
        }
        return  retval;
    }


    /**
     * 在UI线程执行
     * 该方法在UI线程中运行，可以对UI控件进行设置
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        txt.setText("开始执行异步任务");
        txt.setTextColor(Color.GREEN);
        pgbar.setProgress(0);
    }


    /**
     * 在UI线程执行
     * 在 doInBackground() 方法中每次调用 publishProgress() 方法都会触发该方法
     * 它运行在UI线程中，可以对UI控件进行操作
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int value = values[0];
        txt.setText("任务执行中" + value + "%...");
        txt.setTextColor(Color.MAGENTA);
        pgbar.setProgress(value); // 更新进度条进度
    }


    /**
     * 在UI线程执行
     * 用户取消任务最后的回调方法，在doInBackground()之后调用；
     * @param result 从doInBackground()中返回的结果。
     */
    @Override
    protected void onCancelled(String result) {
        super.onCancelled(result);
        txt.setText(result);
        txt.setTextColor(Color.RED);
        pgbar.setProgress(0);
    }

    /**
     * 在UI线程中执行
     * 等方法doInBackground()执行完毕，就运行这个方法
     * @param result 从doInBackground()返回来的结果
     */
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        txt.setText(result);
        txt.setTextColor(Color.BLUE);
        pgbar.setProgress(100);
    }
}
