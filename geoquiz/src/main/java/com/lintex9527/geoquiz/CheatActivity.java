package com.lintex9527.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 有几个漏洞：
 * 1、用户作弊后，可以旋转CheatActivity来清除作弊痕迹；
 * 2、作弊返回后，用户可以旋转QuizActivity来清除mIsCheater变量值；
 * 3、用户可以不断单击NEXT按钮，跳到偷看过答案的问题，从而是作弊记录丢失
 *
 * 解决办法：
 * #1和#2相似，设备配置改变时必须要保存某些状态，覆盖onSaveInstanceState()来保存，onCreate()中重新取出来
 * #3 需要对每个问题做标记，记录用户是否偷看过答案，可以写到文件中，也可以用列表来实现。
 */


public class CheatActivity extends AppCompatActivity {

    private static final String TAG = CheatActivity.class.getName();

    // 键，关联问题答案是TRUE还是FALSE
    private static final String EXTRA_ANSWER_IS_TRUE = "com.lintex9527.geoquiz.answer_is_true";

    // 键，关联用户是否偷看答案
    private static final String EXTRA_ANSWER_SHOWN = "com.lintex9527.geoquiz.answer_shown";

    private boolean mAnswerIsTrue;
    // 只要用户偷看答案，就标记用户在这一题作弊了
    private boolean mIsCheated;

    private TextView mAnswerTextView;
    private Button mShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        // 先恢复原始状态
        if (savedInstanceState != null) {
            mIsCheated = savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN, false);
            // 即便没有单击作弊按钮，只要设备配置发生了变化就回传作弊标记
            if (mIsCheated) {
                setAnswerShowResult(true);
            }
        } else {
            mIsCheated = false;
        }
        Log.d(TAG, "onCreate(), mIsCheated = " + mIsCheated);

        // 在新建活动的时候就得到传递过来的参数
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        // 偷看答案的按钮，只要用户点击就标记用户在这一题上作弊了
        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示正确答案
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }

                // 只要单击了这个按钮就认为用户作弊了
                setAnswerShowResult(true);
            }
        });
    }

    /**
     * 设备配置发生变更时，记录用户当前是否已经作弊
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "onSaveInstanceState(), mIsCheated = " + mIsCheated);
        outState.putBoolean(EXTRA_ANSWER_SHOWN, mIsCheated);
    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    private void setAnswerShowResult(boolean isAnswerShown) {
        // 标记用户作弊了
        mIsCheated = true;

        // 把标记回传给原来的Activity
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    // 类方法，可以直接调用，得知用户是否作弊
    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }
}
