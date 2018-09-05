package com.bingo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;
@Configuration
public class PropertiesConfigUtil {
	public static final String PREFIX_CONFIG_PATH = "configs/";
	public static Map<String, Object> configs=new HashMap<String, Object>();
	public static final String FILE_CONFIG = "fileconfig.properties";
	static {
		InputStream in = null;
		String propertiesFilePath = null;
		List<String> propertiesFiles = initPropertiesFiles();
		ClassLoader loader = PropertiesConfigUtil.class.getClassLoader();
		for (int i=0;i<propertiesFiles.size();i++) {
			try {
				String propertiesFile =(String)propertiesFiles.get(i);
				Properties properties = new Properties();
				propertiesFilePath =PREFIX_CONFIG_PATH+propertiesFile;
				if (null == loader) {
					in = ClassLoader.getSystemClassLoader().getResourceAsStream(propertiesFilePath);
				} else {
					in = loader.getResourceAsStream(propertiesFilePath);
				}
				properties.load(in);
				configs.put(propertiesFile, properties);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}finally{
				if(in!=null){try{in.close();}catch(IOException e) {}}
			}
		}
	}
	
	private static List<String> initPropertiesFiles() {
		List<String> propertiesFiles =new ArrayList<>();
		propertiesFiles.add(FILE_CONFIG);
		return propertiesFiles;
	}
	/**
	 * get properties object
	 * @param fileKey
	 * @return
	 */
	public static Properties getProperties(String fileKey) {
		return (Properties)configs.get(fileKey);
	}
	/**
	 * get properties value
	 * @param fileKey: properties file name
	 * @param paramKey: properties value
	 * @return
	 */
	public static String getValue(String fileKey, String paramKey) {
		Properties properties=(Properties)configs.get(fileKey);
		return properties.getProperty(paramKey);
	}
	/**
	 * get properties value with params
	 * @param fileKey
	 * @param paramKey
	 * @param arguments
	 * @return
	 */
	public static String getValue(String fileKey, String paramKey, Object[] arguments) {
		Properties properties=(Properties)configs.get(fileKey);
		String value = properties.getProperty(paramKey);
		return MessageFormat.format(value, arguments);
	}
}
