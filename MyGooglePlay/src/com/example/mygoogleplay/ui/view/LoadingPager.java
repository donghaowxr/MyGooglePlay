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
	private View mLoadingPage;
	private View mErrorPage;
	private View mEmptyPage;

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
		if (mLoadingPage == null) {
			mLoadingPage = UIUtils.inflate(R.layout.page_loading);
			addView(mLoadingPage);
		}
		if (mErrorPage == null) {
			mErrorPage = UIUtils.inflate(R.layout.page_error);
			addView(mErrorPage);
		}
		if (mEmptyPage == null) {
			mEmptyPage = UIUtils.inflate(R.layout.page_empty);
			addView(mEmptyPage);
		}
	}
}
