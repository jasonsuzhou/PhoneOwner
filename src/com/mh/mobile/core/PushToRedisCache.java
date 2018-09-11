package com.mh.mobile.core;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.mh.mobile.bean.PhoneOwner;
import com.mh.mobile.util.CSVFileUtils;
import com.mh.redis.BatchSetCommands;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.dynamic.RedisCommandFactory;

public class PushToRedisCache {
	
	/**
	 * 
	 * @param sourceFile 
	 * @param link the redis connection link, host::port:database::password
	 * @param keyPrefix key prefix if have default to empty. <prefix>:1811271, will combine this value and phone section as redis key
	 * @throws Exception
	 */
	public static void push(String sourceFile, String link, String keyPrefix) throws Exception {
		long startTime = System.currentTimeMillis();
		System.out.println("Start Time:" + startTime);
		if (keyPrefix == null) { 
			keyPrefix = "";
		}
		String[] args = link.split("::");
		String host = args[0];
		String post = args[1];
		String database = args[2];
		String password = "";
		if (args.length > 3) {
			password = args[3] == null ? "" : args[3];
		}
		String redisURI = "redis://"+password+"@"+host+":"+post+"/"+database;
		RedisClient redisClient = RedisClient.create(redisURI);
		StatefulRedisConnection<String, String> connection = redisClient.connect();
		RedisCommandFactory factory = new RedisCommandFactory(connection);
		BatchSetCommands commands = factory.getCommands(BatchSetCommands.class);
		CSVFileUtils util = new CSVFileUtils(sourceFile);
		String lineData = null;
		String section = null;
		while ((lineData = util.readLine()) != null) {
			List<String> list = CSVFileUtils.fromCSVLinetoArray(lineData);
			section = keyPrefix + list.get(1);
			PhoneOwner phoneOwner = new PhoneOwner();
			phoneOwner.setSection(section);
			phoneOwner.setProvince(list.get(2));
			phoneOwner.setCity(list.get(3));
			phoneOwner.setVendor(list.get(4));
			if (list.get(5).length() > 4) {
				phoneOwner.setAreaCode(list.get(5).substring(0, 4));
			} else {
				phoneOwner.setAreaCode(list.get(5));
			}
			if (list.get(6).length() > 6) {
				phoneOwner.setZipCode(list.get(6).substring(0, 6));
			} else {
				phoneOwner.setZipCode(list.get(6));
			}
			commands.set(section, JSON.toJSONString(phoneOwner));
		}
		connection.close();
		redisClient.shutdown();
		long endTime = System.currentTimeMillis();
		System.out.println("Total Time Seconds:" + (endTime - startTime)/1000);
	}
	
	public static void main(String[] args) throws Exception {
		String fileName = "I:\\jar_lib\\Mobile.20180405.387695.csv\\Mobile.csv";
		String link = "192.168.1.103::6379::0";
		PushToRedisCache.push(fileName, link, null);
	}
}
