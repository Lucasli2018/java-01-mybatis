package com.kkb.mybatis.framework.resource;

import java.io.InputStream;

public class Resources {

	public static InputStream getResourceAsStream(String resource) {
		if (resource == null || resource.equals("")) {
			return null;
		}
		InputStream inputStream = Resources.class.getClassLoader().getResourceAsStream(resource);
		return inputStream;
	}
}
