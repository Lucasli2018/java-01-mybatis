package com.kkb.mybatis.framework.sqlsession;

import java.sql.PreparedStatement;

public interface TypeHandler {

	void setParameter(int i ,PreparedStatement statement, Object parameterObject);
}
