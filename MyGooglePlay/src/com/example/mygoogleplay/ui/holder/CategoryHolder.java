package com.example.mygoogleplay.ui.holder;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygoogleplay.R;
import com.example.mygoogleplay.domain.CategoryInfo;
import com.example.mygoogleplay.http.HttpHelper;
import com.example.mygoogleplay.utils.BitmapHelper;
import com.example.mygoogleplay.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

public class CategoryHolder extends BaseHolder<CategoryInfo> implements
		OnClickListener {

	private TextView tvName1;
	private TextView tvName2;
	private TextView tvName3;
	private ImageView ivIcon1;
	private ImageView ivIcon2;
	private ImageView ivIcon3;
	private LinearLayout llGride1;
	private LinearLayout llGride2;
	private LinearLayout llGride3;
	private BitmapUtils mBitmapUtils;

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.list_item_category);
		tvName1 = (TextView) view.findViewById(R.id.tv_name1);
		tvName2 = (TextView) view.findViewById(R.id.tv_name2);
		tvName3 = (TextView) view.findViewById(R.id.tv_name3);

		ivIcon1 = (ImageView) view.findViewById(R.id.iv_icon1);
		ivIcon2 = (ImageView) view.findViewById(R.id.iv_icon2);
		ivIcon3 = (ImageView) view.findViewById(R.id.iv_icon3);

		llGride1 = (LinearLayout) view.findViewById(R.id.ll_grid1);
		llGride2 = (LinearLayout) view.findViewById(R.id.ll_grid2);
		llGride3 = (LinearLayout) view.findViewById(R.id.ll_grid3);
		mBitmapUtils = BitmapHelper.getBitmapUtils();

		llGride1.setOnClickListener(this);
		llGride2.setOnClickListener(this);
		llGride3.setOnClickListener(this);
		return view;
	}

	@Override
	public void refreshView(CategoryInfo data) {
		tvName1.setText(data.name1);
		tvName2.setText(data.name2);
		tvName3.setText(data.name3);
		mBitmapUtils.display(ivIcon1, HttpHelper.URL + "image?name="
				+ data.url1);
		mBitmapUtils.display(ivIcon2, HttpHelper.URL + "image?name="
				+ data.url2);
		mBitmapUtils.display(ivIcon3, HttpHelper.URL + "image?name="
				+ data.url3);
	}

	@Override
	public void onClick(View v) {
		CategoryInfo info = getData();
		switch (v.getId()) {
		case R.id.ll_grid1:
			Toast.makeText(UIUtils.getContext(), info.name1, Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.ll_grid2:
			Toast.makeText(UIUtils.getContext(), info.name2, Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.ll_grid3:
			Toast.makeText(UIUtils.getContext(), info.name3, Toast.LENGTH_SHORT)
					.show();
			break;

		default:
			break;
		}
	}

}
