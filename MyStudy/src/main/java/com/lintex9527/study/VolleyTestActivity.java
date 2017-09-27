package com.lintex9527.study;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleyTestActivity extends Activity {

	private ImageView imageView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volley_test);
		
		init();
		loadImageVolley();
	}
	
	public void init(){
		imageView = (ImageView) findViewById(R.id.imageview_volley);
	}


	public void loadImageVolley(){
		
		String imageurl = "http://mm.chinasareview.com/wp-content/uploads/2017a/07/03/01.jpg";
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		
		final LruCache<String, Bitmap> lruCache = new LruCache<>(20);
		ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
			
			@Override
			public void putBitmap(String key, Bitmap value) {
				lruCache.put(key, value);
			}
			
			@Override
			public Bitmap getBitmap(String key) {
				return lruCache.get(key);
			}
		};

		ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
		ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, R.drawable.m01, R.drawable.m02);
		imageLoader.get(imageurl, listener);
	
	}
}
