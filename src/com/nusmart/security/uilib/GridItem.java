package com.nusmart.security.uilib;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;

public class GridItem extends LinearLayout {

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize =  MeasureSpec.getSize(widthMeasureSpec);

        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize =  MeasureSpec.getSize(heightMeasureSpec);
	}

	public GridItem(Context context) {
		super(context);
	}
	
	

}
