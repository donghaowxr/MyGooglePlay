package com.example.mygoogleplay.ui.holder;

import java.util.ArrayList;

import com.example.mygoogleplay.R;
import com.example.mygoogleplay.http.HttpHelper;
import com.example.mygoogleplay.utils.BitmapHelper;
import com.example.mygoogleplay.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

public class HomeHeadHolder extends BaseHolder<ArrayList<String>> {

	private ArrayList<String> mData;
	private ViewPager vPager;
	private LinearLayout llPoint;
	private int mOldPosition;

	@Override
	public View initView() {
		RelativeLayout rlRoot = new RelativeLayout(UIUtils.getContext());
		AbsListView.LayoutParams params = new AbsListView.LayoutParams(
				AbsListView.LayoutParams.MATCH_PARENT, UIUtils.dpi2px(150));
		rlRoot.setLayoutParams(params);
		vPager = new ViewPager(UIUtils.getContext());
		RelativeLayout.LayoutParams rLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		vPager.setLayoutParams(rLayoutParams);
		rlRoot.addView(vPager);
		llPoint = new LinearLayout(UIUtils.getContext());
		llPoint.setOrientation(LinearLayout.HORIZONTAL);
		RelativeLayout.LayoutParams llParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		llParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		llParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		llPoint.setLayoutParams(llParams);
		int padding = UIUtils.dpi2px(10);
		llPoint.setPadding(padding, padding, padding, padding);
		rlRoot.addView(llPoint);
		return rlRoot;
	}

	@Override
	public void refreshView(ArrayList<String> data) {
		mData = data;
		vPager.setAdapter(new HomeHeadAdapter());
		vPager.setCurrentItem(mData.size() * 10000);
		for (int i = 0; i < mData.size(); i++) {
			ImageView ivPoint = new ImageView(UIUtils.getContext());
			if (i == 0) {
				ivPoint.setImageResource(R.drawable.indicator_selected);
			} else {
				ivPoint.setImageResource(R.drawable.indicator_normal);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT);
				int margin = UIUtils.dpi2px(3);
				params.leftMargin = margin;
				ivPoint.setLayoutParams(params);
			}
			llPoint.addView(ivPoint);
		}
		vPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				position = position % mData.size();
				ImageView ivPoint = (ImageView) llPoint.getChildAt(position);
				ivPoint.setImageResource(R.drawable.indicator_selected);
				ImageView ivLastPoint = (ImageView) llPoint
						.getChildAt(mOldPosition);
				ivLastPoint.setImageResource(R.drawable.indicator_normal);
				mOldPosition = position;
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});

		HomeTask homeTask = new HomeTask();
		homeTask.start();
	}

	class HomeTask implements Runnable {
		public void start() {
			UIUtils.getHandler().removeCallbacksAndMessages(null);
			UIUtils.getHandler().postDelayed(this, 3000);
		}

		@Override
		public void run() {
			int currentItem = vPager.getCurrentItem();
			currentItem++;
			vPager.setCurrentItem(currentItem);
			UIUtils.getHandler().postDelayed(this, 3000);
		}

	}

	public class HomeHeadAdapter extends PagerAdapter {
		private BitmapUtils mBitmapUtils;

		public HomeHeadAdapter() {
			mBitmapUtils = BitmapHelper.getBitmapUtils();
		}

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			position = position % mData.size();
			ImageView ivPic = new ImageView(UIUtils.getContext());
			ViewPager.LayoutParams params = new ViewPager.LayoutParams();
			params.width = ViewPager.LayoutParams.MATCH_PARENT;
			params.height = ViewPager.LayoutParams.WRAP_CONTENT;
			ivPic.setScaleType(ScaleType.CENTER_CROP);
			ivPic.setLayoutParams(params);
			mBitmapUtils.display(ivPic,
					HttpHelper.URL + "image?name=" + mData.get(position));
			container.addView(ivPic);
			return ivPic;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
}
