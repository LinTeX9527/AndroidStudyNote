package com.lintex9527.runoob;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lintex9527.custom.AIDLService;

/**
 * AIDL 服务
 * http://www.runoob.com/w3cnote/android-tutorial-service-3.html
 *
 * 第一步：新建一个AIDL文件，例如这里的 IPerson.aidl 文件，在里面自定义了一个接口方法；
 *
 * 第二步：自定义AIDLService 类，完成下面的两个步骤：
 * 1、继承Service类，同时也制定一个 PersonQueryBinder 类，用来继承 IPerson.Stub 类，即实现了 IPerson 接口和 IBinder 接口；
 * 2、实例化自定义的 Stub 类，并重写 Service.onBind() 方法，返回一个 binder 对象
 *
 * 第三步：
 * 在 AndroidManifest.xml 文件中注册 Service
 *
 * 第四步：
 * 在客户端即这个Activity 中完成以下步骤：
 * 1、自定义 PersonConnection 类实现 ServiceConnection 接口；
 * 2、以PersonConnection 对象作为参数，调用 bindService() 绑定方式启动服务
 * 3、和本地Service不同，绑定远程Service的ServiceConnection并不能直接获取Service 的 onBind() 方法返回的 IBinder 对象，
 * 只能返回 onBind() 方法说返回的代理对象，必须要使用下面的处理方式：
 * mIPerson = IPerson.Stub.asInterface(service);
 */
public class TestAIDLServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = TestAIDLServiceActivity.class.getSimpleName();

    private EditText edit_num = null;
    private Button btn_query = null;
    private TextView txt_name = null;
    private IPerson mIPerson = null;
    private PersonConnection conn = new PersonConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_aidlservice);

        initViews();

        // 绑定远程Service
        Intent intent = new Intent(this, AIDLService.class);
        intent.setPackage("com.lintex9527.runoob");

        bindService(intent, conn, BIND_AUTO_CREATE);
        btn_query.setOnClickListener(this);
    }

    private void initViews() {
        edit_num = (EditText) findViewById(R.id.edit_num);
        btn_query = (Button) findViewById(R.id.btn_query);
        txt_name = (TextView) findViewById(R.id.txt_name);
    }

    @Override
    public void onClick(View v) {
        String number = edit_num.getText().toString();
        if (number.equals("") || number.equals(null)) {
            number = "0";
        }
        int num = Integer.valueOf(number);

        Log.d(TAG, "转换后的数值 = " + num);

        try {
            txt_name.setText(mIPerson.queryPerson(num));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        edit_num.setText("");
    }


    private final class PersonConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIPerson = IPerson.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIPerson = null;
        }

        @Override
        public void onBindingDied(ComponentName name) {

        }
    }
}
