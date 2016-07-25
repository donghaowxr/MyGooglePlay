package com.example.mygoogleplay.ui.holder;

import android.view.View;

public abstract class BaseHolder<T> {
	public View mRootView;
	private T data;

	public BaseHolder() {
		View view = initView();
		mRootView = view;
		mRootView.setTag(this);
	}

	/**
	 * 获取rootview布局对象
	 * 
	 * @return
	 */
	public View getRootView() {
		return mRootView;
	}

	/**
	 * 设置数据
	 * 
	 * @param data
	 */
	public void setData(T data) {
		this.data = data;
		refreshView(data);
	}

	/**
	 * 返回数据
	 * 
	 * @return
	 */
	public T getData() {
		return data;
	}

	/**
	 * 初始化布局
	 * 
	 * @return
	 */
	public abstract View initView();

	/**
	 * 根据数据刷新页面
	 * 
	 * @param data
	 */
	public abstract void refreshView(T data);
}
