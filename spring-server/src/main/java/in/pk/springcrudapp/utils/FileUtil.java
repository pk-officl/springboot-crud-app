package in.pk.springcrudapp.utils;

import java.io.InputStream;
import java.util.Properties;

public class FileUtil {

	public InputStream getResource(String string) {
		Properties props = new Properties();
		try {
			props.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
