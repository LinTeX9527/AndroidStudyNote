package com.lintex9527.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class TestPageActivity extends Activity {

	public final static String ITEM_DESCRIPTION = "DESCRIPTION";
	public final static String ITEM_ACTION = "ACTION";
	
	private List<HashMap<String, Object>> data = null;
	private static int index = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testpage);


		initData();

		String[] from = new String[]{ITEM_DESCRIPTION};
		int[] to = new int[]{R.id.tvDesc};

		ListView lvAllItem = (ListView) findViewById(R.id.lv_alltest);
		lvAllItem.setAdapter(new SimpleAdapter(this, data, R.layout.testpage_list_item, from, to));
		lvAllItem.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				startTestItem(position);
			}
		});
	}
	
	protected void initData(){
	
		data = new ArrayList<>();
		
		index = 0;
		
		addTestItem(R.string.test_autocomplete, "net.learn2develop.myuitest.AutoCompleteTextViewTestActivity");
		
		addTestItem(R.string.test_image, "net.learn2develop.myuitest.ImageTestActivity");

		addTestItem(R.string.test_listview, "net.learn2develop.myuitest.ListViewTestActivity");
		
		addTestItem(R.string.test_simpleadapter, "net.learn2develop.myuitest.SimpleAdapterTestActivity");
		
		addTestItem(R.string.test_asynctask, "com.lintex9527.network.AsyncTaskTestActivity");
		
		addTestItem(R.string.test_gallery, "net.learn2develop.myuitest.GalleryTestActivity");
		
		addTestItem(R.string.test_imageswitcher, "net.learn2develop.myuitest.ImageSwitcherTestActivity");
		
		addTestItem(R.string.test_menu, "net.learn2develop.myuitest.MenuTestActivity");
		
		addTestItem(R.string.test_gridview, "net.learn2develop.myuitest.GridViewTestActivity");
		
		addTestItem(R.string.test_volley, "com.lintex9527.network.VolleyTestActivity");
		
		addTestItem(R.string.test_audiorecorder, "com.lintex9527.recorderplayer.MyRecorderTestActivity");
		
		addTestItem(R.string.test_mediarecord, "com.lintex9527.recorderplayer.AudioRecordTestActivity");

		addTestItem(R.string.test_fragment, "com.lintex9527.study.FragmentTestActivity");
		
	}
	
	protected void addTestItem(int idDescription, String action){
		HashMap<String, Object> mapx = new HashMap<>();
		mapx.put(ITEM_DESCRIPTION, "" + (++index) +  ": " +getResources().getString(idDescription));
		mapx.put(ITEM_ACTION, action);
		data.add(mapx);
	}

	
	protected void startTestItem(int itemindex){
		if (itemindex >= 0 && itemindex < data.size()){
			startActivity(new Intent(data.get(itemindex).get(ITEM_ACTION).toString()));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_action_bar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_new:
			Toast.makeText(TestPageActivity.this, "not now", Toast.LENGTH_SHORT).show();
			break;

		case R.id.action_back:
			Toast.makeText(TestPageActivity.this, "not now", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.action_find:
			Toast.makeText(TestPageActivity.this, "to be continued", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.action_settings:
			Toast.makeText(TestPageActivity.this, "wait for it", Toast.LENGTH_SHORT).show();
			break;
			
		default:
			break;
		}
		return true;
	}
}
