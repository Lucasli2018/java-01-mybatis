package com.kkb.mybatis.framework.sqlsession;

import com.kkb.mybatis.framework.builder.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

	private Configuration configuration;

	public DefaultSqlSessionFactory(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public SqlSession openSqlSession() {
		return new DefaultSqlSession(configuration);
	}

}
