package com.kkb.mybatis.framework.builder;

public class ParameterMapping {

	private String propertyName;
	
	private Class<?> parameterTypeClass;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Class<?> getParameterTypeClass() {
		return parameterTypeClass;
	}

	public void setParameterTypeClass(Class<?> parameterTypeClass) {
		this.parameterTypeClass = parameterTypeClass;
	}

	public ParameterMapping(String propertyName, Class<?> parameterTypeClass) {
		super();
		this.propertyName = propertyName;
		this.parameterTypeClass = parameterTypeClass;
	}

}
