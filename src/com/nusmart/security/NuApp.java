package com.nusmart.security;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

public class NuApp extends Application {

	public static final String TAG = "NuSecurity";
	public static final boolean DEBUG = true;
	protected static final String NUSECURITY_CONFIG = "nusecurity_config";
	protected static final String PREF_SHOW_TIPS = "pref_show_tips";
	protected static final String PREF_SHOW_SPLASH = "pref_show_splash";
	protected static final String PREF_USE_CUSTOM_GRID = "pref_use_custom_grid";
	private static NuApp sMe;
	private List<Activity> mActivityList = new ArrayList<Activity>(15);
	private Object mActivityLock = new Object();
	
	public static void logd(String msg) {
		if(DEBUG) Log.d(TAG, msg);
	}

	public NuApp() {
		sMe = this;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}

	public static NuApp getInstance() {
		return sMe;
	}

	public void addActivity(Activity activity) {
		synchronized (mActivityLock) {
			mActivityList.add(activity);
		}
	}

	public void removeActivity(Activity activity) {
		synchronized (mActivityLock) {
			mActivityList.remove(activity);
		}
	}

	public void endAllActivities() {
		synchronized (mActivityLock) {
			for (Activity a : mActivityList) {
				if (a != null) {
					a.finish();
				}
			}
			System.exit(0);
		}
	}
}
