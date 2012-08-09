package com.nusmart.security;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class NuApp extends Application {

	protected static final String TAG = "NuSecurity";
	protected static final String NUSECURITY_CONFIG = "nusecurity_config";
	protected static final String PREF_SHOW_TIPS = "pref_show_tips";
	protected static final String PREF_SHOW_SPLASH = "pref_show_splash";
	private static NuApp sMe;
	private List<Activity> mActivityList = new ArrayList<Activity>(15);
	private Object mActivityLock = new Object();

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
