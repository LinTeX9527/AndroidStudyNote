package com.lintex9527.runoob;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * ContentProvider
 * http://www.runoob.com/w3cnote/android-tutorial-contentprovider.html
 *
 */
public class TestContentProviderActivity extends AppCompatActivity {

    private static final String TAG = TestContentProviderActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_content_provider);
    }


    public void readOtherAppInfo(View view) {
        switch (view.getId()) {
            case R.id.btn_read_sms:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 通过 ContentResolver 读取几条短信
                        ContentResolver resolver = getContentResolver();
                        Uri uri = Uri.parse("content://sms/");
                        Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
                        int count = 0;
                        if (cursor != null) {
                            while (cursor.moveToNext()) {
                                count++;
                                if (count >= 5){ // 最多只读取5条信息
                                    break;
                                }
                                String address = cursor.getString(0);
                                String date = cursor.getString(1);
                                String type = cursor.getString(2);
                                String body = cursor.getString(3);
                                Log.d(TAG, "地址：" + address);
                                Log.d(TAG, "时间：" + date);
                                Log.d(TAG, "类型：" + type);
                                Log.d(TAG, "内容：" + body);
                                Log.d(TAG, "===============================================");
                            }
                            cursor.close();
                        }
                    }
                }).start();
                break;

        }
    }
}
