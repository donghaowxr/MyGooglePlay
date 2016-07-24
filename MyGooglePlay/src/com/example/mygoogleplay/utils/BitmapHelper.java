package com.example.mygoogleplay.utils;

import com.lidroid.xutils.BitmapUtils;

public class BitmapHelper {
	private static BitmapUtils mBitmapUtils = null;

	/**
	 * 创建单例模式BitmapUtils，懒汉模式
	 * 
	 * @return
	 */
	public static BitmapUtils getBitmapUtils() {
		if (mBitmapUtils == null) {
			synchronized (BitmapHelper.class) {
				if (mBitmapUtils == null) {
					mBitmapUtils = new BitmapUtils(UIUtils.getContext());
				}
			}
		}
		return mBitmapUtils;
	}
}
