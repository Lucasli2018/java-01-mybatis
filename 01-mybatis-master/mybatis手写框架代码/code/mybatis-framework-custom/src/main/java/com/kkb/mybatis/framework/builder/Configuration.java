package com.kkb.mybatis.framework.builder;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

public class Configuration {

	private DataSource dataSource;

	private Map<String, MappedStatement> mappedStatements = new HashMap<>();

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public MappedStatement getMappedStatement(String statementId) {
		return mappedStatements.get(statementId);
	}

	public void addMappedStatement(MappedStatement mappedStatement) {
		this.mappedStatements.put(mappedStatement.getId(), mappedStatement);
	}
}
