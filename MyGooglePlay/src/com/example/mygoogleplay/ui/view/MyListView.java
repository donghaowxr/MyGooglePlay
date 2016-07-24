package com.example.mygoogleplay.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyListView extends ListView {

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public MyListView(Context context) {
		super(context);
		initView();
	}

	private void initView() {
		this.setDivider(null);
		this.setSelector(new ColorDrawable());
		this.setCacheColorHint(Color.TRANSPARENT);
	}
}
