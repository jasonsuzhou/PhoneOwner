package com.mh.mobile.util;

import java.util.HashSet;
import java.util.Set;

public class SectionSet {
	
	private static Set<String> set = new HashSet<String>();
	
	public static void setSection(String section) {
		set.add(section);
	}
	
	public boolean isExist(String section) {
		System.out.println("[Warn] There is duplicate section::" + section);
		return set.contains(section);
	}

}
