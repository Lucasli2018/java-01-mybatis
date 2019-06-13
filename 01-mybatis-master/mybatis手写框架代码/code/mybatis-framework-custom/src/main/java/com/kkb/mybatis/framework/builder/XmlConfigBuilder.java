package com.kkb.mybatis.framework.builder;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import com.kkb.mybatis.framework.reader.DocumentReader;
import com.kkb.mybatis.framework.resource.Resources;

/**
 * 读取全局配置文件之后，创建Configuration对象
 * 
 * @author think
 *
 */
public class XmlConfigBuilder {

	private InputStream inputStream;

	private Configuration configuration;

	public XmlConfigBuilder(InputStream inputStream) {
		super();
		this.inputStream = inputStream;
		configuration = new Configuration();
	}

	public Configuration parse() {
		// 创建Document对象
		Document document = DocumentReader.getDocument(inputStream);

		// 解析mybatis元素
		parserConfiguration(document.getRootElement());
		return configuration;
	}

	/**
	 * 解析根标签
	 * 
	 * @param rootElement
	 */
	private void parserConfiguration(Element rootElement) {
		// 解析environments元素
		parseEnvironmentsElement(rootElement.element("environments"));
		// 解析mappers元素
		parseMappersElement(rootElement.element("mappers"));
	}

	// 解析mappers元素
	@SuppressWarnings("unchecked")
	private void parseMappersElement(Element element) {
		List<Element> mapperElements = element.elements();
		for (Element mapperElement : mapperElements) {
			String resource = mapperElement.attributeValue("resource");
			InputStream inputStream = Resources.getResourceAsStream(resource);
			XmlMapperBuilder mapperBuilder = new XmlMapperBuilder(inputStream,configuration);
			mapperBuilder.parse();
		}
	}

	// 解析environments元素,封装数据源信息
	@SuppressWarnings("unchecked")
	private void parseEnvironmentsElement(Element element) {

		// 获取默认的环境ID
		String defaultEnv = element.attributeValue("default");

		// 获取environment标签
		List<Element> elements = element.elements();
		for (Element e : elements) {
			String envId = e.attributeValue("id");
			if (defaultEnv.equals(envId)) {
				createDataSource(e);
			}
		}
	}

	// 创建数据源对象
	@SuppressWarnings("unchecked")
	private void createDataSource(Element element) {
		Element dataSourceElemet = element.element("dataSource");

		String dataSourceType = dataSourceElemet.attributeValue("type");
		List<Element> propertyElements = dataSourceElemet.elements();

		Properties properties = new Properties();
		for (Element propertyElement : propertyElements) {
			String name = propertyElement.attributeValue("name");
			String value = propertyElement.attributeValue("value");
			properties.setProperty(name, value);
		}
		
		BasicDataSource dataSource = null;
		if (dataSourceType.equals("DBCP")) {
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(properties.getProperty("driver"));
			dataSource.setUrl(properties.getProperty("url"));
			dataSource.setUsername(properties.getProperty("username"));
			dataSource.setPassword(properties.getProperty("password"));
		}
		configuration.setDataSource(dataSource);
	}
}
