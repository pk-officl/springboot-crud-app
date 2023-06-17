package in.pk.springcrudapp.constants;

import java.nio.charset.Charset;

public class SecurityConstants {
	
	private static String SECRET_KEY_TEMP = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

	public static byte[] SECRET_KEY = SECRET_KEY_TEMP.getBytes(Charset.forName("UTF-8"));
	
	public static long TOKEN_VALIDITY = 60*12*1000;
	
}
