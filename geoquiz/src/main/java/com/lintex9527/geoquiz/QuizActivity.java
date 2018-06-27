package com.lintex9527.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

    // 键，关联用户在这一题上是否作弊
    private static final String KEY_IS_CHEATER = "com.lintex9527.quizactivity.ischeater";

    // 键，关联用户在所有答案上的作弊标记
    private static final String KEY_ANSWER_CHEAT_FLAG = "com.lintex9527.quizactivity.answercheatflag";

    private static final int REQUEST_CODE_CHEAT = 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mLastButton; // 上一题
    private ImageButton mNextButton; // 下一题
    private Button mCheatButton;    // 显示作弊选项的按钮
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    // 用一个列表记录每一个问题是否有作弊行为
    private List<Boolean> mAnswerCheatFlag;

    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        // 初始化作弊标记
        if (mAnswerCheatFlag == null) {
            mAnswerCheatFlag = new ArrayList<>();
            for (int index = 0; index < mQuestionBank.length; index++) {
                Log.d(TAG, "onCreate():  mAnswerCheatFlag size = " + mAnswerCheatFlag.size());
                Log.d(TAG, "onCreate(): index = " + index);
                mAnswerCheatFlag.add(index, false);
            }
        }

        // 检查并获取保存的值
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsCheater = savedInstanceState.getBoolean(KEY_IS_CHEATER, false);

            // 重新获取答案作弊列表
            boolean[] myarray = savedInstanceState.getBooleanArray(KEY_ANSWER_CHEAT_FLAG);
            for (int i = 0; i < mAnswerCheatFlag.size()-1; i++) {
                mAnswerCheatFlag.set(i, myarray[i]);
            }
        }

        Log.i(TAG, "onCreate(): mCurrentIndex = " + mCurrentIndex + ", mIsCheater = " + mIsCheater);
        updateQuestion();

        // 单击问题直接显示下一题
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false; // 每次更新问题时，都需要更新这个值
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

        // 上一题
        mLastButton = (ImageButton) findViewById(R.id.last_button);
        mLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这里 mQuestionBank.length - 1 是因为数组下标的上限就是数组长度减1
                mCurrentIndex = (mCurrentIndex == 0) ? (mQuestionBank.length-1) : (mCurrentIndex - 1);
                mIsCheater = false; // 每次更新问题时，都需要更新这个值
                updateQuestion();
            }
        });

        // 下一题
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false; // 每次更新问题时，都需要更新这个值
                updateQuestion();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CheatActivity
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                // startActivity(intent); // 没有期待返回值
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });
    }

    /**
     * 解析从其他Activity返回来的标志
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
            mAnswerCheatFlag.set(mCurrentIndex, mIsCheater);
        }
        Log.d(TAG, "onActivityResult(): mIsCheater = " + mIsCheater);
    }

    /**
     * device configuration 发生改变时，保存某些标志
     * @param savedInstanceState
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState(): mCurrentIndex = " + mCurrentIndex + ", mIsCheater = " + mIsCheater);

        // 将mCurrentIndex变量值保存到 bundle 中
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);

        savedInstanceState.putBoolean(KEY_IS_CHEATER, mIsCheater);

        // 保存答案作弊列表
        boolean[] myarray = new boolean[mAnswerCheatFlag.size()];
        for (int i = 0; i < mAnswerCheatFlag.size()-1; i++) {
            myarray[i] = mAnswerCheatFlag.get(i);
        }
        savedInstanceState.putBooleanArray(KEY_ANSWER_CHEAT_FLAG, myarray);
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

        Log.d(TAG, "checkAnswer(): mCurrentIndex = " + mCurrentIndex);
        // 从答案作弊列列表中检查用户曾经是否作弊
        mIsCheater = mAnswerCheatFlag.get(mCurrentIndex);

        if (mIsCheater) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
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
