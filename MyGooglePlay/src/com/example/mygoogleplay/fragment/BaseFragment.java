package com.example.mygoogleplay.fragment;

import java.util.ArrayList;

import com.example.mygoogleplay.ui.view.LoadingPager;
import com.example.mygoogleplay.ui.view.LoadingPager.ResultState;
import com.example.mygoogleplay.utils.UIUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
	private LoadingPager mLoadingPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mLoadingPager = new LoadingPager(UIUtils.getContext()) {
			@Override
			public View OnCreateSuccessView() {
				return BaseFragment.this.OnCreateSuccessView();
			}

			@Override
			public ResultState OnLoad() {
				return BaseFragment.this.OnLoad();
			}
		};
		return mLoadingPager;
	}

	public abstract View OnCreateSuccessView();

	public abstract ResultState OnLoad();

	/**
	 * 开始加载页面数据
	 */
	public void loadData() {
		if (mLoadingPager != null) {
			mLoadingPager.loadData();
		}
	}

	/**
	 * 对网络的合法性进行校验
	 * 
	 * @param obj
	 * @return
	 */
	public ResultState check(Object obj) {
		if (obj != null) {
			if (obj instanceof ArrayList) {
				ArrayList list = (ArrayList) obj;
				if (list.isEmpty()) {
					return ResultState.STATE_EMPTY;
				} else {
					return ResultState.STATE_SUCCESS;
				}
			}
		}
		return ResultState.STATE_ERROR;
	}
}
