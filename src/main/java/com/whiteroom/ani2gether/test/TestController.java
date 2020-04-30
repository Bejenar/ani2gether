package com.whiteroom.ani2gether.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

	
	@GetMapping("/ajax")
	public String ajaxShow(Model model) {

		
		return "index1";
	}
	
	
	
}
