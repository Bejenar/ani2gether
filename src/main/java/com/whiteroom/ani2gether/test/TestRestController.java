package com.whiteroom.ani2gether.test;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestRestController {
	
	List<User> users = new ArrayList<User>();

	@GetMapping("/users")
	public List<User> getUsers(){
		return users;
	}
	
	@GetMapping("/users/{id}")
	public User findById(@PathVariable int id) {
		
		for(int i = 0; i < users.size(); i++) {
			if (id == users.get(i).getId()) {
				System.out.println(id);
				return users.get(i);
				
			}
		}
		
		return null;
	}
	
	@PostMapping("/users")
	public User addUser(@RequestBody User user, HttpServletRequest request) {
		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		if (ipAddress == null) {  
		    ipAddress = request.getRemoteAddr();  
		}
		user.setIp(ipAddress);
		user.setRID();
		users.add(user);
		
		return user;
	}
	
	@DeleteMapping("/users/delete/{id}")
	public User deletedById(@PathVariable int id) {
		
		for(int i = 0; i < users.size(); i++) {
			if (id == users.get(i).getId()) {			
				return users.remove(i);
			}
		}
		
		return null;
	}
	
	@PostMapping("/users/ban/{id}")
	public User banUser(@PathVariable int id) {
		User user = null;
		
		for(int i = 0; i < users.size(); i++) {
			if (id == users.get(i).getId()) {			
				user = users.get(i);
			}
		}
		
		if (user != null) {
			IndexController.blacklist.add(user.getIp());
		}
		
		System.out.println("blacklist: "  +  IndexController.blacklist.toString());
		
		return user;
	}
}


