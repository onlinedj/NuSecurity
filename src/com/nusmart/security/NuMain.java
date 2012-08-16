package com.nusmart.security;

import static com.nusmart.security.NuApp.NUSECURITY_CONFIG;
import static com.nusmart.security.NuApp.PREF_HAS_USED_OPTION_MENU;
import static com.nusmart.security.NuApp.PREF_USE_CUSTOM_GRID;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.nusmart.security.uilib.SlidingDrawer;

public class NuMain extends NuActivity {

	private SlidingDrawer mSlideDrawer;
	private ImageView mBackground;
	private GridView mGridBar;
	private GridView mGrid;
	private MyGridAdapter mGridBarAdapter;
	private MyGridAdapter mGridAdapter;
	private PopupWindow mPopupMenu;
	private ListView mPopupList;
	private ListView mTabs;
	private List<GridInfo> mDefaultGrids1 = new ArrayList<GridInfo>(4);
	private List<GridInfo> mDefaultGrids2 = new ArrayList<GridInfo>(4);
	private List<MenuInfo> mMainMenuList = new ArrayList<NuMain.MenuInfo>(5);
	private List<ListItemInfo> mTabList = new ArrayList<NuMain.ListItemInfo>(4);

	private View.OnClickListener mViewListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.option_icon_img:
				if (mPopupMenu.isShowing()) {
					hideMenu();
				} else {
					showMenu();
				}
				break;

			default:
				break;
			}
		}
	};

	private void initTabs() {
		ListItemInfo info1 = new ListItemInfo(1, R.drawable.home_network,
				R.string.wap_monitor, R.string.today_network_size);
		mTabList.add(info1);
		ListItemInfo info2 = new ListItemInfo(2, R.drawable.home_filter,
				R.string.filter_manager, R.string.hint_blocking_msg4);
		mTabList.add(info2);
		ListItemInfo info3 = new ListItemInfo(3, R.drawable.home_accelerate,
				R.string.phone_faster, R.string.memory_free_only);
		mTabList.add(info3);
		ListItemInfo info4 = new ListItemInfo(4, R.drawable.home_battery,
				R.string.battery_manager, R.string.battery_save_close_low);
		mTabList.add(info4);
		mTabs = (ListView) findViewById(R.id.item_tab_list);
		mTabs.setAdapter(new MyTabListAdapter(mTabList, getLayoutInflater(),
				R.layout.item_list_tab));
	}

	private void showMenu() {
		mPopupMenu.showAsDropDown(findViewById(R.id.option_icon_img));
		if (!hasUsedOption()) {
			findViewById(R.id.option_new).setVisibility(View.GONE);
			getSharedPreferences(NUSECURITY_CONFIG, MODE_PRIVATE).edit()
					.putBoolean(PREF_HAS_USED_OPTION_MENU, true).apply();
		}
		NuApp.logd("popupList:" + mPopupList.getMeasuredWidth());
	}

	private void hideMenu() {
		mPopupMenu.dismiss();
	}

	private void initMenu() {
		MenuInfo mi1 = new MenuInfo(1, R.drawable.setting_poplist_icon,
				R.string.system_setting, false);
		mMainMenuList.add(mi1);
		MenuInfo mi2 = new MenuInfo(2, R.drawable.feedback_poplist_icon,
				R.string.FAN_KUI, false);
		mMainMenuList.add(mi2);
		MenuInfo mi3 = new MenuInfo(3, R.drawable.update_poplist_icon,
				R.string.JIAN_CHA_GENG_XIN, false);
		mMainMenuList.add(mi3);
		MenuInfo mi4 = new MenuInfo(4, R.drawable.about_poplist_icon,
				R.string.GUAN_YU_XIN_XI, false);
		mMainMenuList.add(mi4);
		MenuInfo mi5 = new MenuInfo(5, R.drawable.activities_poplist_icon,
				R.string.operating_activies, true);
		mMainMenuList.add(mi5);
		mPopupList = (ListView) getLayoutInflater().inflate(
				R.layout.layout_poplistview, null);
		mPopupList.setAdapter(new MyMenuAdapter(mMainMenuList,
				getLayoutInflater(), R.layout.item_poplistview));
		mPopupMenu = new PopupWindow(mPopupList, 260, LayoutParams.WRAP_CONTENT);// TODO
																					// change
		mPopupMenu.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.poplistview_bg));
		mPopupMenu.setFocusable(true);
		mPopupMenu.setOutsideTouchable(true);
		mPopupList.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				switch (keyCode) {
				case KeyEvent.KEYCODE_MENU:
					if (event.getAction() == KeyEvent.ACTION_DOWN) {
						if (mPopupMenu.isShowing()) {
							hideMenu();
						} else {
							showMenu();
						}
						return true;
					}

				default:
					return false;
				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:
			if (mPopupMenu.isShowing()) {
				hideMenu();
			} else {
				showMenu();
			}
			return true;

		default:
			return super.onKeyDown(keyCode, event);
		}
	}

	private boolean hasUsedOption() {
		return getSharedPreferences(NUSECURITY_CONFIG, MODE_PRIVATE)
				.getBoolean(PREF_HAS_USED_OPTION_MENU, false);
	}

	@Override
	public void onBackPressed() {
		if (mPopupMenu.isShowing()) {
			mPopupMenu.dismiss();
		} else if (mSlideDrawer.isOpened()) {
			mSlideDrawer.animateClose();
		} else {
			super.onBackPressed();
		}
	}

	private List<GridInfo>[] loadCustomGrids() {
		return null;
	}

	private void initGrids() {
		GridInfo info1 = new GridInfo(1, R.drawable.main_icon_antivirus,
				R.string.virus_scan);
		mDefaultGrids1.add(info1);
		GridInfo info2 = new GridInfo(2, R.drawable.main_icon_private,
				R.string.private_protect);
		mDefaultGrids1.add(info2);
		GridInfo info3 = new GridInfo(3, R.drawable.main_icon_mysoftware,
				R.string.my_software);
		mDefaultGrids1.add(info3);
		GridInfo info4 = new GridInfo(4, R.drawable.main_icon_software,
				R.string.market);
		mDefaultGrids1.add(info4);

		GridInfo info5 = new GridInfo(5, R.drawable.main_icon_synchronous,
				R.string.connect_backup);
		mDefaultGrids2.add(info5);
		GridInfo info6 = new GridInfo(6, R.drawable.main_icon_security,
				R.string.pickproof);
		mDefaultGrids2.add(info6);
		GridInfo info7 = new GridInfo(7, R.drawable.main_icon_commonnum,
				R.string.common_funtion);
		mDefaultGrids2.add(info7);
		GridInfo info8 = new GridInfo(8, R.drawable.main_icon_mysoftware_move,
				R.string.software_move);
		mDefaultGrids2.add(info8);

		mSlideDrawer = (SlidingDrawer) findViewById(R.id.sliding_drawer);
		mBackground = (ImageView) findViewById(R.id.black_layout);
		mSlideDrawer
				.setOnDrawerScrollListener(new SlidingDrawer.OnDrawerScrollListener() {

					@Override
					public void onScrollStarted() {
						mBackground.setVisibility(View.VISIBLE);
					}

					@Override
					public void onScrollEnded() {
					}
				});
		mSlideDrawer
				.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {

					@Override
					public void onDrawerClosed() {
						mBackground.setVisibility(View.INVISIBLE);
					}
				});
		mGridBar = (GridView) findViewById(R.id.grid_bar);
		mGrid = (GridView) findViewById(R.id.grid);
		mGridBarAdapter = new MyGridAdapter(mDefaultGrids1,
				getLayoutInflater(), R.layout.item_grid);
		mGridAdapter = new MyGridAdapter(mDefaultGrids2, getLayoutInflater(),
				R.layout.item_grid);
		if (useCustomGrid()) {
			loadCustomGrids();
			mGridBarAdapter.setGrids(mDefaultGrids1);
			mGridAdapter.setGrids(mDefaultGrids2);
		}
		mGridBar.setAdapter(mGridBarAdapter);
		mGrid.setAdapter(mGridAdapter);
	}

	private boolean useCustomGrid() {
		return getSharedPreferences(NUSECURITY_CONFIG, MODE_PRIVATE)
				.getBoolean(PREF_USE_CUSTOM_GRID, false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		findViewById(R.id.item_back).setVisibility(View.INVISIBLE);
		findViewById(R.id.item_option).setVisibility(View.VISIBLE);
		ImageView optionImg = (ImageView) findViewById(R.id.option_icon_img);
		optionImg.setImageResource(R.drawable.poplistview_button_icon);
		optionImg.setOnClickListener(mViewListener);
		if (!hasUsedOption()) {
			findViewById(R.id.option_new).setVisibility(View.VISIBLE);
		}

		initTabs();

		initGrids();

		initMenu();
	}

	class MyGridAdapter extends BaseAdapter {

		private List<GridInfo> mGrids;
		private LayoutInflater mInflater;
		private int mItemLayout;

		public void setGrids(List<GridInfo> grids) {
			mGrids = grids;
		}

		public MyGridAdapter(List<GridInfo> grids, LayoutInflater inflater,
				int layouId) {
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
			if (convertView == null) {
				convertView = mInflater.inflate(mItemLayout, null);
			}
			GridInfo info = mGrids.get(position);
			ImageView img = (ImageView) convertView
					.findViewById(R.id.item_image);
			img.setImageResource(info.drawableId);
			TextView text = (TextView) convertView.findViewById(R.id.item_text);
			text.setText(info.textId);
			return convertView;
		}

	}

	class MyMenuAdapter extends BaseAdapter {

		private List<MenuInfo> mMenus;
		private LayoutInflater mInflater;
		private int mItemLayout;

		public MyMenuAdapter(List<MenuInfo> menus, LayoutInflater inflater,
				int layoutId) {
			mMenus = menus;
			mInflater = inflater;
			mItemLayout = layoutId;
		}

		@Override
		public int getCount() {
			return mMenus.size();
		}

		@Override
		public Object getItem(int position) {
			return mMenus.get(position);
		}

		@Override
		public long getItemId(int position) {
			return mMenus.get(position).id;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = mInflater.inflate(mItemLayout, null);
			}
			MenuInfo info = mMenus.get(position);
			ImageView img = (ImageView) convertView
					.findViewById(R.id.poplist_menu_img);
			img.setImageResource(info.drawableId);
			TextView text = (TextView) convertView
					.findViewById(R.id.poplist_menu_text);
			text.setText(info.textId);
			if (info.showNew) {// TODO how to hide the new?
				convertView.findViewById(R.id.poplist_menu_new).setVisibility(
						View.VISIBLE);
			} else {
				convertView.findViewById(R.id.poplist_menu_new).setVisibility(
						View.GONE);
			}
			return convertView;
		}

	}

	class MyTabListAdapter extends BaseAdapter {

		private List<ListItemInfo> mTabs;
		private LayoutInflater mInflater;
		private int mItemLayout;

		public MyTabListAdapter(List<ListItemInfo> tabs,
				LayoutInflater inflater, int layoutId) {
			mTabs = tabs;
			mInflater = inflater;
			mItemLayout = layoutId;
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		@Override
		public Object getItem(int position) {
			return mTabs.get(position);
		}

		@Override
		public long getItemId(int position) {
			return mTabs.get(position).id;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = mInflater.inflate(mItemLayout, null);
			}
			ListItemInfo info = mTabs.get(position);
			ImageView img = (ImageView) convertView
					.findViewById(R.id.list_item_icon);
			img.setImageResource(info.drawableId);
			TextView textLarge = (TextView) convertView
					.findViewById(R.id.list_item_title);
			textLarge.setText(info.textLargeId);
			TextView textSmall = (TextView) convertView
					.findViewById(R.id.list_item_content);
			textSmall.setText(info.textSmallId);
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

	class MenuInfo {
		int id;
		int drawableId;
		int textId;
		boolean showNew;

		public MenuInfo(int id, int drawableId, int textId, boolean showNew) {
			this.id = id;
			this.drawableId = drawableId;
			this.textId = textId;
			this.showNew = showNew;
		}
	}

	class ListItemInfo {
		int id;
		int drawableId;
		int textLargeId;
		int textSmallId;

		public ListItemInfo(int id, int drawableId, int textLargeId,
				int textSmallId) {
			this.id = id;
			this.drawableId = drawableId;
			this.textLargeId = textLargeId;
			this.textSmallId = textSmallId;
		}
	}
}
