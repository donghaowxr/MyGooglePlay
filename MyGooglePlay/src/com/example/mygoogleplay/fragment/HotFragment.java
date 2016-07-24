package com.example.mygoogleplay.fragment;

import java.util.ArrayList;
import java.util.Random;
import com.example.mygoogleplay.http.protocol.HotProtocol;
import com.example.mygoogleplay.ui.view.FlowLayout;
import com.example.mygoogleplay.ui.view.LoadingPager.ResultState;
import com.example.mygoogleplay.utils.DrawableUtils;
import com.example.mygoogleplay.utils.UIUtils;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class HotFragment extends BaseFragment {

	private ArrayList<String> data;

	@Override
	public View OnCreateSuccessView() {
		ScrollView scrollView = new ScrollView(UIUtils.getContext());
		FlowLayout flowLayout = new FlowLayout(UIUtils.getContext());
		int padding = UIUtils.dpi2px(10);
		flowLayout.setPadding(padding, padding, padding, padding);
		flowLayout.setHorizontalSpacing(UIUtils.dpi2px(6));
		flowLayout.setVerticalSpacing(UIUtils.dpi2px(8));
		scrollView.addView(flowLayout);
		Random random = new Random();
		for (int i = 0; i < data.size(); i++) {
			final TextView textView = new TextView(UIUtils.getContext());
			textView.setText(data.get(i));
			textView.setPadding(padding, padding, padding, padding);
			textView.setGravity(Gravity.CENTER);
			int r = 30 + random.nextInt(200);
			int g = 30 + random.nextInt(200);
			int b = 30 + random.nextInt(200);
			textView.setBackground(DrawableUtils.getSelector(
					Color.rgb(r, g, b), 0xffcecece, UIUtils.dpi2px(6)));
			textView.setTextColor(Color.WHITE);
			flowLayout.addView(textView);
			textView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(UIUtils.getContext(), textView.getText(),
							Toast.LENGTH_SHORT).show();
				}
			});
		}
		return scrollView;
	}

	@Override
	public ResultState OnLoad() {
		HotProtocol protocol = new HotProtocol();
		data = protocol.getData(0);
		return check(data);
	}

}
