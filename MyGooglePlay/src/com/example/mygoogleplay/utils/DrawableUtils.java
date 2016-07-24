package com.example.mygoogleplay.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class DrawableUtils {
	/**
	 * 设置圆角矩形
	 * 
	 * @param radius
	 * @param color
	 * @return
	 */
	public static GradientDrawable getGradientDrawable(int radius, int color) {
		GradientDrawable shape = new GradientDrawable();
		shape.setShape(GradientDrawable.RECTANGLE);
		shape.setCornerRadius(radius);
		shape.setColor(color);
		return shape;
	}

	/**
	 * 设置状态选择器
	 * 
	 * @param nomal
	 * @param press
	 */
	public static StateListDrawable getSelector(Drawable nomal, Drawable press) {
		StateListDrawable selector = new StateListDrawable();
		selector.addState(new int[] { android.R.attr.state_pressed }, press);
		selector.addState(new int[] {}, nomal);
		return selector;
	}

	/**
	 * 设置点击效果
	 * 
	 * @param normal
	 * @param press
	 * @param radius
	 * @return
	 */
	public static StateListDrawable getSelector(int normal, int press,
			int radius) {
		GradientDrawable bgNormal = getGradientDrawable(radius, normal);
		GradientDrawable bgPress = getGradientDrawable(radius, press);
		StateListDrawable selector = getSelector(bgNormal, bgPress);
		return selector;
	}
}
