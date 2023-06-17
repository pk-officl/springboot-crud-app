package in.pk.springcrudapp.controllers.helper;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import in.pk.springcrudapp.entities.User;
import in.pk.springcrudapp.services.UserService;

public class AuthHelper {
	
	private UserService userService;

	public AuthHelper(UserService userService) {
		this.userService = userService;
	}
	
	/***
	 * @method registerUser
	 * @author Praveen.K
	 * @param request
	 * @param jsonString
	 * @return ResponseEntity
	 */
	public ResponseEntity<String> registerUser(HttpServletRequest request, String jsonString) {
		JSONObject resJson = new JSONObject();
		try {
			if (StringUtils.isNotBlank(jsonString)) {
				JSONObject userJson = new JSONObject(jsonString);
				String email = userJson.optString("email");
				User existUser = userService.findUserByEmail(email);
				if (existUser != null) {
					return ResponseEntity.ok(resJson.put("message", "user exist already!").toString());
				}
				User user = new User();
				user.setEmail(userJson.optString("email"));
				user.setPassword(userJson.optString("password"));
				user.setFirstName(userJson.optString("firstname"));
				user.setLastName(userJson.optString("lastname"));
				User savedUser = userService.saveUser(user);
				if (savedUser != null) {
					resJson.put("message", "User created successfully" + savedUser.getFirstName());
					return ResponseEntity.ok(resJson.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(resJson.toString());
	}
	
	public ResponseEntity<String> login(HttpServletRequest request, String jsonString) {
		JSONObject resJson = new JSONObject();
		try {
			if (StringUtils.isNotBlank(jsonString)) {
				JSONObject inputJson = new JSONObject(jsonString);
				String userName = inputJson.optString("username");
				String password = inputJson.optString("password");
				User user = userService.findUserByEmail(userName);
				if (user != null) {
					if (user.getPassword().equalsIgnoreCase(password)) {
						resJson.put("username", userName);
						resJson.put("authToken", userService.generateToken(user));
						return ResponseEntity.ok(resJson.toString());
					}
					return new ResponseEntity<String>("UnAuthorized",HttpStatus.UNAUTHORIZED);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("No user registered with the given username",HttpStatus.NOT_FOUND);
	}
}
