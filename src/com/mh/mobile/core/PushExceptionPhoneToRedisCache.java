package com.mh.mobile.core;

import com.mh.mobile.util.CSVFileUtils;
import com.mh.redis.BatchSetCommands;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.dynamic.RedisCommandFactory;

/**
 * 用户异常的号码放到redis里面去
 * @author 
 *
 */
public class PushExceptionPhoneToRedisCache {
	
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
		while ((lineData = util.readLine()) != null) {
			commands.set(lineData, "0");
		}
		connection.close();
		redisClient.shutdown();
		long endTime = System.currentTimeMillis();
		System.out.println("Total Time Seconds:" + (endTime - startTime)/1000);
	}
	
	public static void main(String[] args) throws Exception {
		String fileName = "test.csv";
		String link = "127.0.0.1::6379::0";
		PushExceptionPhoneToRedisCache.push(fileName, link, null);
	}
}
