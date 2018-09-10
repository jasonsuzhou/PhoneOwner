package com.mh.mobile.util;

import java.util.HashSet;
import java.util.Set;

public class SectionSet {
	
	private static Set<String> set = new HashSet<String>(500000);
	
	public static void setSection(String section) {
		set.add(section);
	}
	
	public static boolean isExist(String section) {
		boolean exist = set.contains(section);
		if (exist) {
			System.out.println("[Warn] There is duplicate section::" + section);
		}
		return exist;
	}

}
