package com.example.mygoogleplay.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

public class GooglePlayApplication extends Application {
	private static Context context;
	private static Handler handler;
	private static int myTid;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		handler = new Handler();
		myTid = android.os.Process.myTid();
	}

	public static Context getContext() {
		return context;
	}

	public static Handler getHandler() {
		return handler;
	}

	public static int getMyTid() {
		return myTid;
	}

}
