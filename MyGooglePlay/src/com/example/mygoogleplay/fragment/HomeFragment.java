package com.example.mygoogleplay.fragment;

import com.example.mygoogleplay.ui.view.LoadingPager.ResultState;
import com.example.mygoogleplay.utils.UIUtils;

import android.view.View;
import android.widget.TextView;

public class HomeFragment extends BaseFragment {

	@Override
	public View OnCreateSuccessView() {
		TextView view = new TextView(UIUtils.getContext());
		view.setText("加载成功");
		return view;
	}

	@Override
	public ResultState OnLoad() {
		return ResultState.STATE_SUCCESS;
	}

}
