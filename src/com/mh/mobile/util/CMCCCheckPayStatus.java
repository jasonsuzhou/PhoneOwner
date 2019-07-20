package com.mh.mobile.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CMCCCheckPayStatus {
	
	public static void main(String args[]) throws Exception {
		CSVFileUtils util = new CSVFileUtils("C:\\Users\\jason.yao\\Desktop\\sqlresult_3950853.csv");
		String lineData = null;
		while ((lineData = util.readLine()) != null) {
			List<String> list = CSVFileUtils.fromCSVLinetoArray(lineData);
			String result = list.get(3);
			boolean result1 = checkPayStatus(result);
			if (result1) {
				System.out.println(list.get(1)+","+result1);
			}
			
		}
	}
	
	public static boolean checkPayStatus(String orderId) throws Exception  {
		String result = accessAfterPayUrl(orderId);
		if (result != null && result.length()>0) {
            return true;
		} else {
			return false;
		}
	}
	
	public static String accessAfterPayUrl(String jobId) throws Exception {
		String location = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		HttpURLConnection conn = null;
		try {
			URL url = new URL("https://pay.shop.10086.cn/paygw/WXafterpayeBack?orderId="+jobId);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setInstanceFollowRedirects(false);
			
			int responseCode = conn.getResponseCode();
			if (200 == responseCode) {
				inputStream = conn.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
				bufferedReader = new BufferedReader(inputStreamReader);
				String str = null;
				StringBuilder sb = new StringBuilder("");
				while ((str = bufferedReader.readLine()) != null) {
					sb.append(str);
				}
				location = sb.toString();
			} else if (302 == responseCode) {
				location = conn.getHeaderField("Location");
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null)
			bufferedReader.close();
			if (inputStreamReader != null) inputStreamReader.close();
			if (inputStream != null) inputStream.close();
			if (conn != null) {
				conn.disconnect();
			}
		}
		return location;
	}
	

}
