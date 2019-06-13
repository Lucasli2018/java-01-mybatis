package com.kkb.mybatis.framework.builder;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.kkb.mybatis.framework.reader.DocumentReader;

public class XmlMapperBuilder {
	private InputStream inputStream;

	private Configuration configuration;

	public XmlMapperBuilder(InputStream inputStream, Configuration configuration) {
		this.inputStream = inputStream;
		this.configuration = configuration;
	}

	public void parse() {
		Document document = DocumentReader.getDocument(inputStream);

		// 解析mapper根标签
		parseMapperElement(document.getRootElement());
	}

	@SuppressWarnings("unchecked")
	private void parseMapperElement(Element rootElement) {
		String namespace = rootElement.attributeValue("namespace");
		if (namespace == null || namespace.equals("")) {
			return;
		}
		// 专门解析select子标签
		parseStatementElements(rootElement.elements("select"));
	}

	private void parseStatementElements(List<Element> elements) {
		for (Element element : elements) {
			parseStatementElement(element);
		}
	}

	private void parseStatementElement(Element element) {
		String id = element.attributeValue("id");
		String parameterType = element.attributeValue("parameterType");
		Class<?> parameterTypeClass = resolveClass(parameterType);

		String resultType = element.attributeValue("resultType");
		Class<?> resultTypeClass = resolveClass(resultType);
		String statementType = element.attributeValue("statementType") == null ? "prepared"
				: element.attributeValue("statementType");

		SqlSource sqlSource = createSqlSource(element);
		MappedStatement statement = new MappedStatement(configuration, id, parameterTypeClass, resultTypeClass,
				statementType, sqlSource);
		configuration.addMappedStatement(statement);
	}

	private SqlSource createSqlSource(Element element) {
		String text = element.getTextTrim();
		return new SqlSource(text);
	}

	private Class<?> resolveClass(String type) {
		try {
			return Class.forName(type);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
