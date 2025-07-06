package com.lorian.chat_service.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.lorian.chat_service.websocket.DTOs.WSMessage;

@Controller
public class ChatWSController {
	
	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public WSMessage message(WSMessage message) {
		return message;
	}

}
