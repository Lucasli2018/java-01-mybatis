package com.kkb.mybatis.framework.sqlsession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import com.kkb.mybatis.framework.builder.BoundSql;
import com.kkb.mybatis.framework.builder.Configuration;
import com.kkb.mybatis.framework.builder.ParameterMapping;

public class PreparedStatementHandler implements StatementHandler {

	private Configuration configuration;

	private Object parameterObject;

	public PreparedStatementHandler(Configuration configuration, Object parameterObject) {
		this.configuration = configuration;
		this.parameterObject = parameterObject;
	}

	@Override
	public Statement getStatement(String sql) {
		PreparedStatement statement = null;
		try {
			DataSource dataSource = configuration.getDataSource();
			Connection connection = dataSource.getConnection();
			statement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}

	@Override
	public void setParameter(Statement statement, BoundSql boundSql) {
		PreparedStatement preparedStatement = (PreparedStatement) statement;
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		for (int i = 0; i < parameterMappings.size(); i++) {
			Class<?> parameterTypeClass = parameterMappings.get(i).getParameterTypeClass();
			TypeHandler typeHandler = getTypeHandler(parameterTypeClass);
			typeHandler.setParameter(i + 1, preparedStatement, parameterObject);
		}
	}

	private TypeHandler getTypeHandler(Class<?> parameterTypeClass) {
		return new IntegerTypeHandler();
	}

	@Override
	public ResultSet execute(Statement statement) {
		ResultSet rs = null;
		try {
			rs = ((PreparedStatement) statement).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
