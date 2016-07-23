package com.example.mygoogleplay.ui.adapter;

import java.util.ArrayList;

import com.example.mygoogleplay.ui.holder.BaseHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter {
	private ArrayList<T> data;

	public MyBaseAdapter(ArrayList<T> data) {
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BaseHolder holder;
		if (convertView==null) {
			holder=getHolder();
		}else {
			holder=(BaseHolder) convertView.getTag();
		}
		holder.setData(getItem(position));
		return holder.getRootView();
	}
	public abstract BaseHolder<T> getHolder();
}
