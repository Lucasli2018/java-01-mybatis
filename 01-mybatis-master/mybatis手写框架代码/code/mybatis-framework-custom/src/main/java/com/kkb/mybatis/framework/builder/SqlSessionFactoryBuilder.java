package com.kkb.mybatis.framework.builder;

import java.io.InputStream;

import com.kkb.mybatis.framework.sqlsession.DefaultSqlSessionFactory;
import com.kkb.mybatis.framework.sqlsession.SqlSessionFactory;

public class SqlSessionFactoryBuilder {

	public SqlSessionFactory build(InputStream inputStream) {
		XmlConfigBuilder builder = new XmlConfigBuilder(inputStream);
		return new DefaultSqlSessionFactory(builder.parse());
	}
}
