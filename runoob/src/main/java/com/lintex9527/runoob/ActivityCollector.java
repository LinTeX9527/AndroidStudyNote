package com.lintex9527.runoob;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityCollector {

	private final static String TAG = ActivityCollector.class.getName();
	
	private static HashMap<Activity, String> activitySet = new HashMap<Activity, String>();

    /**
     * 空的构造函数
     */
    public ActivityCollector() {

    }

    /**
     * 向管理器中添加一个记录
     * @param activity 一个Activity对象
     * @param description 该Activity对象的描述性字符串
     */
    public static void addActivity(Activity activity, String description){
		if (activitySet.containsKey(activity)){
            Log.d(TAG, "addActivity(): alread exist: " + description);
			return;
		}
		
		activitySet.put(activity, description);
	}


    /**
     * 修改某个记录对应的描述性字符串
     * @param activity 一个Activity对象，如果该对象不在管理器中则添加进去
     * @param description 描述性字符串
     */
	public static void modifyDescription(Activity activity, String description){
		if (activitySet.containsKey(activity)){
			activitySet.remove(activity);
			activitySet.put(activity, description);
		} else {
			activitySet.put(activity, description);
		}
	}

    /**
     * 从管理器中删除一个Activity对象
     * @param activity 一个Activity对象
     */
	public static void removeActivity(Activity activity){
		if (activitySet.containsKey(activity)){
			activitySet.remove(activity);
		}
	}


    /**
     * 通过描述性语句查找对应的Activity对象，返回其对应的class
     * @param description 描述性语句
     * @return 成功则返回某个Activity对象的class；失败则返回null
     */
	public static Class<Activity> getActivity(String description){
		if (description != null){
			for (Activity activity: activitySet.keySet()){
				if (activitySet.get(activity) == description){
					return (Class<Activity>) activity.getClass();
				}
			}
		}
		return null;
	}


    /**
     * 通过管理器终结所有的Activity对象
     */
	public static void finishAll(){
		for (Activity activity: activitySet.keySet()){
			if (!activity.isFinishing()){
				activity.finish();
			}
		}
		activitySet.clear();
	}


    /**
     * 获取管理器中所有的记录的描述性语句
     * @return 字符串数组
     */
	public static ArrayList<String> getAllDescription(){
		return new ArrayList<String>(activitySet.values());
	}
}
