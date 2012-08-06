package com.nusmart.security;

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

	private static final String NUSECURITY_CONFIG = "nusecurity_config";
	private static final String PREF_SHOW_TIPS = "pref_show_tips";
	private static final String AGREEMENT_URL = "http://yangxinle.com";
	private ViewPager mPager;
	private List<View> mPages = new ArrayList<View>(4);
	private List<ImageView> mDots = new ArrayList<ImageView>(4);
	private ImageView mNextButton;

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
		mNextButton = (ImageView) findViewById(R.id.next_page);
		View.OnClickListener clickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mPager.getCurrentItem() < mPages.size() - 1) {
					mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
				}
			}
		};
		mNextButton.setOnClickListener(clickListener);
	}

	private void initViewPager() {
		LayoutInflater inflater = getLayoutInflater();
		mPager = (ViewPager) findViewById(R.id.pager);
		View tips1 = inflater.inflate(R.layout.tips_one, null);
		mPages.add(tips1);

		View tips2 = inflater.inflate(R.layout.tips_two, null);
		mPages.add(tips2);

		View tips3 = inflater.inflate(R.layout.tips_three, null);
		mPages.add(tips3);

		View tips4 = inflater.inflate(R.layout.tips_four, null);
		mPages.add(tips4);
		ImageView hyperLinkImg = (ImageView) tips4
				.findViewById(R.id.hyper_link_img);
		Button agreeButton = (Button) tips4
				.findViewById(R.id.agree_button);
		View.OnClickListener clickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.hyper_link_img:
					checkAgreementDetails();
					break;
				case R.id.agree_button:
					goToMainActivity();
					break;

				default:
					break;
				}
			}
		};
		hyperLinkImg.setOnClickListener(clickListener);
		agreeButton.setOnClickListener(clickListener);

		MyPagerAdapter adapter = new MyPagerAdapter();
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

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(mPages.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			View obj = mPages.get(position);
			((ViewPager) container).addView(obj);
			return obj;
		}

		@Override
		public int getCount() {
			return mPages.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}

	private boolean shouldShowTips() {
		return getSharedPreferences(NUSECURITY_CONFIG, Context.MODE_PRIVATE)
				.getBoolean(PREF_SHOW_TIPS, true);
	}

	private void goToMainActivity() {
		startActivity(new Intent(this, NuMain.class));
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (shouldShowTips()) {
			setContentView(R.layout.tips_container);
			initViewPager();
			initDotsAndNextButton();
		} else {
			goToMainActivity();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

}
