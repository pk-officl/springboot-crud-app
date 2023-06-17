package in.pk.springcrudapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import in.pk.springcrudapp.controllers.helper.UserHelper;
import in.pk.springcrudapp.services.UserService;

@Controller
@RequestMapping(value="/user")
public class UserContoller {
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/home")
	public ResponseEntity<String> name() {
		System.out.println("pk");
		return ResponseEntity.ok("Hello");
	}
	
	@PostMapping(value="/getUser")
	public ResponseEntity<String> getUser(HttpServletRequest request, @RequestBody String jsonString) {
		return new UserHelper(userService).getUser(request, jsonString);
	}
	
}
