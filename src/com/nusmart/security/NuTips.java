package com.nusmart.security;

import static com.nusmart.security.NuApp.NUSECURITY_CONFIG;
import static com.nusmart.security.NuApp.PREF_SHOW_TIPS;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nusmart.security.uilib.PagerAdapter;
import com.nusmart.security.uilib.ViewPager;
import com.nusmart.security.uilib.ViewPager.OnPageChangeListener;

public class NuTips extends NuActivity {

	private static final String AGREEMENT_URL = "http://yangxinle.com";
	private ViewPager mPager;
	private int mPageCount;
	private List<ImageView> mDots = new ArrayList<ImageView>(4);
	private ImageView mNextButton;

	private void goToMainActivity() {
		startActivity(new Intent(this, NuMain.class));
		finish();
	}
	
	private void checkAgreementDetails() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(AGREEMENT_URL));
		startActivity(intent);
	}

	private void initDotsAndNextButton() {
		ImageView iv1 = (ImageView) findViewById(R.id.scroll_dot1);
		mDots.add(iv1);
		ImageView iv2 = (ImageView) findViewById(R.id.scroll_dot2);
		mDots.add(iv2);
		ImageView iv3 = (ImageView) findViewById(R.id.scroll_dot3);
		mDots.add(iv3);
		ImageView iv4 = (ImageView) findViewById(R.id.scroll_dot4);
		mDots.add(iv4);
		ImageView iv5 = (ImageView) findViewById(R.id.scroll_dot5);
		mDots.add(iv5);
		ImageView iv6 = (ImageView) findViewById(R.id.scroll_dot6);
		mDots.add(iv6);
		mNextButton = (ImageView) findViewById(R.id.next_page);
		View.OnClickListener clickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mPager.getCurrentItem() < mPageCount - 1) {
					mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
				}
			}
		};
		mNextButton.setOnClickListener(clickListener);
	}

	private void initViewPager() {
		LayoutInflater inflater = getLayoutInflater();
		mPager = (ViewPager) findViewById(R.id.pager);
		List<View> pages = new ArrayList<View>(4);
		View tips1 = inflater.inflate(R.layout.layout_tips_one, null);
		pages.add(tips1);

		View tips2 = inflater.inflate(R.layout.layout_tips_two, null);
		pages.add(tips2);

		View tips3 = inflater.inflate(R.layout.layout_tips_three, null);
		pages.add(tips3);

		View tips4 = inflater.inflate(R.layout.layout_tips_four, null);
		pages.add(tips4);
		
		View tips5 = inflater.inflate(R.layout.layout_tips_five, null);
		pages.add(tips5);
		
		View tips6 = inflater.inflate(R.layout.layout_tips_six, null);
		pages.add(tips6);
		
		mPageCount = 6;
		
		ImageView hyperLinkImg = (ImageView) tips6
				.findViewById(R.id.hyper_link_img);
		Button agreeButton = (Button) tips6.findViewById(R.id.agree_button);
		View.OnClickListener clickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.hyper_link_img:
					checkAgreementDetails();
					break;
				case R.id.agree_button:
					getSharedPreferences(NUSECURITY_CONFIG,
							Context.MODE_PRIVATE).edit()
							.putBoolean(PREF_SHOW_TIPS, false).apply();
					goToMainActivity();
					break;

				default:
					break;
				}
			}
		};
		hyperLinkImg.setOnClickListener(clickListener);
		agreeButton.setOnClickListener(clickListener);

		MyPagerAdapter adapter = new MyPagerAdapter(pages);
		mPager.setAdapter(adapter);
		OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				if (arg0 == mDots.size() - 1) {
					mNextButton.setVisibility(View.INVISIBLE);
				} else if (mNextButton.getVisibility() != View.VISIBLE) {
					mNextButton.setVisibility(View.VISIBLE);
				}
				for (int i = 0; i < mDots.size(); i++) {
					if (i == arg0) {
						mDots.get(i).setBackgroundResource(
								R.drawable.scroll_ad_dot_black);
					} else {
						mDots.get(i).setBackgroundResource(
								R.drawable.scroll_ad_dot_white);
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// Nothing to do

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// Nothing to do

			}
		};
		mPager.setOnPageChangeListener(pageChangeListener);
	}

	class MyPagerAdapter extends PagerAdapter {

		private List<View> pages;
		public MyPagerAdapter(List<View> pages) {
			this.pages = pages;
		}
		
		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(pages.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			View obj = pages.get(position);
			((ViewPager) container).addView(obj);
			return obj;
		}

		@Override
		public int getCount() {
			return pages.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_tips_container);
		initViewPager();
		initDotsAndNextButton();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

}
