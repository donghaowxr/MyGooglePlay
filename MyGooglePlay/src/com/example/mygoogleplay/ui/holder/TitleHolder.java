package com.example.mygoogleplay.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.example.mygoogleplay.R;
import com.example.mygoogleplay.domain.CategoryInfo;
import com.example.mygoogleplay.utils.UIUtils;

public class TitleHolder extends BaseHolder<CategoryInfo> {

	private TextView tvTitle;

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.list_item_title);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		return view;
	}

	@Override
	public void refreshView(CategoryInfo data) {
		tvTitle.setText(data.title);
	}

}
