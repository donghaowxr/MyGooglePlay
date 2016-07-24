package com.example.mygoogleplay.http.protocol;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.mygoogleplay.domain.AppInfo;

public class AppProtocol extends BaseProtocol<ArrayList<AppInfo>> {

	@Override
	public String getKey() {
		return "app";
	}

	@Override
	public String getParam() {
		return "";
	}

	@Override
	public ArrayList<AppInfo> parseData(String result) {
		try {
			JSONArray ja = new JSONArray(result);
			ArrayList<AppInfo> appInfoList = new ArrayList<AppInfo>();
			for (int i = 0; i < ja.length(); i++) {
				JSONObject joList = ja.getJSONObject(i);
				AppInfo info = new AppInfo();
				info.des = joList.getString("des");
				info.downloadUrl = joList.getString("downloadUrl");
				info.iconUrl = joList.getString("iconUrl");
				info.id = joList.getString("id");
				info.name = joList.getString("name");
				info.packageName = joList.getString("packageName");
				info.size = joList.getLong("size");
				info.stars = (float) joList.getDouble("stars");
				appInfoList.add(info);
			}
			return appInfoList;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
