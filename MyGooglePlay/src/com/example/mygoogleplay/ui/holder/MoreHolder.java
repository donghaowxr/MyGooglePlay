package com.example.mygoogleplay.ui.holder;

import com.example.mygoogleplay.R;
import com.example.mygoogleplay.utils.UIUtils;

import android.view.View;

public class MoreHolder extends BaseHolder<Integer> {

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.list_item_more);
		return view;
	}

	@Override
	public void refreshView(Integer data) {

	}

}
