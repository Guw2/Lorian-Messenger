package com.lorian.chat_service.websocket.DTOs;

import java.time.Instant;

public record WSMessage(String sender, String content, Instant timestamp) {

}
