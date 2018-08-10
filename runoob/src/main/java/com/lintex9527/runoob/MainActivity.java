package com.lintex9527.runoob;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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
 * 2018/8/10 16:56 添加了动态权限申请
 */

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public final static String ITEM_DESCRIPTION = "DESCRIPTION";
    public final static String ITEM_ACTION = "ACTION";

    private List<HashMap<String, Object>> data = null;
    private static int index = 0;

    // 动态申请的权限列表
    private static final String[] REQUIRED_PERMISSION_LIST = new String[] {
            Manifest.permission.READ_SMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    // 保存系统默认没有许可的权限
    private List<String> missingPermissions = new ArrayList<>();
    // 动态申请权限的申请码
    private static final int REQUEST_PERMISSION_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 立刻开始申请权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkAndRequestPermissions();
        }
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


    /**
     * 主动检查权限是否许可，未许可的话就直接动态申请
     */
    private void checkAndRequestPermissions() {
        // 先动态检查一遍权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String eachPermission : REQUIRED_PERMISSION_LIST) {
                if (checkSelfPermission(eachPermission) == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "权限 " + eachPermission + "已满足");
                } else {
                    Log.d(TAG, "权限 " + eachPermission + "需要动态申请");
                    missingPermissions.add(eachPermission);
                }
            }
        }
        // 在来动态申请权限
        if (missingPermissions.isEmpty()) {
            Log.d(TAG, "所有需要的权限以满足，无需动态申请");
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    missingPermissions.toArray(new String[missingPermissions.size()]),
                    REQUEST_PERMISSION_CODE);
        }
    }


    /**
     * 检查动态申请权限的结果
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = permissions.length-1; i >= 0; i--) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "动态申请此权限" + permissions[i] + "已许可");
                    missingPermissions.remove(permissions[i]);
                }
            }

            if (missingPermissions.isEmpty()) {
                Log.d(TAG, "所有的权限已许可");
            } else {
                // 列出所有未许可的权限
                Log.d(TAG, "如下权限未满足：\n" + missingPermissions.toString());
                Toast.makeText(getBaseContext(), "如下权限未满足：\n" + missingPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void initData(){

        data = new ArrayList<>();

        index = 0;

        addTestItem(R.string.contentprovider_attr, "com.lintex9527.runoob.TestContentProviderActivity");
        addTestItem(R.string.broadcast_attr, "com.lintex9527.runoob.TestBroadcastReceiverActivity");
        addTestItem(R.string.aidl_server_attr, "com.lintex9527.TestAIDLServiceActivity");
        addTestItem(R.string.bindservice_attr, "com.lintex9527.runoob.TestBinderServiceActivity");
        addTestItem(R.string.service_play_music_attr, "com.lintex9527.runoob.TestServicePlayerActivity");
        addTestItem(R.string.servicebasic_attr, "com.lintex9527.runoob.TestServiceActivity");
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
