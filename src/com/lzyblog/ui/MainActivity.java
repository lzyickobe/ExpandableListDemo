package com.lzyblog.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.lzyblog.R;
import com.lzyblog.indicator.CirclePageIndicator;
import com.lzyblog.pagetransform.AlphaPageTransformer;
import com.lzyblog.ui.fragment.FriendListFragment;
import com.lzyblog.ui.fragment.GroupListFrament;
import com.lzyblog.ui.module.TitleModule;

public class MainActivity extends FragmentActivity {
	private static final int NUM_PAGES = 2;

	TitleModule titleModule;

	ViewPager mPager;
	PagerAdapter mPagerAdapter;
	CirclePageIndicator mPageIndicator;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);

		init();
	}

	private void init() {
		//标题栏
		titleModule = new TitleModule(this, "好友");
		titleModule.showBack(false);
		titleModule.setBackText("分组管理");
		titleModule.setBackClickListener(onClickListener);

		//左右滑动的ViewPager
		mPager = (ViewPager) findViewById(R.id.pager);
		mPageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
		mPagerAdapter = new PageAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);
		mPageIndicator.setViewPager(mPager);
		//page切换动画效果，在pagetransform中有其他的动画效果
		mPager.setPageTransformer(true, new AlphaPageTransformer());
		mPager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				if (position == 0) {
					titleModule.setTitle("好友");
				} else if (position == 1) {
					titleModule.setTitle("群组");
				}
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			public void onPageScrollStateChanged(int arg0) {

			}
		});

		//提示
		Toast.makeText(MainActivity.this, "可左右滑动，切换好友列表和群组列表", Toast.LENGTH_LONG).show();
	}

	OnClickListener onClickListener = new OnClickListener() {
		public void onClick(View v) {
			if (v == titleModule.btnBack) { //分组管理

			}
		}
	};

	class PageAdapter extends FragmentPagerAdapter {

		public PageAdapter(FragmentManager fm) {
			super(fm);
		}

		public Fragment getItem(int position) {
			if (position == 0) {
				return new FriendListFragment();
			} else {
				return new GroupListFrament();
			}
		}

		public int getCount() {
			return NUM_PAGES;
		}
	}

}