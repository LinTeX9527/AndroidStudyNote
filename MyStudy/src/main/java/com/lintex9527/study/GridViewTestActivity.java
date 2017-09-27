/**
 * 
 */
package com.lintex9527.study;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/**
 * http://blog.csdn.net/jiangliloveyou/article/details/11218555
 * 
 * https://developer.android.com/guide/topics/media/mediarecorder.html
 * 
 * @author LinTeX9527
 *
 */
public class GridViewTestActivity extends Activity {


	Integer[] imageIDs = {
			R.drawable.m01,
			R.drawable.m02,
			R.drawable.m03,
			R.drawable.m04,
			R.drawable.m05,
			R.drawable.m06,
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gridview_test);
		
		GridView gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(new ImageAdapter(this));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getBaseContext(), "click: " + position, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	
	public class ImageAdapter extends BaseAdapter{
		private Context context;
		
		public ImageAdapter(Context c){
			context = c;
		}

		@Override
		public int getCount() {
			return imageIDs.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			
			if (convertView == null){
				
				Display display = getWindowManager().getDefaultDisplay();
				@SuppressWarnings("deprecation")
				int width = display.getWidth() / 4;
				
				imageView = new ImageView(context);
				imageView.setLayoutParams(new GridView.LayoutParams(width, width));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(5, 5, 5, 5);
			} else {
				imageView = (ImageView) convertView;
			}
			
			imageView.setImageResource(imageIDs[position]);
			
			return imageView;
		}
	} // end of class ImageAdapter
	
}
