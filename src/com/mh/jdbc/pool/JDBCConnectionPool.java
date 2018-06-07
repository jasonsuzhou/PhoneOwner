package com.mh.jdbc.pool;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JDBCConnectionPool {

	/**
	 * 
	 * @param connString
	 *            host::port::db::user:password
	 */
	public static synchronized DataSource init(String connString) {
		String[] values = connString.split("::");
		String host = values[0];
		String port = values[1];
		String dbName = values[2];
		String user = values[3];
		String password = values[4];
		return initConfig(host, Integer.parseInt(port), dbName, user, password);
	}

	public synchronized static DataSource initConfig(String host, int port, String dbName, String user, String pass) {
		HikariConfig config = new HikariConfig();
		config.setMaximumPoolSize(100);
		config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
		config.addDataSourceProperty("serverName", host);
		config.addDataSourceProperty("port", port);
		config.addDataSourceProperty("databaseName", dbName);
		config.addDataSourceProperty("user", user);
		config.addDataSourceProperty("password", pass);
		config.addDataSourceProperty("useUnicode", "true");
		config.addDataSourceProperty("characterEncoding", "utf8");
		HikariDataSource ds = new HikariDataSource(config);
		return ds;
	}

}
