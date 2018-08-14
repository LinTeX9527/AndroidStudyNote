package com.lintex9527.android.sqlitedemo01;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by lintex9527@yeah.net on 2018/8/14 15:49
 *
 * Android SQLite 数据库操作，很不错的例程：
 * https://www.journaldev.com/9438/android-sqlite-database-example-tutorial
 *
 */
public class CountryListActivity extends AppCompatActivity{

    private static final String TAG = CountryListActivity.class.getSimpleName();

    private DBManager mDBManager;
    private ListView mListView;
    private SimpleCursorAdapter mAdapter;

    final String[] from = new String[]{DatabaseOpenHelper._ID,
                                    DatabaseOpenHelper.SUBJECT,
                                    DatabaseOpenHelper.DESC};

    final int[] to  = new int[] {R.id.id, R.id.title, R.id.desc};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() 活动开始建立");
        setContentView(R.layout.fragment_emp_list);

        mDBManager = new DBManager(this);
        mDBManager.open();
        Cursor cursor = mDBManager.fetch();

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setEmptyView(findViewById(R.id.empty));

        mAdapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        mAdapter.notifyDataSetChanged();
        mListView.setAdapter(mAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.title);
                TextView descTextView = (TextView) view.findViewById(R.id.desc);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();

                // 通过Intent传递数据
                Intent modify_intent = new Intent(getApplicationContext(), ModifyCountryActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);
                startActivity(modify_intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() 销毁活动");
        mDBManager.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        Log.d(TAG, "onCreateOptionsMenu() 新建选项菜单");
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_record) {
            Intent add_mem = new Intent(this, AddCountryActivity.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }
}
