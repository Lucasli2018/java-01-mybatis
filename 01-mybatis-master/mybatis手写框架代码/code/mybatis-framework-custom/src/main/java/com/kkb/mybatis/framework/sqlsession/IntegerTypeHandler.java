package com.kkb.mybatis.framework.sqlsession;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IntegerTypeHandler implements TypeHandler {

	// public boolean

	@Override
	public void setParameter(int i,PreparedStatement statment,Object parameterObject) {
		
		try {
			statment.setInt(i,(int) parameterObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
