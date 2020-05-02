package com.whiteroom.ani2gether.test;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class IndexController {

	public static Set<String> blacklist = new HashSet<String>(); 
	
	@GetMapping("/")
	public String index(Model model) {
		
		User user = new User();
		
		model.addAttribute("user", user);
		
		return "index";
	}
	@PostMapping("/room")
	public String room(@ModelAttribute User user, Model model, HttpServletRequest request) {
		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		if (ipAddress == null) {  
		    ipAddress = request.getRemoteAddr();  
		}
		if (blacklist.contains(ipAddress)) {
			return "index";
		}
		
		System.out.println(user + " - " + ipAddress);
		return "room";
	}
}
