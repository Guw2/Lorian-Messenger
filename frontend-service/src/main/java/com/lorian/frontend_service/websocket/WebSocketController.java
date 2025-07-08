package com.lorian.frontend_service.websocket;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.lorian.frontend_service.websocket.DTOs.WebSocketMessage;

@Controller
public class WebSocketController {

	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public WebSocketMessage message(WebSocketMessage msg, Principal principal) {
		String username = principal.getName();
		msg.setSender(username);
		return msg;
	}
	
}
