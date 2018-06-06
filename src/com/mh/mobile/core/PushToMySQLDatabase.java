package com.mh.mobile.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import com.mh.mobile.util.CSVFileUtils;

public class PushToMySQLDatabase {

	public static void push(String sourceFile, String link) throws Exception {
		String[] links = link.split("::");
		String url = links[0]+"?characterEncoding=utf-8&useOldAliasMetadataBehavior=true";
		String username = links[1];
		String password = links[2];
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement stmt = conn.createStatement();
		CSVFileUtils util = new CSVFileUtils(sourceFile);
		String lineData = null;
		int index = 0;
		while ((lineData = util.readLine()) != null) {
			System.out.println("===== begin read line =====");
			StringBuilder sb = new StringBuilder(128);
			List<String> list = CSVFileUtils.fromCSVLinetoArray(lineData);
			sb.append("insert into owner(`section`,`province`,`city`,`vendor`,`area_code`,`zip_code`) values");
			sb.append("(");
			sb.append("'").append(list.get(1)).append("',");
			sb.append("'").append(list.get(2)).append("',");
			sb.append("'").append(list.get(3)).append("',");
			sb.append("'").append(list.get(4)).append("',");
			if (list.get(5).length()> 4) {
				sb.append("'").append(list.get(5).substring(0, 4)).append("',");
			} else {
				sb.append("'").append(list.get(5)).append("',");
			}
			if (list.get(6).length()> 6) {
				sb.append("'").append(list.get(6).substring(0, 6)).append("'");
			} else {
				sb.append("'").append(list.get(6)).append("'");
			}
			sb.append(");");
			stmt.addBatch(sb.toString());
			System.out.println("===== end read line ::  " + index + "=====");
			index ++;
			if (index == 5000) {
				index = 0;
				System.out.println("===== begin execute batch insert =====");
				stmt.executeBatch();
				System.out.println("===== end execute batch insert =====");
			} 
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

}
