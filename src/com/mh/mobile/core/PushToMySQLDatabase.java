package com.mh.mobile.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mh.jdbc.pool.JDBCConnectionPool;
import com.mh.mobile.util.CSVFileUtils;
import com.mh.mobile.util.SectionSet;

public class PushToMySQLDatabase {

	public static void push(String sourceFile, String link) throws Exception {
		final DataSource ds = JDBCConnectionPool.init(link);
		CSVFileUtils util = new CSVFileUtils(sourceFile);
		String lineData = null;
		int index = 0;
		List<String> sqls = null;
		while ((lineData = util.readLine()) != null) {
			if (index == 0) {
				sqls = new ArrayList<String>(5000);
			}
			System.out.println("===== begin read line =====");
			StringBuilder sb = new StringBuilder(128);
			List<String> list = CSVFileUtils.fromCSVLinetoArray(lineData);
			sb.append(
					"insert into phone_owner(`section`,`province`,`city`,`vendor`,`area_code`,`zip_code`) values");
			sb.append("(");
			sb.append("'").append(list.get(1)).append("',");
			sb.append("'").append(list.get(2)).append("',");
			sb.append("'").append(list.get(3)).append("',");
			sb.append("'").append(list.get(4)).append("',");
			if (list.get(5).length() > 4) {
				sb.append("'").append(list.get(5).substring(0, 4)).append("',");
			} else {
				sb.append("'").append(list.get(5)).append("',");
			}
			if (list.get(6).length() > 6) {
				sb.append("'").append(list.get(6).substring(0, 6)).append("'");
			} else {
				sb.append("'").append(list.get(6)).append("'");
			}
			sb.append(");");
			index++;
			if (!SectionSet.isExist(list.get(1))) {
				sqls.add(sb.toString());
				SectionSet.setSection(list.get(1));
			} 
			System.out.println("===== end read line ::  " + index + "=====");
			if (index == 5000) {
				index = 0;
				insertIntoDatabase(sqls, ds);
			}
		}
		// The last cycle may not have 5000 records
		if (sqls.size() < 5000) {
			insertIntoDatabase(sqls, ds);
		}
	}

	private static void insertIntoDatabase(final List<String> sqls, final DataSource ds) {
		System.out.println("begin insert into database");
		new Thread(new Runnable() {

			@Override
			public void run() {
				Connection conn = null;
				Statement stmt = null;
				try {
					conn = ds.getConnection();
					stmt = conn.createStatement();
					for (String sql : sqls) {
						stmt.addBatch(sql);
					}
					System.out.println("begin execute batch insert");
					stmt.executeBatch();
					System.out.println("end execute batach insert");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmt != null) {
						try {
							stmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
		System.out.println("end insert into database");
	}
	
}
