package com.example.mygoogleplay.http.protocol;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class HotProtocol extends BaseProtocol<ArrayList<String>> {

	@Override
	public String getKey() {
		return "hot";
	}

	@Override
	public String getParam() {
		return "";
	}

	@Override
	public ArrayList<String> parseData(String result) {
		try {
			JSONArray jArray = new JSONArray(result);
			ArrayList<String> list = new ArrayList<String>();
			for (int i = 0; i < jArray.length(); i++) {
				list.add(jArray.getString(i));
			}
			return list;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
