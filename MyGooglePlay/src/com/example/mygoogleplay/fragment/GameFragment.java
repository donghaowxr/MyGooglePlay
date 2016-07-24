package com.example.mygoogleplay.fragment;

import com.example.mygoogleplay.ui.view.LoadingPager.ResultState;
import com.example.mygoogleplay.utils.UIUtils;

import android.view.View;
import android.widget.TextView;

public class GameFragment extends BaseFragment {

	@Override
	public View OnCreateSuccessView() {
		TextView textView = new TextView(UIUtils.getContext());
		textView.setText("GameFragment");
		return textView;
	}

	@Override
	public ResultState OnLoad() {
		return ResultState.STATE_SUCCESS;
	}

}
