package com.example.mygoogleplay.fragment;

import java.util.ArrayList;

import com.example.mygoogleplay.domain.CategoryInfo;
import com.example.mygoogleplay.http.protocol.CategoryProtocol;
import com.example.mygoogleplay.ui.adapter.MyBaseAdapter;
import com.example.mygoogleplay.ui.holder.BaseHolder;
import com.example.mygoogleplay.ui.holder.CategoryHolder;
import com.example.mygoogleplay.ui.holder.TitleHolder;
import com.example.mygoogleplay.ui.view.MyListView;
import com.example.mygoogleplay.ui.view.LoadingPager.ResultState;
import com.example.mygoogleplay.utils.UIUtils;

import android.view.View;

public class CategoryFragment extends BaseFragment {

	private ArrayList<CategoryInfo> data;

	@Override
	public View OnCreateSuccessView() {
		MyListView listView = new MyListView(UIUtils.getContext());
		listView.setAdapter(new CategoryAdapter(data));
		return listView;
	}

	@Override
	public ResultState OnLoad() {
		CategoryProtocol protocol = new CategoryProtocol();
		data = protocol.getData(0);
		return check(data);
	}

	public class CategoryAdapter extends MyBaseAdapter<CategoryInfo> {

		public CategoryAdapter(ArrayList<CategoryInfo> data) {
			super(data);
		}

		@Override
		public BaseHolder<CategoryInfo> getHolder(int position) {
			CategoryInfo info = data.get(position);
			if (info.isTitle) {
				return new TitleHolder();
			} else {
				return new CategoryHolder();
			}
		}

		@Override
		public boolean hanMore() {
			return false;// 没有更多数据，隐藏更多布局
		}

		@Override
		public int getViewTypeCount() {
			return super.getViewTypeCount() + 1;// 在原来基础上添加一种布局类型
		}

		@Override
		public int getInnerType(int position) {
			CategoryInfo info = data.get(position);
			if (info.isTitle) {
				// 标题类型
				return super.getInnerType(position) + 1;// 在normal的基础上+1,确保normal的类型为1
			} else {
				// 普通类型
				return super.getInnerType(position);
			}
		}

		@Override
		public ArrayList<CategoryInfo> onLoadMore() {
			return null;
		}

	}
}
