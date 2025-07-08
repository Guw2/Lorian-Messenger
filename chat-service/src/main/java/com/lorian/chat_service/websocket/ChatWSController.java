package com.lorian.chat_service.websocket;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.lorian.chat_service.websocket.DTOs.WSMessage;

@Controller
public class ChatWSController {
	
	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public WSMessage sendMessage(WSMessage message) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> sender = restTemplate.getForEntity(
				"http://localhost:8080/user/username", String.class);
		WSMessage filteredMsg = new WSMessage(sender.getBody(), message.content().concat("camelo"), message.timestamp());
		return filteredMsg;
	}

}
