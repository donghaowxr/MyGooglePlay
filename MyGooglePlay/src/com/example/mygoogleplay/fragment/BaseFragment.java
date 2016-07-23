package com.example.mygoogleplay.fragment;

import com.example.mygoogleplay.ui.view.LoadingPager;
import com.example.mygoogleplay.utils.UIUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LoadingPager pager = new LoadingPager(UIUtils.getContext()) {
			@Override
			public View OnCreateSuccessView() {
				return BaseFragment.this.OnCreateSuccessView();
			}
		};
		return pager;
	}

	public abstract View OnCreateSuccessView();
}
