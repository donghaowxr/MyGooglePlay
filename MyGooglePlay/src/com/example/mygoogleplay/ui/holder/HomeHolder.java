package com.example.mygoogleplay.ui.holder;

import com.example.mygoogleplay.R;
import com.example.mygoogleplay.domain.AppInfo;
import com.example.mygoogleplay.utils.UIUtils;
import android.view.View;
import android.widget.TextView;

public class HomeHolder extends BaseHolder<AppInfo> {

	private TextView tvShow;

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.list_item_home);
		tvShow = (TextView) view.findViewById(R.id.tv_list_item);
		return view;
	}

	@Override
	public void refreshView(AppInfo data) {
		tvShow.setText(data.name);
	}

}
