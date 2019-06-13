package com.kkb.mybatis.framework.sqlsession;

import java.util.List;

import com.kkb.mybatis.framework.builder.Configuration;
import com.kkb.mybatis.framework.builder.MappedStatement;

public class DefaultSqlSession implements SqlSession {

	private Configuration configuration;

	public DefaultSqlSession(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public <T> T selectOne(String statementId, Object args) {
		List<T> list = this.selectList(statementId, args);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public <T> List<T> selectList(String statementId, Object args) {
		// 获取MappedStatement
		MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
		if (mappedStatement == null) {
			return null;
		}
		
		//真正执行SQL操作
		Executor executor = new SimpleExecutor();
		List<T> list = executor.execute(mappedStatement, configuration,args);
		return list;
	}

}
