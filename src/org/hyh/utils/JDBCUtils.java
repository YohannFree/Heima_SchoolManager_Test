package org.hyh.utils;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	private static DataSource dataSource = new ComboPooledDataSource();

	public static DataSource getDateSource() {
		return dataSource;
	}

	@Test
	public void test() {
		new QueryRunner(getDateSource());
	}
}
