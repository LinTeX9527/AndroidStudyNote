/**
 * 
 */
package com.lintex9527.study;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ImageTestActivity extends BaseActivity {

	
	ImageView imageView = null;
	static int count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_test);
	
		ActivityCollector.modifyDescription(this, getResources().getString(R.string.test_image));
		
		imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setBackgroundColor(Color.GREEN);
		imageView.setPadding(33, 43, 33, 53);
		imageView.setCropToPadding(true);
		imageView.setImageResource(R.drawable.pandorabox);
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				count ++;
				if (count >= 2){
					count = 0;
				}
				if (count % 2 == 0){
					imageView.setImageResource(R.drawable.pandorabox);
				} else {
					imageView.setImageResource(R.drawable.ic_launcher);
				}
			}
		});
	}

}
