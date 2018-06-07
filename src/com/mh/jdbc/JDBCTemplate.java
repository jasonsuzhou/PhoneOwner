package com.mh.jdbc;

import javax.sql.DataSource;

public class JDBCTemplate {

	private DataSource dataSource;

	public JDBCTemplate(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
