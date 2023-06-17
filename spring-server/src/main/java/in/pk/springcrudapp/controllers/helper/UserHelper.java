package in.pk.springcrudapp.controllers.helper;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import in.pk.springcrudapp.entities.User;
import in.pk.springcrudapp.services.UserService;

public class UserHelper {

	private UserService userService;

	public UserHelper(UserService userService) {
		this.userService = userService;
	}

	public ResponseEntity<String> getUser(HttpServletRequest request, String jsonString) {
		try {
			if (StringUtils.isNotBlank(jsonString)) {
				JSONObject inputJson = new JSONObject(jsonString);
				String userName = inputJson.optString("username");
				User user = userService.findUserByEmail(userName);
				if (user != null) {
					JSONObject respJson = new JSONObject();
					respJson.put("username", user.getEmail());
					respJson.put("firstname", user.getFirstName());
					respJson.put("lastname", user.getLastName());
					return ResponseEntity.ok().body(respJson.toString());
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}

}
