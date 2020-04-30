package com.whiteroom.ani2gether.test;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RpgController {


	
	@MessageMapping("/mob/damage")
	  @SendTo("/topic/mob/damage")
	  public PlayerCommand damage(@RequestBody PlayerCommand command) throws Exception {

		System.out.println(command);
	    return command;
	  }
	
	@MessageMapping("/mob/add")
	  @SendTo("/topic/mob/add")
	  public String add(String note) throws Exception {

		System.out.println(note);
	    return note;
	  }
}
