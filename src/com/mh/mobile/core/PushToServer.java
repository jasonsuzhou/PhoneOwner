package com.mh.mobile.core;

import java.util.List;

import com.mh.mobile.util.CSVFileUtils;
import com.mh.mobile.util.HttpClient;
import com.mh.mobile.util.HttpHeader;

public class PushToServer {

	public static void push(String sourceFile, String url) throws Exception {
		CSVFileUtils util = new CSVFileUtils(sourceFile);
		String lineData = null;
		while ((lineData = util.readLine()) != null) {
			StringBuilder sb = new StringBuilder(128);
			List<String> list = CSVFileUtils.fromCSVLinetoArray(lineData);
			sb.append("{");
			sb.append("\"section\":\""+list.get(1)+"\",");
			sb.append("\"province\":\""+list.get(2)+"\",");
			sb.append("\"city\":\""+list.get(3)+"\",");
			sb.append("\"vendor\":\""+list.get(4)+"\",");
			if (list.get(5).length()> 4) {
				sb.append("\"areaCode\":\""+list.get(5).substring(0, 4)+"\",");
			} else {
				sb.append("\"areaCode\":\""+list.get(5)+"\",");
			}
			if (list.get(6).length()> 6) {
				sb.append("\"zipCode\":\""+list.get(6).substring(0, 6)+"\"");
			} else {
				sb.append("\"zipCode\":\""+list.get(6)+"\"");
			}
			sb.append("}");
			if (url.startsWith("https")) {
				HttpClient.sendPostRquestSSL(url, sb.toString(), HttpHeader.getJsonHeader());
			} else {
				HttpClient.sendPostRquest(url, sb.toString(), HttpHeader.getJsonHeader());
			}
		}
	}

}
