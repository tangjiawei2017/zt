package com.shop.framework.dataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;

import com.shop.framework.context.ContextHolder;

/**
 * 多数据源配置
 *
 */
public class MultiDataSource implements DataSource {
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*
	 * 此处配置数据库的连接 siteId
	 */
	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = this.getDataSource().getConnection();
		String siteId = ContextHolder.getContext().getSiteId();
		if (StringUtils.isNotEmpty(siteId)) {
			connection.setCatalog(siteId);
		}
		return connection;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		Connection connection = this.getDataSource().getConnection(username, password);
		String siteId = ContextHolder.getContext().getSiteId();
		if (StringUtils.isNotEmpty(siteId)) {
			connection.setCatalog(siteId);
		}
		return connection;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return dataSource.getLogWriter();
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		dataSource.setLogWriter(out);
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		dataSource.setLoginTimeout(seconds);
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return dataSource.getLoginTimeout();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return dataSource.getParentLogger();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return dataSource.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return dataSource.isWrapperFor(iface);
	}
}
