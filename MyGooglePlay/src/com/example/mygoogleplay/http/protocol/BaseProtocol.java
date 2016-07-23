package com.example.mygoogleplay.http.protocol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.example.mygoogleplay.http.HttpHelper;
import com.example.mygoogleplay.http.HttpHelper.HttpResult;
import com.example.mygoogleplay.utils.IOUtils;
import com.example.mygoogleplay.utils.StringUtils;
import com.example.mygoogleplay.utils.UIUtils;

public abstract class BaseProtocol<T> {
	public void getData(int index) {
		String result=getCache(index);
		if (!StringUtils.isEmpty(result)) {//如果没有缓存或者缓存失效则请求网络
			result= getDataFromServer(index);
		}
		if (result!=null) {
			parseData(result);
		}
	}

	private String getDataFromServer(int index) {
		HttpResult httpResult = HttpHelper.get(HttpHelper.URL + getKey()
				+ "?index=" + index + getParam());
		if (httpResult != null) {
			String result = httpResult.getString();
			if (!StringUtils.isEmpty(result)) {
				setCache(index, result);
				return result;
			}
		}
		return null;
	}

	public abstract String getKey();// 获取网络请求关键字

	public abstract String getParam();// 获取网络请求参数
	public abstract T parseData(String result);

	/**
	 * 设置缓存
	 * @param index
	 * @param json
	 */
	private void setCache(int index, String json) {
		File cacheDir = UIUtils.getContext().getCacheDir();
		File cacheFile = new File(cacheDir, "?index=" + index + getParam());
		FileWriter writer = null;
		try {
			writer = new FileWriter(cacheFile);
			long deadline = System.currentTimeMillis() + 30 * 60 * 1000;
			writer.write(deadline + "\n");
			writer.write(json);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(writer);
		}
	}
	
	/**
	 * 获取缓存
	 * @param index
	 * @return
	 */
	private String getCache(int index) {
		File cacheDir = UIUtils.getContext().getCacheDir();
		File cacheFile = new File(cacheDir, "?index=" + index + getParam());
		if (cacheFile.exists()) {//判断缓存是否存在
			BufferedReader reader=null;
			try {
				reader=new BufferedReader(new FileReader(cacheFile));
				String deadLine=reader.readLine();
				long deadTime=Long.parseLong(deadLine);
				if (System.currentTimeMillis()<deadTime) {//当当前时间小于有效时间时。该缓存有效
					StringBuffer sb=new StringBuffer();
					String line;
					while ((line=reader.readLine())!= null) {
						sb.append(line);
					}
					return sb.toString();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				IOUtils.close(reader);
			}
		}
		return null;
	}
}
