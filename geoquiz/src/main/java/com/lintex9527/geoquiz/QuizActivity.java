package com.lintex9527.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 参见教程：
 * 《Android编程权威指南 第2版》
 *
 * 应用等待某个特定事件的发生，也可以说是正在“监听”特定事件。为响应某个事件而创建的对象叫做监听器（listener）
 * 监听器是实现特定监听器接口的对象，用来监听某类时间的发生。
 *
 * 修改日志：
 * 实现方法onSaveInstanceState()，可保存和恢复基本数据类型、可实现Serializable或者Parcelable接口的对象。
 * 在 Bundle中保存定制类对象不是一个好主意。因为你取回的对象可能已经过时了。比较好的做法是通过其他方式保存定制类对象，
 * 而在 Bundle 中保存对象对应的基本数据类型的标识符。
 */

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = QuizActivity.class.getName();

    // 新增一个常量作为将要存储在 bundle 中的键值对的键
    // 然后覆盖方法 onSaveInstanceState() 方法，将 mCurrentIndex变量值保存到bundle中
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mLastButton; // 上一题
    private ImageButton mNextButton; // 下一题
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);


        // 检查并获取保存的值
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        // 显示第一个问题
        updateQuestion();

        // 单击问题直接显示下一题
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mLastButton = (ImageButton) findViewById(R.id.last_button);
        mLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这里 mQuestionBank.length - 1 是因为数组下标的上限就是数组长度减1
                mCurrentIndex = (mCurrentIndex == 0) ? (mQuestionBank.length-1) : (mCurrentIndex - 1);
                updateQuestion();
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState()");

        // 将mCurrentIndex变量值保存到 bundle 中
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    /**
     * 根据当前问题编号显示问题。
     */
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }


    /**
     * 检查用户答案正确与否
     * @param userPressedTrue 用户输入值
     */
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(getApplicationContext(), messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
}
