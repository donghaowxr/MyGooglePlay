package com.example.mygoogleplay.fragment;

import java.util.ArrayList;
import com.example.mygoogleplay.domain.AppInfo;
import com.example.mygoogleplay.http.protocol.AppProtocol;
import com.example.mygoogleplay.ui.adapter.MyBaseAdapter;
import com.example.mygoogleplay.ui.holder.AppHolder;
import com.example.mygoogleplay.ui.holder.BaseHolder;
import com.example.mygoogleplay.ui.view.MyListView;
import com.example.mygoogleplay.ui.view.LoadingPager.ResultState;
import com.example.mygoogleplay.utils.UIUtils;
import android.view.View;

public class AppFragment extends BaseFragment {

	private MyListView listView;
	private ArrayList<AppInfo> data;

	@Override
	public View OnCreateSuccessView() {
		listView = new MyListView(UIUtils.getContext());
		listView.setAdapter(new AppAdapter(data));
		return listView;
	}

	@Override
	public ResultState OnLoad() {
		AppProtocol protocol = new AppProtocol();
		data = protocol.getData(0);
		return check(data);
	}

	public class AppAdapter extends MyBaseAdapter<AppInfo> {

		public AppAdapter(ArrayList<AppInfo> data) {
			super(data);
		}

		@Override
		public BaseHolder<AppInfo> getHolder(int position) {
			AppHolder appHolder = new AppHolder();
			return appHolder;
		}

		@Override
		public ArrayList<AppInfo> onLoadMore() {
			AppProtocol protocol = new AppProtocol();
			ArrayList<AppInfo> moreData = protocol.getData(getListSize());
			return moreData;
		}

	}
}
