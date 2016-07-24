package com.example.mygoogleplay.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class RatioLayout extends FrameLayout {
	private final static String namespace="http://schemas.android.com/apk/res/com.example.mygoogleplay";
	private float ratio;
	public RatioLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public RatioLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		ratio = attrs.getAttributeFloatValue(namespace, "ratio", -1);
	}

	public RatioLayout(Context context) {
		super(context);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode= MeasureSpec.getMode(widthMeasureSpec);//获取控件宽度模式
		int width=MeasureSpec.getSize(widthMeasureSpec);//获取控件宽度真实值
		int heightMode=MeasureSpec.getMode(heightMeasureSpec);
		int height=MeasureSpec.getSize(heightMeasureSpec);
		if (widthMode==MeasureSpec.EXACTLY&&heightMode!=MeasureSpec.EXACTLY&&ratio>0) {
			int imageWidth=width-getPaddingLeft()-getPaddingRight();
			int imageHeight=(int) (imageWidth/ratio+0.5f);
			height=imageHeight+getPaddingTop()+getPaddingBottom();
		}
		heightMeasureSpec=MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
