package commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {
	private final Properties properties;
	private final String propertyFilePath = GlobalConstants.PROJECT_PATH + File.separator+ "resources"+ File.separator+"config.properties";
	
	private static PropertiesConfig configLoader;
	
	private PropertiesConfig() {
		properties = PropertiesConfig.propertyLoader(propertyFilePath);
	}

	private static Properties propertyLoader(String propertyFilePath) {
		Properties properties = new Properties();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			try {
				properties.load(reader);
				reader.close();
			}catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new RuntimeException("Failed to load properties file "+propertyFilePath);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Configuration properties not found at "+propertyFilePath);
		}
		return null;
	}
	
	public static PropertiesConfig getFileConfigReader() {
		if (configLoader == null) {
			configLoader = new PropertiesConfig();
		}
		return configLoader;
	}
	
	public long getLongTimeout() {
		String longTimeout = properties.getProperty("LongTimeOut");
		if(longTimeout != null)
			return Long.parseLong(longTimeout);
		else 
			throw new RuntimeException("Long timeout not found in Config file");
	}
	
	public long getShortTimeout() {
		String shortTimeout = properties.getProperty("ShortTimeOut");
		if(shortTimeout != null)
			return Long.parseLong(shortTimeout);
		else 
			throw new RuntimeException("Short timeout not found in Config file");
	}
	
	public String getUserURL() {
		String url = properties.getProperty("UserURL");
		if(url != null)
			return url;
		else 
			throw new RuntimeException("User URL not found in Config file");
	}
	
	public String getAdminURL() {
		String url = properties.getProperty("AdminURL");
		if(url != null)
			return url;
		else 
			throw new RuntimeException("Admin URL not found in Config file");
	}
}
