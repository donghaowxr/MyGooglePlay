package com.example.mygoogleplay.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class RatioLayout extends FrameLayout {
	private final static String namespace="http://schemas.android.com/apk/res/com.example.mygoogleplay";
	public RatioLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public RatioLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		float ratio=attrs.getAttributeFloatValue(namespace, "ratio", -1);
		System.out.println(ratio);
	}

	public RatioLayout(Context context) {
		super(context);
	}

}
