package in.pk.springcrudapp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.pk.springcrudapp.services.RedisService;
import redis.clients.jedis.Jedis;

@Service
public class SpringCrudAppApplicationStartupService {

	public void init() {
		Jedis jedis = null;
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			String ipAddress = inetAddress.getHostAddress();
			System.out.println(ipAddress);
			jedis = new Jedis(ipAddress,6379);
			Map<String, String> map = new HashMap<>();
			map.put("SYSTEM_IP", ipAddress);
			jedis.hset("SYSTEM_CONF", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
