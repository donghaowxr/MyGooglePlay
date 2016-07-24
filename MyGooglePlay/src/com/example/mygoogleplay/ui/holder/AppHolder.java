package com.example.mygoogleplay.ui.holder;

import com.example.mygoogleplay.R;
import com.example.mygoogleplay.domain.AppInfo;
import com.example.mygoogleplay.http.HttpHelper;
import com.example.mygoogleplay.utils.BitmapHelper;
import com.example.mygoogleplay.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class AppHolder extends BaseHolder<AppInfo> {

	private TextView tvShow;
	private ImageView ivIcon;
	private TextView tvName;
	private TextView tvSize;
	private TextView tvDes;
	private RatingBar rbStar;
	private BitmapUtils mBitmapUtils;

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.list_item_home);
		ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
		tvName = (TextView) view.findViewById(R.id.tv_name);
		tvSize = (TextView) view.findViewById(R.id.tv_size);
		tvDes = (TextView) view.findViewById(R.id.tv_des);
		rbStar = (RatingBar) view.findViewById(R.id.rb_star);
		mBitmapUtils = BitmapHelper.getBitmapUtils();
		mBitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_default);
		return view;
	}

	@Override
	public void refreshView(AppInfo data) {
		tvName.setText(data.name);
		tvSize.setText(Formatter.formatFileSize(UIUtils.getContext(), data.size));
		tvDes.setText(data.des);
		rbStar.setRating(data.stars);
		mBitmapUtils.display(ivIcon, HttpHelper.URL + "image?name="
				+ data.iconUrl);
	}

}
