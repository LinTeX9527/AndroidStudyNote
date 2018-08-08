package com.lintex9527.runoob;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lintex9527.custom.ExamBinderService;

public class TestBinderServiceActivity extends AppCompatActivity {

    private static final String TAG = TestBinderServiceActivity.class.getSimpleName();

    private Button btnBind = null;
    private Button btnUnbind = null;
    private Button btnGetStatus = null;

    // 是否绑定了服务，这个状态需要手工标记，不然会出现 fatal exception
    private boolean isServiceBinded = false;

    ExamBinderService.MyBinder mMyBinder = null;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "绑定服务成功  -- " + name.getClassName());
            isServiceBinded = true;
            mMyBinder = (ExamBinderService.MyBinder) service;
        }

        /**
         * 只有当服务的进程挂掉了才能探测到服务丢失，从而调用这个回调方法。
         * 而主动 unbindService() 是不会触发这个回调方法的。
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "与服务解除了绑定  -- " + name.getClassName());
            mMyBinder = null;
            isServiceBinded = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_binder_service);

        initViews();
    }

    private void initViews() {
        btnBind = (Button) findViewById(R.id.btn_bind);
        btnUnbind = (Button) findViewById(R.id.btn_unbind);
        btnGetStatus = (Button) findViewById(R.id.btn_get_status);

        final Intent intent = new Intent(this, ExamBinderService.class);

        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, conn, Service.BIND_AUTO_CREATE);
            }
        });

        btnUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServiceBinded) {
                    isServiceBinded = false;
                    unbindService(conn);
                } else {
                    Toast.makeText(getBaseContext(), "还未绑定服务", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnGetStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMyBinder != null) {
                    Log.d(TAG, "Service 的 count 的值为 = " + mMyBinder.getCount());
                    Toast.makeText(getBaseContext(), "Service 的 count 的值为 = " + mMyBinder.getCount(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "请先绑定服务");
                    Toast.makeText(getBaseContext(), "请先绑定服务", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 做清理工作，如果不在这里解除绑定，会产生异常
        if (isServiceBinded) {
            unbindService(conn);
        }
    }
}
