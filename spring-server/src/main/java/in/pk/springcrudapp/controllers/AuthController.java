package in.pk.springcrudapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import in.pk.springcrudapp.controllers.helper.AuthHelper;
import in.pk.springcrudapp.services.UserService;

@CrossOrigin
@Controller
@RequestMapping(value="/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/register")
	public ResponseEntity<String> registerUser(HttpServletRequest request, @RequestBody String jsonString) {
		return new AuthHelper(userService).registerUser(request, jsonString);
	}
	
	@PostMapping(value="/login")
	public ResponseEntity<String> login(HttpServletRequest request, @RequestBody String jsonString) {
		return new AuthHelper(userService).login(request, jsonString);
	}
}
