package com.lintex9527.study;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.util.Log;

public class ActivityCollector {

	private final static String TAG_STRING = "ActivityCollector";
	
	public static HashMap<Activity, String> activitySet = new HashMap<Activity, String>();
	
	public static void addActivity(Activity activity, String description){
		if (activitySet.containsKey(activity)){
			return;
		}
		
		activitySet.put(activity, description);
	}

	
	public static void modifyDescription(Activity activity, String description){
		if (activitySet.containsKey(activity)){
			activitySet.remove(activity);
			activitySet.put(activity, description);
		} else {
			activitySet.put(activity, description);
		}
	}
	
	public static void removeActivity(Activity activity){
		if (activitySet.containsKey(activity)){
			activitySet.remove(activity);
		}
	}

	
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
	
	public static void finishAll(){
		for (Activity activity: activitySet.keySet()){
			if (!activity.isFinishing()){
				activity.finish();
			}
		}
		activitySet.clear();
	}
	
	public static ArrayList<String> getAllDescription(){
		return new ArrayList<String>(activitySet.values());
	}
}
