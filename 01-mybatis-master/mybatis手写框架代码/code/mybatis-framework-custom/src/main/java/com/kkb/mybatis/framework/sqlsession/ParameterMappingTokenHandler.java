package com.kkb.mybatis.framework.sqlsession;

import java.util.ArrayList;
import java.util.List;

import com.kkb.mybatis.framework.builder.Configuration;
import com.kkb.mybatis.framework.builder.ParameterMapping;

public class ParameterMappingTokenHandler implements TokenHandler {
	private List<ParameterMapping> parameterMappings = new ArrayList<>();
	private Class<?> parameterTypeClass;

	public ParameterMappingTokenHandler(Configuration configuration, Class<?> parameterTypeClass) {
		this.parameterTypeClass = parameterTypeClass;
	}

	// context是#{}
	@Override
	public String handleToken(String content) {
		parameterMappings.add(buildParameterMapping(content));
		return "?";
	}

	private ParameterMapping buildParameterMapping(String content) {
		ParameterMapping parameterMapping = new ParameterMapping(content, parameterTypeClass);
		return parameterMapping;
	}

	public List<ParameterMapping> getParameterMappings() {
		return parameterMappings;
	}

	public void setParameterMappings(List<ParameterMapping> parameterMappings) {
		this.parameterMappings = parameterMappings;
	}

	public Class<?> getParameterTypeClass() {
		return parameterTypeClass;
	}

	public void setParameterTypeClass(Class<?> parameterTypeClass) {
		this.parameterTypeClass = parameterTypeClass;
	}

}
