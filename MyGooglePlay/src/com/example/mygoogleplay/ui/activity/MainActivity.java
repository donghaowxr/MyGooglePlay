package com.example.mygoogleplay.ui.activity;

import com.example.mygoogleplay.R;
import com.example.mygoogleplay.fragment.BaseFragment;
import com.example.mygoogleplay.fragment.FragmentFactory;
import com.example.mygoogleplay.ui.view.PagerTab;
import com.example.mygoogleplay.utils.UIUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class MainActivity extends BaseActivity {

	private PagerTab ptMain;
	private ViewPager vpMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ptMain = (PagerTab) findViewById(R.id.pt_main);
		vpMain = (ViewPager) findViewById(R.id.vp_main);
		vpMain.setAdapter(new MyAdpter(getSupportFragmentManager()));
		ptMain.setViewPager(vpMain);
		ptMain.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				BaseFragment fragment = FragmentFactory
						.createFragment(position);
				fragment.loadData();
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}

	class MyAdpter extends FragmentPagerAdapter {
		private String[] mTabNameArray;

		public MyAdpter(FragmentManager fm) {
			super(fm);
			mTabNameArray = UIUtils.getStringArray(R.array.tab_names);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mTabNameArray[position];
		}

		@Override
		public Fragment getItem(int position) {
			BaseFragment fragment = FragmentFactory.createFragment(position);
			return fragment;
		}

		@Override
		public int getCount() {
			return mTabNameArray.length;
		}

	}
}
