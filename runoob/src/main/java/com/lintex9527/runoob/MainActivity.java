package com.lintex9527.runoob;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 主页通过列表展示所有的案例项目。
 *
 * 和主页相关的几个类分别是：
 * ActivityCollector
 * BaseActivity
 *
 */

public class MainActivity extends AppCompatActivity {

    public final static String ITEM_DESCRIPTION = "DESCRIPTION";
    public final static String ITEM_ACTION = "ACTION";

    private List<HashMap<String, Object>> data = null;
    private static int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initData();

        String[] from = new String[]{ITEM_DESCRIPTION};
        int[] to = new int[]{R.id.tvDesc};

        ListView lvAllItem = (ListView) findViewById(R.id.lv_alltest);
        lvAllItem.setAdapter(new SimpleAdapter(this, data, R.layout.testpage_list_item, from, to));
        // 给ListView 添加分割线
        lvAllItem.setDivider(new ColorDrawable(Color.GRAY));
        lvAllItem.setDividerHeight(2);

        lvAllItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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


        addTestItem(R.string.gestureadd_attr, "com.lintex9527.runoob.TestGesture02Activity");
        addTestItem(R.string.gesturebasic_attr, "com.lintex9527.runoob.TestGesture01Activity");
        addTestItem(R.string.asynctask_attr, "com.lintex9527.runoob.TestAsyncTaskActivity");
        addTestItem(R.string.configuration_attr, "com.lintex9527.runoob.TestConfigurationActivity");
        addTestItem(R.string.multipointer_attr, "com.lintex9527.runoob.TestMultiPointerActivity");
        addTestItem(R.string.handler_attr, "com.lintex9527.runoob.TestHandlerActivity");
        addTestItem(R.string.androidevent_attr, "com.lintex9527.runoob.TestEventListenerActivity");
        addTestItem(R.string.menus_attr, "com.lintex9527.runoob.TestMenusActivity");
        addTestItem(R.string.popupwindow_attr, "com.lintex9527.runoob.TestPopupWindowActivity");
        addTestItem(R.string.alertdialog_attr, "com.lintex9527.runoob.TestAlertDialogActivity");
        addTestItem(R.string.miscdialog_attr, "com.lintex9527.runoob.MiscDialogActivity");
        addTestItem(R.string.notification_attr, "com.lintex9527.runoob.TestNotificationActivity");
        addTestItem(R.string.expandablelistview_attr, "com.lintex9527.runoob.TestExpandableListViewActivity");
        addTestItem(R.string.viewflipper_static_attr, "com.lintex9527.runoob.TestViewFlipperActivity");
        addTestItem(R.string.viewflipper_dynamic_attr, "com.lintex9527.runoob.TestViewFlipper2Activity");
        addTestItem(R.string.textview_attr, "com.lintex9527.runoob.TestTextViewActivity");
        addTestItem(R.string.edittext_attr, "com.lintex9527.runoob.TestEditTextActivity");
        addTestItem(R.string.button_attr, "com.lintex9527.runoob.TestButtonActivity");
        addTestItem(R.string.imageview_attr, "com.lintex9527.runoob.TestImageViewActivity");
        addTestItem(R.string.radiobuton_attr, "com.lintex9527.runoob.TestRadioButtonActivity");
        addTestItem(R.string.togglebutton_attr, "com.lintex9527.runoob.TestToggleButtonActivity");
        addTestItem(R.string.progressbar_attr, "com.lintex9527.runoob.TestProgressBarActivity");
        addTestItem(R.string.seekbar_attr, "com.lintex9527.runoob.TestSeekBarActivity");
        addTestItem(R.string.ratingbar_attr, "com.lintex9527.runoob.TestRatingBarActivity");
        addTestItem(R.string.scrollview_attr, "com.lintex9527.runoob.TestScrollViewActivity");
        addTestItem(R.string.datetime_attr, "com.lintex9527.runoob.TestTextClockActivity");
        addTestItem(R.string.arrayadapter_attr, "com.lintex9527.runoob.TestArrayAdapterActivity");
        addTestItem(R.string.simpleadapter_attr, "com.lintex9527.runoob.TestSimpleAdapterActivity");
        addTestItem(R.string.baseadapter_attr, "com.lintex9527.runoob.TestBaseAdapterActivity");
        addTestItem(R.string.listviewupdate_attr, "com.lintex9527.runoob.TestListViewUpdateActivity");
        addTestItem(R.string.customadapter_attr, "com.lintex9527.runoob.TestCustomAdapterActivity");
        addTestItem(R.string.multilayoutadapter_attr, "com.lintex9527.runoob.TestMultiLayoutAdapterActivity");
        addTestItem(R.string.gridview_attr, "com.lintex9527.runoob.TestGridViewActivity");
        addTestItem(R.string.spinner_attr, "com.lintex9527.runoob.TestSpinnerActivity");
        addTestItem(R.string.autocompletetextview_attr, "com.lintex9527.runoob.TestAutoCompleteTextViewActivity");

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
}
