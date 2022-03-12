package com.whiteroom.ani2gether.infrostructure.controller;

import com.whiteroom.ani2gether.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
	
	Map<Integer, User> users = new HashMap<>();

	@GetMapping("/users")
	public Collection<User> getUsers(){
		return users.values();
	}
	
	@GetMapping("/users/{id}")
	public User findById(@PathVariable int id) {
		return users.get(id);
	}
	
	@PostMapping("/users")
	public User addUser(@RequestBody User user, HttpServletRequest request) {
		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");

		if (ipAddress == null) {  
		    ipAddress = request.getRemoteAddr();  
		}
		user.setIp(ipAddress);
		user.setRID();
		users.put(user.getId(), user);
		
		return user;
	}
	
	@DeleteMapping("/users/delete/{id}")
	public User deletedById(@PathVariable int id) {
		return users.remove(id);
	}
	
	@PostMapping("/users/ban/{id}")
	public User banUser(@PathVariable int id) {
		User user = users.get(id);

		if (user != null) {
			PageController.blacklist.add(user.getIp());
		}

		log.info("blacklist: "  +  PageController.blacklist.toString());
		
		return user;
	}
}


