package com.example.mygoogleplay.ui.adapter;

import java.util.ArrayList;

import com.example.mygoogleplay.ui.holder.BaseHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter {
	private ArrayList<T> data;
	private static final int TYPE_NORMAL = 0;
	private static final int TYPE_MORE = 1;

	public MyBaseAdapter(ArrayList<T> data) {
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size() + 1;
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 返回布局类型个数
	 */
	@Override
	public int getViewTypeCount() {
		return 2;
	}

	/**
	 * 返回当前位置应该展示的布局
	 */
	@Override
	public int getItemViewType(int position) {
		if (position == getCount() - 1) {
			return TYPE_MORE;
		} else {
			return getInnerType();
		}
	}

	public int getInnerType() {
		return TYPE_NORMAL;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BaseHolder holder;
		if (convertView == null) {
			holder = getHolder();
		} else {
			holder = (BaseHolder) convertView.getTag();
		}
		holder.setData(getItem(position));
		return holder.getRootView();
	}

	public abstract BaseHolder<T> getHolder();
}
