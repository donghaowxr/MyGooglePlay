package com.example.mygoogleplay.fragment;

import java.util.ArrayList;

import com.example.mygoogleplay.domain.SubjectInfo;
import com.example.mygoogleplay.http.protocol.SubjectProtocol;
import com.example.mygoogleplay.ui.adapter.MyBaseAdapter;
import com.example.mygoogleplay.ui.holder.BaseHolder;
import com.example.mygoogleplay.ui.holder.SubjectHolder;
import com.example.mygoogleplay.ui.view.MyListView;
import com.example.mygoogleplay.ui.view.LoadingPager.ResultState;
import com.example.mygoogleplay.utils.UIUtils;

import android.view.View;

public class SubjectFragment extends BaseFragment {

	private ArrayList<SubjectInfo> data;

	@Override
	public View OnCreateSuccessView() {
		MyListView listView = new MyListView(UIUtils.getContext());
		listView.setAdapter(new SubjectAdapter(data));
		return listView;
	}

	@Override
	public ResultState OnLoad() {
		SubjectProtocol protocol = new SubjectProtocol();
		data = protocol.getData(0);
		return check(data);
	}

	public class SubjectAdapter extends MyBaseAdapter<SubjectInfo> {

		public SubjectAdapter(ArrayList<SubjectInfo> data) {
			super(data);
		}

		@Override
		public BaseHolder<SubjectInfo> getHolder() {
			SubjectHolder holder = new SubjectHolder();
			return holder;
		}

		@Override
		public ArrayList<SubjectInfo> onLoadMore() {
			SubjectProtocol protocol = new SubjectProtocol();
			ArrayList<SubjectInfo> moreData = protocol.getData(getListSize());
			return moreData;
		}

	}
}
