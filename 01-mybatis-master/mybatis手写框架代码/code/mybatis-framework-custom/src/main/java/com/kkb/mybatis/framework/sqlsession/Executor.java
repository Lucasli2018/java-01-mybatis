package com.kkb.mybatis.framework.sqlsession;

import java.util.List;

import com.kkb.mybatis.framework.builder.Configuration;

import com.kkb.mybatis.framework.builder.MappedStatement;

public interface Executor {

	<T> List<T> execute(MappedStatement mappedStatement,Configuration configuration,Object parameterObject);
}
