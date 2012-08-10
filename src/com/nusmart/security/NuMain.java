package com.nusmart.security;

import static com.nusmart.security.NuApp.NUSECURITY_CONFIG;
import static com.nusmart.security.NuApp.PREF_USE_CUSTOM_GRID;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class NuMain extends NuActivity {

	private GridView mGridBar;
	private GridView mGrid;
	private List<GridInfo> mDefaultGrids1 = new ArrayList<GridInfo>(4);
	private List<GridInfo> mDefaultGrids2 = new ArrayList<GridInfo>(4);

	private void initGrids() {
		GridInfo info1 = new GridInfo(1, R.drawable.main_icon_antivirus, R.string.virus_scan);
		mDefaultGrids1.add(info1);
		GridInfo info2 = new GridInfo(2, R.drawable.main_icon_antivirus, R.string.virus_scan);
		mDefaultGrids1.add(info2);
		GridInfo info3 = new GridInfo(3, R.drawable.main_icon_antivirus, R.string.virus_scan);
		mDefaultGrids1.add(info3);
		GridInfo info4 = new GridInfo(4, R.drawable.main_icon_antivirus, R.string.virus_scan);
		mDefaultGrids1.add(info4);
		
		GridInfo info5 = new GridInfo(5, R.drawable.main_icon_antivirus, R.string.virus_scan);
		mDefaultGrids2.add(info5);
		GridInfo info6 = new GridInfo(6, R.drawable.main_icon_antivirus, R.string.virus_scan);
		mDefaultGrids2.add(info6);
		GridInfo info7 = new GridInfo(7, R.drawable.main_icon_antivirus, R.string.virus_scan);
		mDefaultGrids2.add(info7);
		GridInfo info8 = new GridInfo(8, R.drawable.main_icon_antivirus, R.string.virus_scan);
		mDefaultGrids2.add(info8);
	}

	private boolean useCustomGrid() {
		return getSharedPreferences(NUSECURITY_CONFIG, MODE_PRIVATE)
				.getBoolean(PREF_USE_CUSTOM_GRID, false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		mGridBar = (GridView) findViewById(R.id.grid_bar);
		mGrid = (GridView) findViewById(R.id.grid);
		initGrids();
		MyAdapter adapter1 = new MyAdapter(mDefaultGrids1, getLayoutInflater(), R.layout.item_grid);
		MyAdapter adapter2 = new MyAdapter(mDefaultGrids2, getLayoutInflater(), R.layout.item_grid);
		mGridBar.setAdapter(adapter1);
		mGrid.setAdapter(adapter2);
	}

	class MyAdapter extends BaseAdapter {

		private List<GridInfo> mGrids;
		private LayoutInflater mInflater;
		private int mItemLayout;

		public MyAdapter(List<GridInfo> grids, LayoutInflater inflater, int layouId) {
			mGrids = grids;
			mInflater = inflater;
			mItemLayout = layouId;
		}

		@Override
		public int getCount() {
			return mGrids.size();
		}

		@Override
		public Object getItem(int position) {
			return mGrids.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null) {
				convertView = mInflater.inflate(mItemLayout, null);
			}
			GridInfo info = mGrids.get(position);
			ImageView img = (ImageView) convertView.findViewById(R.id.item_image);
			img.setImageResource(info.drawableId);
			TextView text = (TextView) convertView.findViewById(R.id.item_text);
			text.setText(info.textId);
			return convertView;
		}

	}
	
	class GridInfo {
		int id;
		int drawableId;
		int textId;
		public GridInfo(int id, int drawableId, int textId) {
			this.id = id;
			this.drawableId = drawableId;
			this.textId = textId;
		}
	}

}
