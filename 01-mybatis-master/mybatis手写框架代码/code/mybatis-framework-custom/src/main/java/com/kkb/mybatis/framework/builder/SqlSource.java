package com.kkb.mybatis.framework.builder;

import java.util.ArrayList;
import java.util.List;

import com.kkb.mybatis.framework.sqlsession.GenericTokenParser;
import com.kkb.mybatis.framework.sqlsession.ParameterMappingTokenHandler;

public class SqlSource {

	private String text;

	/**
	 * 负责解析#{}和${}，并最终将解析之后的SQL语句和对应的参数映射信息组合到BoundSql类中存储
	 * 
	 * @param parameterObject
	 * @param configuration
	 * @return
	 */
	public BoundSql getBoundSql(MappedStatement mappedStatement, Configuration configuration, Object parameterObject) {
		ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler(configuration,
				mappedStatement.getParameterTypeClass());
		GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", handler);
		String sql = tokenParser.parse(text);

		return new BoundSql(sql, handler.getParameterMappings(), parameterObject);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public SqlSource(String text) {
		super();
		this.text = text;
	}

}
