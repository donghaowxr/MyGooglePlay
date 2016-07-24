package com.example.mygoogleplay.ui.adapter;

import java.util.ArrayList;

import com.example.mygoogleplay.ui.holder.BaseHolder;
import com.example.mygoogleplay.ui.holder.MoreHolder;
import com.example.mygoogleplay.utils.UIUtils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

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
			if (getItemViewType(position) == TYPE_MORE) {
				holder = new MoreHolder(hanMore());
			} else {
				holder = getHolder();
			}
		} else {
			holder = (BaseHolder) convertView.getTag();
		}
		if (getItemViewType(position) != TYPE_MORE) {
			holder.setData(getItem(position));
		} else {
			// 加载更多布局
			MoreHolder moreHolder = (MoreHolder) holder;
			if (moreHolder.getData() == MoreHolder.STATE_LOAD_MORE) {
				loadMore(moreHolder);
			}
		}
		return holder.getRootView();
	}

	// 是否正在进行加载更多操作
	private boolean isLoadMore = false;

	/**
	 * 加载更多数据处理
	 */
	private void loadMore(final MoreHolder moreHolder) {
		// 只有不在加载更多操作时才能进行加载更多操作
		if (isLoadMore == false) {
			isLoadMore = true;
			new Thread() {
				@Override
				public void run() {
					final ArrayList<T> moreData = onLoadMore();
					UIUtils.runOnUIThread(new Runnable() {
						@Override
						public void run() {
							if (moreData == null) {
								moreHolder.setData(MoreHolder.STATE_LOAD_ERROR);
							} else {
								if (moreData.size() < 20) {
									moreHolder
											.setData(MoreHolder.STATE_LOAD_NONE);
									Toast.makeText(UIUtils.getContext(),
											"没有更多数据了...", Toast.LENGTH_SHORT)
											.show();
								} else {
									moreHolder
											.setData(MoreHolder.STATE_LOAD_MORE);
								}
								data.addAll(moreData);
								MyBaseAdapter.this.notifyDataSetChanged();
							}
							isLoadMore = false;
						}
					});
				}
			}.start();
		}
	}

	public abstract BaseHolder<T> getHolder();

	public abstract ArrayList<T> onLoadMore();

	public boolean hanMore() {
		return true;
	}

	/**
	 * 获取当前集合大小
	 * 
	 * @return
	 */
	public int getListSize() {
		return data.size();
	}
}
