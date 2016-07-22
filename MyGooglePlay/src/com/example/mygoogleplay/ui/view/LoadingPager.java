package com.example.mygoogleplay.ui.view;

import com.example.mygoogleplay.R;
import com.example.mygoogleplay.utils.UIUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class LoadingPager extends FrameLayout {
	private static final int STATE_LOAD_UNDO = 1;
	private static final int STATE_LOAD_LOADING = 2;
	private static final int STATE_LOAD_ERROR = 3;
	private static final int STATE_LOAD_EMPTY = 4;
	private static final int STATE_LOAD_SUCCESS = 5;
	private int mCurrentState = STATE_LOAD_UNDO;

	public LoadingPager(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	public LoadingPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public LoadingPager(Context context) {
		super(context);
		initView();
	}
	private void initView() {
		View view=UIUtils.inflate(R.layout.page_loading);
		addView(view);
	}
}
