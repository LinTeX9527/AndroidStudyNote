package com.lintex9527.geoquiz;

/**
 * Android 与 MVC 设计模式
 *
 * 为GeoQuiz 项目新增一个Question类，该类的一个实例代表一道题目。
 * 然后在创建一个Question数据对象交由QuizActivity管理。
 */
public class Question {
    private int mTextResId; // 保存地理知识问题字符串的资源ID，而资源ID总是int类型
    private boolean mAnswerTrue;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
