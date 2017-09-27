package com.lintex9527.study;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

/**
 * AutoCompleteTextView 测试
 *
 * 有一个AutoCompleteTextView 控件的数据源是一开始就完整的，另外一个是动态添加和删除的。
 */
public class AutoCompleteTextViewTestActivity extends BaseActivity {

	private static final String TAG = AutoCompleteTextViewTestActivity.class.getSimpleName();

	private Toast mToast;

	// 动态添加和删除的AutoCompleteTextView 的数据源
	String[] nameLibs = {"Aaron",
						"Abby",
						"Adam",
						"Baker",
						"Betty",
						"Bob",
						"Carl",
						"Cole",
						"Daisy",
						"Dale",
						"Dean",
						"Dodge",
						"Edward",
						"Eve",
						"Frank",
						"Franklin",
						"George",
						"Gill",
						"Hamlet",
						"Hill",
						"James",
						"Jane",
						"Jordan",
						"Kathy",
						"Lincoln",
						"Linda",
						"Lucia",
						"Mac",
						"Maria",
						"Norman",
						"Orlando",
						"Pete",
						"Peter",
						"Pike",
						"Robert",
						"Robin",
						"Roger",
						"Sam",
						"Shaw",
						"Sidney",
						"Taylor",
						"Toby",
						"Tommy",
						"Victoria",
						"Victor",
						"White",
						"William",
						"Xavier",
						"York",
						"Zoe"};
	List<String> nameList = new ArrayList<String>();
	int index = 0;
	AutoCompleteTextView autoComEditTextViewUserName = null;
	ArrayAdapter<String> userNameAdapter = null;
	Button btnAddNames = null;
	Button btnRemoveNames = null;
	
	String[] fruitNames = {"Almond",
							"Apple", 
							"Apricot", 
							"Bagasse",
							"Banana",
							"Bennet",
							"Berry", 
							"Black brin",
							"Bush fruit",
							"Cherry",
							"Core",
							"Custard apple",
							"Damson",
							"Date",
							"Durian",
							"Fig",
							"Filbert",
							"Flat peach",
							"Ginkgo",
							"Gooseberry",
							"Grape",
							"Haw",
							"Hickory",
							"Juicy peach",
							"Kernel fruit",
							"Kiwifruit",
							"Lemon",
							"Lichee",
							"Longan",
							"Loquat",
							"Lotus nut",
							"Mandarin",
							"Mango",
							"Marc",
							"Melon",
							"Nectarine",
							"Newton pippin",
							"Nucleus",
							"Olive",
							"Orange",
							"Papaya",
							"Peach",
							"Peanut",
							"Pear",
							"Phoenix eye nut",
							"Plum",
							"Quarenden",
							"Rambutan",
							"Segment",
							"Sorgo",
							"Strawberry",
							"Sweet acorn",
							"Tangerine",
							"Tangor",
							"Teazle fruit",
							"Vermillion orange",
							"Walnut",
							"Water Caltrop",
							"Wild peach"};
	AutoCompleteTextView editTextFruit = null;
	ArrayAdapter<String> fruitAdapter = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_autocompletetextview_test);

		ActivityCollector.modifyDescription(this, getResources().getString(R.string.test_autocomplete));

		mToast = Toast.makeText(getBaseContext(), "", Toast.LENGTH_SHORT);

        editTextFruit = (AutoCompleteTextView) findViewById(R.id.editFruits);
        fruitAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, fruitNames);
        editTextFruit.setAdapter(fruitAdapter);
        editTextFruit.setThreshold(0);

		/**
		 * 经过测试，对于AutoCompleteTextView 控件的数据源，最后直接在它对应的adapter 上直接操作，例如下面的
		 * 按钮事件中，直接用 userNameAdapter.add() 和 userNameAdapter.remove() 移除对象，然后再
		 * userNameAdapter.notifyDataSetChanged() 就可以实时更新 ListView 的UI 显示。
		 */
		autoComEditTextViewUserName = (AutoCompleteTextView) findViewById(R.id.autoTextView);
        userNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, nameList);
        autoComEditTextViewUserName.setAdapter(userNameAdapter);
        autoComEditTextViewUserName.setThreshold(0);
        btnAddNames = (Button) findViewById(R.id.btnAddNames);
        btnAddNames.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (index < nameLibs.length){
					//nameList.add(nameLibs[index]);
					userNameAdapter.add(nameLibs[index]);
					userNameAdapter.notifyDataSetChanged();
					Log.d(TAG, String.format("Adding: index = %d, name = %s", index, nameLibs[index]));
					showTips("Add ok: " + nameLibs[index]);
					index ++;
				} else {
					showTips("Add Failed: " + nameLibs[index]);
				}
			}
		});

        
        btnRemoveNames = (Button) findViewById(R.id.btnRemoveNames);
        btnRemoveNames.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (index <= 0) {
					showTips("List is already empty");
				} else {
					index --;
					if (userNameAdapter.getItem(index).equals(nameLibs[index])){
						Log.d(TAG, String.format("deleting: index = %d, name = %s", index, nameLibs[index]));
						//nameList.remove(index);
						userNameAdapter.remove(nameLibs[index]);
						userNameAdapter.notifyDataSetChanged();
						showTips("Delete OK: " + nameLibs[index]);
					}
				}
			}
		});

    }


	/**
	 * 弹出提示消息
	 * @param msg 消息内容
	 */
	private void showTips(String msg){
		mToast.setText(msg);
		mToast.setDuration(Toast.LENGTH_SHORT);
		mToast.show();
	}


}
