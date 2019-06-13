package com.kkb.mybatis.framework.sqlsession;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.kkb.mybatis.framework.builder.BoundSql;
import com.kkb.mybatis.framework.builder.Configuration;
import com.kkb.mybatis.framework.builder.MappedStatement;
import com.kkb.mybatis.framework.builder.SqlSource;

public class SimpleExecutor implements Executor {

	@Override
	public <T> List<T> execute(MappedStatement mappedStatement, Configuration configuration, Object parameterObject) {
		List<T> list = new ArrayList<>();
		try {
			SqlSource sqlSource = mappedStatement.getSqlSource();
			BoundSql boundSql = sqlSource.getBoundSql(mappedStatement, configuration, parameterObject);

			String statementType = mappedStatement.getStatementType();
			StatementHandler statementHandler = null;
			if (statementType.equals("prepared")) {
				statementHandler = new PreparedStatementHandler(configuration, parameterObject);
				PreparedStatement statement = (PreparedStatement) statementHandler.getStatement(boundSql.getSql());
				statementHandler.setParameter(statement, boundSql);
				ResultSet rs = statementHandler.execute(statement);
				list = handleResultSet(rs, mappedStatement);
			} else if (statementType.equals("statement")) {
				// statementHandler = new StatementHandler();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private <T> List<T> handleResultSet(ResultSet rs, MappedStatement mappedStatement) {
		List<T> results = new ArrayList<>();
		try {
			Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
			while (rs.next()) {
				Object resultObject = resultTypeClass.newInstance();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					Field field = resultTypeClass.getDeclaredField(metaData.getColumnLabel(i));
					field.setAccessible(true);
					field.set(resultObject, rs.getObject(i));
					System.out.println(metaData.getColumnLabel(i) + "---" + rs.getObject(i) + "----"
							+ metaData.getColumnClassName(i));
				}
				
				results.add((T) resultObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return results;
	}

}
