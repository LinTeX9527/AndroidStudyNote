package com.lintex9527.runoob;

import android.content.Context;
import android.content.DialogInterface;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * 添加手势
 * http://www.runoob.com/w3cnote/android-tutorial-gestures.html
 */
public class TestGesture02Activity extends AppCompatActivity {

    private static final String TAG = TestGesture02Activity.class.getName();

    private Context mContext;

    private EditText mEditText;
    private GestureOverlayView mGestureOverlayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_gesture02);
        mContext = TestGesture02Activity.this;

        initViews();
    }

    private void initViews() {
        mGestureOverlayView = (GestureOverlayView) findViewById(R.id.gesture);
        mGestureOverlayView.setGestureColor(Color.RED);
        mGestureOverlayView.setGestureStrokeWidth(5);
        mGestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, final Gesture gesture) {
                View saveDialog = getLayoutInflater().inflate(R.layout.dialog_save, null, false);
                ImageView image_show = (ImageView) saveDialog.findViewById(R.id.image_show);
                final EditText edit_name = (EditText) saveDialog.findViewById(R.id.edit_gesture_name);

                Bitmap bitmap = gesture.toBitmap(128, 128, 10, 0xffff0000);
                image_show.setImageBitmap(bitmap);

                new AlertDialog.Builder(mContext).setView(saveDialog)
                        .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 获取文件对应的手势库
                                String publicPath = Environment.getExternalStorageDirectory().getPath();
                                String savePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "mygestures";
                                Log.d(TAG, "公共存储路径：" + publicPath);
                                Log.d(TAG, "外部公共存储路径：" + savePath);
//                                GestureLibrary gestureLibrary = GestureLibraries.fromFile(publicPath + "/" + "mygestures");
                                GestureLibrary gestureLibrary = GestureLibraries.fromFile(savePath);
                                gestureLibrary.addGesture(edit_name.getText().toString(), gesture);
                                gestureLibrary.save(); // FIXME 无法访问外不能存储空间
                            }
                        }).setNegativeButton("取消", null)
                        .show();
            }
        });
    }
}
