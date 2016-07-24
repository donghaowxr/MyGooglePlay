package com.example.mygoogleplay.http.protocol;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.mygoogleplay.domain.CategoryInfo;

public class CategoryProtocol extends BaseProtocol<ArrayList<CategoryInfo>> {

	@Override
	public String getKey() {
		return "category";
	}

	@Override
	public String getParam() {
		return "";
	}

	@Override
	public ArrayList<CategoryInfo> parseData(String result) {
		try {
			JSONArray jArray = new JSONArray(result);
			ArrayList<CategoryInfo> list = new ArrayList<CategoryInfo>();
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject jObject = jArray.getJSONObject(i);
				if (jObject.has("title")) {
					CategoryInfo info = new CategoryInfo();
					info.title = jObject.getString("title");
					info.isTitle = true;
					list.add(info);
				}
				if (jObject.has("infos")) {
					JSONArray jArray2 = jObject.getJSONArray("infos");
					for (int j = 0; j < jArray2.length(); j++) {
						JSONObject jObject2 = jArray2.getJSONObject(j);
						CategoryInfo info = new CategoryInfo();
						info.name1 = jObject2.getString("name1");
						info.name2 = jObject2.getString("name2");
						info.name3 = jObject2.getString("name3");
						info.url1 = jObject2.getString("url1");
						info.url2 = jObject2.getString("url2");
						info.url3 = jObject2.getString("url3");
						info.isTitle = false;
						list.add(info);
					}
				}
			}
			return list;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
