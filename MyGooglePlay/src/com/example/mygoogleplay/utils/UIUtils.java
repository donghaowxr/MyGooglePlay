package com.example.mygoogleplay.utils;

import com.example.mygoogleplay.global.GooglePlayApplication;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

public class UIUtils {
	/**
	 * 获取Context
	 * 
	 * @return
	 */
	public static Context getContext() {
		return GooglePlayApplication.getContext();
	}

	/**
	 * 获取Handler
	 * 
	 * @return
	 */
	public static Handler getHandler() {
		return GooglePlayApplication.getHandler();
	}

	/**
	 * 获取主线程id
	 * 
	 * @return
	 */
	public static int getMyTid() {
		return GooglePlayApplication.getMyTid();
	}

	/**
	 * 获取字符串
	 * 
	 * @param id
	 * @return
	 */
	public static String getString(int id) {
		return getContext().getResources().getString(id);
	}

	/**
	 * 获取字符串数组
	 * 
	 * @param id
	 * @return
	 */
	public static String[] getStringArray(int id) {
		return getContext().getResources().getStringArray(id);
	}

	/**
	 * 获取图片
	 * 
	 * @param id
	 * @return
	 */
	public static Drawable getDrawable(int id) {
		return getContext().getResources().getDrawable(id);
	}

	/**
	 * 获取颜色
	 * 
	 * @param id
	 * @return
	 */
	public static int name(int id) {
		return getContext().getResources().getColor(id);
	}

	/**
	 * 获取px尺寸
	 * 
	 * @param id
	 * @return
	 */
	public static int getDimen(int id) {
		return getContext().getResources().getDimensionPixelSize(id);
	}

	/**
	 * dpi转px
	 * 
	 * @param dpi
	 * @return
	 */
	public static int dpi2px(float dpi) {
		float density = getContext().getResources().getDisplayMetrics().density;
		return (int) (dpi * density + 0.5f);
	}

	/**
	 * px转dpi
	 * 
	 * @param px
	 * @return
	 */
	public static float px2dpi(int px) {
		float density = getContext().getResources().getDisplayMetrics().density;
		return px / density;
	}

	/**
	 * 布局初始化
	 * 
	 * @param id
	 * @return
	 */
	public static View inflate(int id) {
		return View.inflate(getContext(), id, null);
	}

	/**
	 * 判断当前是否为ui线程
	 * 
	 * @return
	 */
	public static boolean isRunOnUIThread() {
		int myTid = android.os.Process.myTid();
		return getMyTid() == myTid;
	}

	/**
	 * 在主线程中运行
	 * 
	 * @param r
	 */
	public static void runOnUIThread(Runnable r) {
		if (isRunOnUIThread()) {
			r.run();
		} else {
			getHandler().post(r);
		}
	}
}
