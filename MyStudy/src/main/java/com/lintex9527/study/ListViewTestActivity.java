package com.lintex9527.study;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewTestActivity extends BaseActivity {

	private ListView myListView = null;
	private String[] data = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActivityCollector.modifyDescription(ListViewTestActivity.this, getResources().getString(R.string.test_listview));
		
		setContentView(R.layout.activity_listview_test);
		
		initData();
		myListView = (ListView) findViewById(R.id.id_listview);
		
//		ArrayAdapter<String> myaArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data);
		ArrayAdapter<String> myaArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, data);
		myListView.setAdapter(myaArrayAdapter);
		myListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		
		
		myListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				TextView tempTextView = (TextView) view;
			}
		});
		
	}
	
	
	protected void initData(){
		data = new String[]{"Bruce Lee",
							"Jet Lee",
							"Jacky Chen",
							"Stephen Chow"};
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.removeActivity(ListViewTestActivity.this);
	}
	
	
	
}
