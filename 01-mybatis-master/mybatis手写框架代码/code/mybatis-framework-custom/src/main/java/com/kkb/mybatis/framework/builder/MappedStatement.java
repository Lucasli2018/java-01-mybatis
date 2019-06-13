package com.kkb.mybatis.framework.builder;

public class MappedStatement {

	private Configuration configuration;

	private String id;
	private String statementType;
	private SqlSource sqlSource;
	private Class<?> parameterTypeClass;
	private Class<?> resultTypeClass;

	public MappedStatement(Configuration configuration, String id, Class<?> parameterTypeClass,
			Class<?> resultTypeClass, String statementType, SqlSource sqlSource) {
		this.configuration = configuration;
		this.id = id;
		this.sqlSource = sqlSource;
		this.statementType = statementType;
		this.parameterTypeClass = parameterTypeClass;
		this.resultTypeClass = resultTypeClass;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatementType() {
		return statementType;
	}

	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}

	public SqlSource getSqlSource() {
		return sqlSource;
	}

	public void setSqlSource(SqlSource sqlSource) {
		this.sqlSource = sqlSource;
	}

	public Class<?> getParameterTypeClass() {
		return parameterTypeClass;
	}

	public void setParameterTypeClass(Class<?> parameterTypeClass) {
		this.parameterTypeClass = parameterTypeClass;
	}

	public Class<?> getResultTypeClass() {
		return resultTypeClass;
	}

	public void setResultTypeClass(Class<?> resultTypeClass) {
		this.resultTypeClass = resultTypeClass;
	}

	
}
