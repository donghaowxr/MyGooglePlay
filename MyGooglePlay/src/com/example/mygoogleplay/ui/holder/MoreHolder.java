package com.example.mygoogleplay.ui.holder;

import com.example.mygoogleplay.R;
import com.example.mygoogleplay.utils.UIUtils;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MoreHolder extends BaseHolder<Integer> {
	public static final int STATE_LOAD_MORE = 1;
	public static final int STATE_LOAD_ERROR = 2;
	public static final int STATE_LOAD_NONE = 3;
	private LinearLayout llLoadMore;
	private TextView tvLoadError;

	public MoreHolder(boolean state) {
		setData(state ? STATE_LOAD_MORE : STATE_LOAD_NONE);
	}

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.list_item_more);
		llLoadMore = (LinearLayout) view.findViewById(R.id.ll_more);
		tvLoadError = (TextView) view.findViewById(R.id.tv_load_error);
		return view;
	}

	@Override
	public void refreshView(Integer data) {
		switch (data) {
		case STATE_LOAD_MORE:
			llLoadMore.setVisibility(View.VISIBLE);
			tvLoadError.setVisibility(View.GONE);
			break;
		case STATE_LOAD_NONE:
			llLoadMore.setVisibility(View.GONE);
			tvLoadError.setVisibility(View.GONE);
			break;
		case STATE_LOAD_ERROR:
			llLoadMore.setVisibility(View.GONE);
			tvLoadError.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}

}
