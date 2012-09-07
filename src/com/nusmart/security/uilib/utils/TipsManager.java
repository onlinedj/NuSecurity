package com.nusmart.security.uilib.utils;

import com.nusmart.security.NuApp;
import com.nusmart.security.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;

public class TipsManager {

	private static TipsManager mTipsManager;
	private static PopupWindow mPopup;

	public static TipsManager instance(Context context) {
		if (mPopup == null) {
			mPopup = new PopupWindow(context);
		}
		if (mTipsManager == null) {
			mTipsManager = new TipsManager();
		}
		return mTipsManager;
	}

	public void showAsDropDown(View parent, View contentView, int width,
			int height, int x, int y, PopupWindow.OnDismissListener listener) {
		contentView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				NuApp.logd("event.action=" + event.getAction());
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					mPopup.dismiss();
				}
				return false;
			}
		});
		mPopup.setContentView(contentView);
		mPopup.setWidth(width);
		mPopup.setHeight(height);
		mPopup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		mPopup.setFocusable(true);
		mPopup.showAsDropDown(parent, x, y);
		if(listener != null) {
			mPopup.setOnDismissListener(listener);
		}
		
	}

	public void showAtLocation(View parent, View contentView, int width,
			int height, int gravity, int x, int y) {
		contentView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				NuApp.logd("event.action=" + event.getAction());
				if (v.getId() == R.id.bubble_tip
						&& event.getAction() == MotionEvent.ACTION_DOWN) {
					mPopup.dismiss();
				}
				return false;
			}
		});
		mPopup.setContentView(contentView);
		mPopup.setWidth(width);
		mPopup.setHeight(height);
		mPopup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		mPopup.setFocusable(true);
		mPopup.showAtLocation(parent, gravity, x, y);
	}

	public void dismissTips() {
		mPopup.dismiss();
	}

}
