package com.lintex9527.custom;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.lintex9527.runoob.IPerson;

/**
 *
 *
 * http://www.runoob.com/w3cnote/android-tutorial-service-3.html
 *
 * 自定义我们的Service类，完成下述操作
 * 1、继承Service类，同时也定义一个PersonQueryBinder类，用来继承 IPerson.Stub类，也就是实现了 IPerson 接口和 IBinder 接口
 * 2、实例化自定义的 Stub 类，并重写 Service 的 onBind() 方法，返回一个 binder 对象
 */
public class AIDLService extends Service {

    private IBinder mIBinder = new PersonQueryBinder();
    private String[] names = {"B神", "艹神", "基神", "J神", "翔神"};

    private String query(int num) {
        if (num > 0 && num < 6) {
            return names[num - 1];
        }
        return null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder; // 这里必须返回IBinder对象
    }


    /**
     * 使用AIDL方式定义的服务，其中的Binder对象必须继承自 IPerson.Stub 即 IXXXX.Stub，这样同时继承了 IPerson 接口和 IBinder 接口
     */
    private final class PersonQueryBinder extends IPerson.Stub {

        @Override
        public String queryPerson(int num) throws RemoteException {
            return query(num);
        }
    }
}