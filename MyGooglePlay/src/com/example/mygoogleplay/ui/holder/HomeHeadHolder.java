package com.example.mygoogleplay.ui.holder;

import java.util.ArrayList;

import com.example.mygoogleplay.http.HttpHelper;
import com.example.mygoogleplay.utils.BitmapHelper;
import com.example.mygoogleplay.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

public class HomeHeadHolder extends BaseHolder<ArrayList<String>> {

	private ArrayList<String> mData;
	private ViewPager vPager;

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
		return rlRoot;
	}

	@Override
	public void refreshView(ArrayList<String> data) {
		mData = data;
		vPager.setAdapter(new HomeHeadAdapter());
	}

	public class HomeHeadAdapter extends PagerAdapter {
		private BitmapUtils mBitmapUtils;

		public HomeHeadAdapter() {
			mBitmapUtils = BitmapHelper.getBitmapUtils();
		}

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
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
