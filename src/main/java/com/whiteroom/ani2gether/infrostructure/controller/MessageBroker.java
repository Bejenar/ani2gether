package com.whiteroom.ani2gether.infrostructure.controller;

import com.whiteroom.ani2gether.domain.model.UserCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
public class MessageBroker {

	@MessageMapping("/note/event")
	  @SendTo("/topic/note/event")
	  public UserCommand event(@RequestBody UserCommand command) throws Exception {

		log.info(String.valueOf(command));

	    return command;
	  }
	
	@MessageMapping("/note/add")
	  @SendTo("/topic/note/add")
	  public String add(String note) throws Exception {

		log.info(String.valueOf(note));

	    return note;
	  }
	
	@MessageMapping("/note/users")
	  @SendTo("/topic/note/users")
	  public String userMsg(String note) throws Exception {

		log.info(String.valueOf(note));

	    return note;
	  } 
}
