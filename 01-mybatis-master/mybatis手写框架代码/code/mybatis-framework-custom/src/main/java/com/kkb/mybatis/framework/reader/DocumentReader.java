package com.kkb.mybatis.framework.reader;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class DocumentReader {

	public static Document getDocument(InputStream inputStream) {
		Document document = null;
		try {
			SAXReader reader = new SAXReader();
			document = reader.read(inputStream);
		} catch (Exception e) {
		}
		return document;
	}

}
