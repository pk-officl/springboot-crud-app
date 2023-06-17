package in.pk.springcrudapp.utils;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertyUtil {
private static Properties startUpProperties = new Properties();
	
	public static Properties getStartUpProperties() {
		if (startUpProperties == null || startUpProperties.isEmpty()) {
			loadStartupProperties();
		}
		return startUpProperties;
	}
	
	public static Properties loadStartupProperties() {
		try {
			if (startUpProperties == null || startUpProperties.isEmpty()) {
				try (InputStream in = new FileUtil().getResource("application.properties")) {
					if (in != null) {
						startUpProperties = new Properties();
						startUpProperties.load(in);
					}
				}
				if (startUpProperties == null || startUpProperties.isEmpty()) {
					startUpProperties = PropertiesLoaderUtils.loadAllProperties("application.properties");
			}
			}
		} catch (Exception e) {
			System.out.println("Failed to process startup properties file: " + e);
		}
		return startUpProperties;
	}
}
