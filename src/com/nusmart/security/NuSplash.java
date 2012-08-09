package com.nusmart.security;

import static com.nusmart.security.NuApp.NUSECURITY_CONFIG;
import static com.nusmart.security.NuApp.PREF_SHOW_SPLASH;
import static com.nusmart.security.NuApp.PREF_SHOW_TIPS;
import static com.nusmart.security.NuApp.TAG;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class NuSplash extends NuActivity {

	private Handler mHandler = new Handler(Looper.myLooper());
	private Runnable mRunnable = new Runnable() {
		
		@Override
		public void run() {
			if (shouldShowTips()) {
				goToTipsActivity();
			} else {
				goToMainActivity();
			}
		}
	};
	
	private void goToTipsActivity() {
		startActivity(new Intent(this, NuTips.class));
		finish();
	}

	private void goToMainActivity() {
		startActivity(new Intent(this, NuMain.class));
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (shouldShowSplash()) {
			setContentView(R.layout.splash);
			createShortcut();
			getSharedPreferences(NUSECURITY_CONFIG, Context.MODE_PRIVATE)
					.edit().putBoolean(PREF_SHOW_SPLASH, false).apply();
		} else if (shouldShowTips()) {
			goToTipsActivity();
		} else {
			goToMainActivity();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mHandler.removeCallbacks(mRunnable);
		mHandler.postDelayed(mRunnable, 800);
	}

	private boolean shouldShowTips() {
		return getSharedPreferences(NUSECURITY_CONFIG, Context.MODE_PRIVATE)
				.getBoolean(PREF_SHOW_TIPS, true);
	}

	private boolean shouldShowSplash() {
		return getSharedPreferences(NUSECURITY_CONFIG, Context.MODE_PRIVATE)
				.getBoolean(PREF_SHOW_SPLASH, true);
	}

	private void createShortcut() {
		Intent shortcut = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
				getString(R.string.app_name));
		shortcut.putExtra("duplicate", false);
		ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(
				this, R.drawable.icon);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
		ComponentName name = new ComponentName(this, NuSplash.class);
		Log.d(TAG, "component:"+name.toString());
		Intent respondIntent = new Intent(Intent.ACTION_MAIN).setComponent(name);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, respondIntent);
		sendBroadcast(shortcut);
		// we don't need to show toast, because launcher had done the job.
		// Toast.makeText(this, R.string.shortcut_succ_tips, Toast.LENGTH_LONG)
		// .show();
	}

	/**
	 * Not used for launcher package name may have changed.
	 * 
	 * @deprecated
	 */
	private boolean hasShortcut() {
		boolean isInstallShortcut = false;
		final ContentResolver cr = getContentResolver();
		final String AUTHORITY = "com.android.launcher2.settings";
		final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
				+ "/favorites?notify=true");
		Cursor c = cr.query(CONTENT_URI, new String[] { "title" }, "title=?",
				new String[] { getString(R.string.app_name).trim() }, null);
		if (c != null && c.getCount() > 0) {
			isInstallShortcut = true;
		}
		return isInstallShortcut;
	}

}
