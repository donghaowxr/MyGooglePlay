package com.example.mygoogleplay.http.protocol;

import com.example.mygoogleplay.http.HttpHelper;
import com.example.mygoogleplay.http.HttpHelper.HttpResult;

public abstract class BaseProtocol {
	public void getData(int index) {
		getDataFromServer(index);
	}

	private void getDataFromServer(int index) {
		HttpResult httpResult = HttpHelper.get(HttpHelper.URL + getKey()
				+ "?index=" + index + getParam());
		if (httpResult != null) {
			String result = httpResult.getString();
		}
	}

	public abstract String getKey();// 获取网络请求关键字

	public abstract String getParam();// 获取网络请求参数
}
