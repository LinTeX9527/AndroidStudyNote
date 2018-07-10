package com.lintex9527.runoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 *
 * RatingBar 基本使用
 * http://www.runoob.com/w3cnote/android-tutorial-ratingbar.html
 *
 */
public class TestRatingBarActivity extends AppCompatActivity {

    private RatingBar mRatingBar;
    private Button mButtonRandomRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rating_bar);

        initGUI();
    }

    private void initGUI(){
        mRatingBar = findViewById(R.id.id_ratingbar);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("当前值：" + rating);
                if (fromUser) {
                    stringBuilder.append(", 用户触发");
                } else {
                    stringBuilder.append(", 程序触发");
                }
                Toast.makeText(getBaseContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        mButtonRandomRating = findViewById(R.id.btnRandomRating);
        mButtonRandomRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float maxRating = mRatingBar.getMax();
                float value = (float) (Math.random() * maxRating);
                mRatingBar.setRating(value);
            }
        });

    }
}
