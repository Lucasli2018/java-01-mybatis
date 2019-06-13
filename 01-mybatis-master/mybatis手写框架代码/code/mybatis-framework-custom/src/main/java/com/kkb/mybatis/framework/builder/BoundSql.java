package com.kkb.mybatis.framework.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用组合模式将解析之后的sql语句和参数映射信息
 * @author think
 *
 */
public class BoundSql {

	private String sql;
	
	private List<ParameterMapping> parameterMappings = new ArrayList<>();
	
	private Object parameterObject;
	
	public BoundSql(String sql, List<ParameterMapping> parameterMappings, Object parameterObject) {
		this.sql = sql;
		this.parameterMappings = parameterMappings;
		this.parameterObject = parameterObject;
	}

	public Object getParameterObject() {
		return parameterObject;
	}

	public void setParameterObject(Object parameterObject) {
		this.parameterObject = parameterObject;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<ParameterMapping> getParameterMappings() {
		return parameterMappings;
	}
	
	public void addParameterMapping(ParameterMapping parameterMapping) {
		this.parameterMappings.add(parameterMapping);
	}
}
