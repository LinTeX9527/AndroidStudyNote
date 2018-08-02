package com.lintex9527.custom;

/**
 * 延时操作
 */
public class DelayOperator {
    // 毫秒级延时
    public void delay(int delayMs) {
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
