package com.example.mygoogleplay.http.protocol;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class RecommendProtocol extends BaseProtocol<ArrayList<String>> {

	@Override
	public String getKey() {
		return "recommend";
	}

	@Override
	public String getParam() {
		return "";
	}

	@Override
	public ArrayList<String> parseData(String result) {
		try {
			JSONArray ja = new JSONArray(result);
			ArrayList<String> list = new ArrayList<String>();
			for (int i = 0; i < ja.length(); i++) {
				list.add(ja.getString(i));
			}
			return list;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
