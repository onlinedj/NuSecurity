package com.nusmart.security;

import android.app.Activity;
import android.os.Bundle;

public class NuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		NuApp.getInstance().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		NuApp.getInstance().removeActivity(this);
	}
	
}
