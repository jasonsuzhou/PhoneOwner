package com.mh.mobile.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import com.mh.mobile.util.CSVFileUtils;

public class ConvertToSQLFile {
	public static void genSQLFile(String sourceFile, String targetFile) throws Exception {
		System.out.println("===== begin generate sql file =====");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(targetFile), false)));
		CSVFileUtils util = new CSVFileUtils(sourceFile);
		String lineData = null;
		while ((lineData = util.readLine()) != null) {
			StringBuilder sb = new StringBuilder(128);
			List<String> list = CSVFileUtils.fromCSVLinetoArray(lineData);
			sb.append("insert into `pro_phone_owner`(`section`,`province`,`city`,`vendor`,`area_code`,`zip_code`) values");
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
			sb.append(");\r\n");
			pw.print(sb.toString());
		}
		pw.close();
		System.out.println("===== end generate sql file =====");
	}
}
