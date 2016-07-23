package com.example.mygoogleplay.ui.view;

import com.example.mygoogleplay.R;
import com.example.mygoogleplay.utils.UIUtils;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public abstract class LoadingPager extends FrameLayout {
	private static final int STATE_LOAD_UNDO = 1;
	private static final int STATE_LOAD_LOADING = 2;
	private static final int STATE_LOAD_ERROR = 3;
	private static final int STATE_LOAD_EMPTY = 4;
	private static final int STATE_LOAD_SUCCESS = 5;
	private int mCurrentState = STATE_LOAD_UNDO;
	private View mLoadingPage;
	private View mErrorPage;
	private View mEmptyPage;
	private View mSuccessPage;

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

	/**
	 * 初始化布局
	 */
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
		showRightPage();
	}

	/**
	 * 根据状态显示不同的布局
	 */
	private void showRightPage() {
		mLoadingPage
				.setVisibility((mCurrentState == STATE_LOAD_UNDO || mCurrentState == STATE_LOAD_LOADING) ? View.VISIBLE
						: View.GONE);
		mEmptyPage
				.setVisibility((mCurrentState == STATE_LOAD_EMPTY) ? View.VISIBLE
						: View.GONE);
		mErrorPage
				.setVisibility((mCurrentState == STATE_LOAD_ERROR) ? View.VISIBLE
						: View.GONE);

		if (mSuccessPage == null && mCurrentState == STATE_LOAD_SUCCESS) {
			mSuccessPage = OnCreateSuccessView();
			if (mSuccessPage != null) {
				addView(mSuccessPage);
			}
		}
		if (mSuccessPage != null) {
			mSuccessPage
					.setVisibility((mCurrentState == STATE_LOAD_SUCCESS) ? View.VISIBLE
							: View.GONE);
		}
	}

	/**
	 * 开始加载页面数据
	 */
	public void loadData() {
		new Thread() {
			@Override
			public void run() {
				if (mCurrentState != STATE_LOAD_LOADING) {
					mCurrentState = STATE_LOAD_LOADING;
					final ResultState resultState = OnLoad();
					UIUtils.runOnUIThread(new Runnable() {
						@Override
						public void run() {
							if (resultState != null) {
								mCurrentState = resultState.getState();
								showRightPage();
							}
						}
					});
				}
			}
		}.start();
	}

	/**
	 * 创建加载状态枚举
	 * 
	 * @author donghao
	 * 
	 */
	public enum ResultState {
		STATE_SUCCESS(STATE_LOAD_SUCCESS), STATE_EMPTY(STATE_LOAD_EMPTY), STATE_ERROR(
				STATE_LOAD_ERROR);
		private int state;

		private ResultState(int state) {
			this.state = state;
		}

		public int getState() {
			return state;
		}
	}

	public abstract View OnCreateSuccessView();

	public abstract ResultState OnLoad();
}
