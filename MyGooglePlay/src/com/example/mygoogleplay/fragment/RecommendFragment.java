package com.example.mygoogleplay.fragment;

import java.util.ArrayList;
import java.util.Random;

import com.example.mygoogleplay.http.protocol.RecommendProtocol;
import com.example.mygoogleplay.ui.fly.StellarMap;
import com.example.mygoogleplay.ui.view.LoadingPager.ResultState;
import com.example.mygoogleplay.utils.UIUtils;

import android.R.string;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class RecommendFragment extends BaseFragment {

	private ArrayList<String> data;

	@Override
	public View OnCreateSuccessView() {
		StellarMap stellarMap = new StellarMap(UIUtils.getContext());
		stellarMap.setAdapter(new RecommendAdapter());
		stellarMap.setRegularity(6, 9);// 设置9行6列的随机方式
		int padding = UIUtils.dpi2px(10);
		stellarMap.setInnerPadding(padding, padding, padding, padding);
		stellarMap.setGroup(0, true);
		return stellarMap;
	}

	@Override
	public ResultState OnLoad() {
		RecommendProtocol protocol = new RecommendProtocol();
		data = protocol.getData(0);
		return check(data);
	}

	public class RecommendAdapter implements StellarMap.Adapter {

		@Override
		public int getGroupCount() {
			return 2;
		}

		@Override
		public int getCount(int group) {
			int count = data.size() / getGroupCount();
			if (group == getGroupCount() - 1) {
				count += data.size() % getGroupCount();
			}
			return count;
		}

		@Override
		public View getView(int group, int position, View convertView) {
			position += (group) * getCount(group - 1);
			TextView textView = new TextView(UIUtils.getContext());
			textView.setText(data.get(position));
			Random random = new Random();
			int size = 16 + random.nextInt(10);
			textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
			int r = 30 + random.nextInt(200);
			int g = 30 + random.nextInt(200);
			int b = 30 + random.nextInt(200);
			textView.setTextColor(Color.rgb(r, g, b));
			return textView;
		}

		@Override
		public int getNextGroupOnZoom(int group, boolean isZoomIn) {
			if (isZoomIn) {
				if (group > 0) {
					group--;
				} else {
					group = getGroupCount() - 1;
				}
			} else {
				if (group < getGroupCount() - 1) {
					group++;
				} else {
					group = 0;
				}
			}
			return group;
		}

	}
}
