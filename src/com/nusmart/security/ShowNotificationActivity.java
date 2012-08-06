package com.nusmart.security;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ShowNotificationActivity extends NuActivity implements OnClickListener{
    /** Called when the activity is first created. */
	Button mNext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mNext = (Button) findViewById(R.id.next);
//        mNext.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
    
//    @Override
//    protected void onResume() {
//    	super.onResume();
//    	NotificationUtils.cleanNotification(this);
//    }
//    
//    @Override
//    protected void onStop() {
//    	super.onStop();
//    	ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//    	List<RunningAppProcessInfo> apps = am.getRunningAppProcesses();
//    	boolean show = true;
//    	for(RunningAppProcessInfo info : apps) {
//    		if(info.processName.equals(this.getPackageName())) {
//    			Toast.makeText(this, "importance="+info.importance, 2000).show();
//    			if(info.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
//    				show = false;
//    			}
//    			if(info.importance == RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
//    				show = false;
//    			}
//    		}
//    	}
//    	
//    	if(show) {
//    		NotificationUtils.showNotification(this);
//    	}
//    }
//    
//    @Override
//    public void onBackPressed() {
//    	super.onBackPressed();
//    	showExitDialog();
//    }
//    
//    private void showExitDialog() {
//    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
//    	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				switch (which) {
//				case -1:
//					Process.killProcess(Process.myPid());
//					break;
//
//				default:
//					break;
//				}
//			}
//		};
//    	builder.setNegativeButton("cancel", listener);
//    	builder.setPositiveButton("exit", listener);
//    	AlertDialog dialog = builder.create();
//    	dialog.show();
//    }
//    
//	@Override
//	public void onClick(View v) {
//		int id = v.getId();
//		switch (id) {
//		case R.id.next:
//			startNext();
//			break;
//
//		default:
//			break;
//		}
//	}
//	
//	private void startNext() {
//		//Intent action = new Intent(this, NextActivity.class);
//		startActivity(action);
//	}
}