package com.example.mygoogleplay.http.protocol;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.mygoogleplay.domain.AppInfo;

public class HomeProtocol extends BaseProtocol<ArrayList<AppInfo>> {

	private ArrayList<String> pictures;

	@Override
	public String getKey() {
		return "home";
	}

	@Override
	public String getParam() {
		return "";// 由于需要字符串拼接，所以不能返回null
	}

	@Override
	public ArrayList<AppInfo> parseData(String result) {
		try {
			JSONObject jo = new JSONObject(result);
			JSONArray ja = jo.getJSONArray("list");
			ArrayList<AppInfo> appinfoList = new ArrayList<AppInfo>();
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
				appinfoList.add(info);
			}
			JSONArray jaPic = jo.getJSONArray("picture");
			pictures = new ArrayList<String>();
			for (int i = 0; i < jaPic.length(); i++) {
				String pic = jaPic.getString(i);
				pictures.add(pic);
			}
			return appinfoList;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getPictureList() {
		return pictures;
	}
}
