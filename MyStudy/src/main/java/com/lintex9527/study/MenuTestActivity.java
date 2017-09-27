/**
 * 
 */
package com.lintex9527.study;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Gallery.LayoutParams;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class MenuTestActivity extends Activity implements ViewFactory{

	private final static int MENU_ITEM_ID_1 = 0xAA000001;
	private final static int MENU_ITEM_ID_2 = 0xAA000002;
	private final static int MENU_ITEM_ID_3 = 0xAA000003;
	private final static int MENU_ITEM_ID_4 = 0xAA000004;
	private final static int MENU_ITEM_ID_5 = 0xAA000005;
	private final static int MENU_ITEM_ID_6 = 0xAA000006;
	
	private final static int CONTEXT_MENU_ITEM_ID_01 = 0xAB000001;
	private final static int CONTEXT_MENU_ITEM_ID_02 = 0xAB000002;
	private final static int CONTEXT_MENU_ITEM_ID_06 = 0xAB000006;
	
	private final static int CONTEXT_MENU_ITEM_ID_03 = 0xAB000003;
	private final static int CONTEXT_MENU_ITEM_ID_04 = 0xAB000004;
	
	private final static int CONTEXT_MENU_ITEM_ID_05 = 0xAB000005;

	private ImageSwitcher imageSlide = null;
	private Button btnImageSwitch = null;
	private Button btnText = null;
	private TextView tvText = null;
	
	int[] imageIDs = {
			R.drawable.m01,
			R.drawable.m02,
			R.drawable.m03,
			R.drawable.m04,
			R.drawable.m05,
			R.drawable.m06
	};
	static int index = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_test);
		
		
		imageSlide = (ImageSwitcher) findViewById(R.id.imageslide);
		imageSlide.setFactory(this);
		
		imageSlide.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
		imageSlide.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
		
		index = 0;
		btnImageSwitch = (Button) findViewById(R.id.btnImageSwitch);
		registerForContextMenu(btnImageSwitch);
		
		btnImageSwitch.setOnCreateContextMenuListener(this);
		
		
		tvText = (TextView) findViewById(R.id.tvText);
		
		btnText = (Button) findViewById(R.id.btnText);
		registerForContextMenu(btnText);
		 
		btnText.setOnCreateContextMenuListener(this);
	}

	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		
		if (v.getId() == btnImageSwitch.getId()){
			

			MenuItem item01 = menu.add(0, CONTEXT_MENU_ITEM_ID_01, 0, R.string.picLast);
			{
				item01.setIcon(R.drawable.m01);
				item01.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			}
			
			MenuItem item02 = menu.add(0, CONTEXT_MENU_ITEM_ID_02, 0, R.string.picNext);
			{
				item02.setIcon(R.drawable.m02);
				item02.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			}
			
			MenuItem item06 = menu.add(0, CONTEXT_MENU_ITEM_ID_06, 0, R.string.picRandom);
			{
				item06.setIcon(R.drawable.m02);
				item06.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			}
			
		} else if (v.getId() == btnText.getId()){


			MenuItem item03 = menu.add(0, CONTEXT_MENU_ITEM_ID_03, 0, R.string.changeColor);
			{
				item03.setIcon(R.drawable.m03);
				item03.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			}
			MenuItem item04 = menu.add(0, CONTEXT_MENU_ITEM_ID_04, 0, R.string.changeSize);
			{
				item04.setIcon(R.drawable.m04);
				item04.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			}			
		}
		
		MenuItem item05 = menu.add(0, CONTEXT_MENU_ITEM_ID_05, 0, R.string.sharedMenu);
		{
			item05.setIcon(R.drawable.m05);
			item05.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		}
		
	}




	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case CONTEXT_MENU_ITEM_ID_01:
			if (index == 0){
				index = imageIDs.length - 1;
			}
			imageSlide.setImageResource(imageIDs[index]);
			index --;
			break;
			
		case CONTEXT_MENU_ITEM_ID_02:
			if (index == imageIDs.length - 1){
				index = 0;
			}
			imageSlide.setImageResource(imageIDs[index]);
			index ++;
			break;

		case CONTEXT_MENU_ITEM_ID_03:
			int red = (int)(Math.random() * 255);
			int green = (int)(Math.random() * 255);
			int blue = (int)(Math.random() * 255);
			tvText.setBackgroundColor(Color.rgb(red, green, blue));
			break;
			
		case CONTEXT_MENU_ITEM_ID_04:
			int size = (int) (16 + (Math.random() * 10));
			tvText.setTextSize(size);
			break;			

		case CONTEXT_MENU_ITEM_ID_05:
			Toast.makeText(getBaseContext(), "shared menu", Toast.LENGTH_SHORT).show();
			break;
			
		case CONTEXT_MENU_ITEM_ID_06:
			index = (int)(Math.random() * (imageIDs.length));
			imageSlide.setImageResource(imageIDs[index]);
			Toast.makeText(getBaseContext(), getResources().getString(R.string.picRandom) + " : " + index, Toast.LENGTH_SHORT).show();
			break;			
			
		default:
			Toast.makeText(getBaseContext(), "no menu", Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		int groupId = 5;
		int order = Menu.CATEGORY_SECONDARY;
		MenuItem item01 = menu.add(groupId, MENU_ITEM_ID_1, order, "m01");
		item01.setEnabled(false);
		item01.setTitle("m01");
		item01.setIcon(imageIDs[0]);
		
		MenuItem item02 = menu.add(groupId, MENU_ITEM_ID_2, order, "m02");
		item02.setEnabled(true);
		item02.setTitle("M02");
		item02.setIcon(imageIDs[1]);
		
		groupId = 1;
		MenuItem item03 = menu.add(groupId, MENU_ITEM_ID_3, order, "m03");
		item03.setEnabled(false);
		item03.setTitle("M03");
		item03.setIcon(imageIDs[2]);
		
		MenuItem item04 = menu.add(groupId, MENU_ITEM_ID_4, order, "m04");
		item04.setEnabled(true);
		item04.setTitle("M04");
		item04.setIcon(imageIDs[3]);
		item04.setShortcut('1', 'a');
		item04.setVisible(false);

		groupId = 2;
		//order = Menu.CATEGORY_ALTERNATIVE;
		MenuItem item05 = menu.add(groupId, MENU_ITEM_ID_5, order, "m05");
		item05.setEnabled(true);
		item05.setTitle("M05");
		item05.setIcon(imageIDs[4]);
		item05.setCheckable(false);
		
		MenuItem item06 = menu.add(groupId, MENU_ITEM_ID_6, order, "m06");
		item06.setEnabled(true);
		item06.setTitle("M06");
		item06.setIcon(imageIDs[5]);
		item06.setCheckable(false);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case MENU_ITEM_ID_1:
			imageSlide.setImageResource(imageIDs[0]);
			break;
		case MENU_ITEM_ID_2:
			imageSlide.setImageResource(imageIDs[1]);
			break;
		case MENU_ITEM_ID_3:
			imageSlide.setImageResource(imageIDs[2]);
			break;
		case MENU_ITEM_ID_4:
			imageSlide.setImageResource(imageIDs[3]);
			break;
		case MENU_ITEM_ID_5:
			imageSlide.setImageResource(imageIDs[4]);
			break;
		case MENU_ITEM_ID_6:
			imageSlide.setImageResource(imageIDs[5]);
			break;

		default:
			Toast.makeText(getBaseContext(), "no image", Toast.LENGTH_SHORT).show();
			break;
		}
		
		return true;
	}


	@Override
	public View makeView() {
		ImageView imageView = new ImageView(this);
		imageView.setBackgroundColor(0xFF00FF00);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		return imageView;
	}
	
}
