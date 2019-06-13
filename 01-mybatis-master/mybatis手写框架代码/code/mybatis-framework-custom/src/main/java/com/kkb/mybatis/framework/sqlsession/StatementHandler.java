package com.kkb.mybatis.framework.sqlsession;

import java.sql.ResultSet;
import java.sql.Statement;

import com.kkb.mybatis.framework.builder.BoundSql;

public interface StatementHandler {

	Statement getStatement(String sql);
	
	void setParameter(Statement statement, BoundSql boundSql);
	
	ResultSet execute(Statement statement);
}
