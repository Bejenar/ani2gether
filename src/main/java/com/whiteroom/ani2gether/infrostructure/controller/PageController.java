package com.whiteroom.ani2gether.infrostructure.controller;

import com.whiteroom.ani2gether.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Controller
public class PageController {

	public static Set<String> blacklist = new HashSet();
	
	@GetMapping
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

		log.info(user + " - " + ipAddress);

		return "room";
	}
}
