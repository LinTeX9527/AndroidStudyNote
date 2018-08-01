package com.lintex9527.custom;

/**
 * 延时操作
 */
public class DelayOperator {
    public void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
