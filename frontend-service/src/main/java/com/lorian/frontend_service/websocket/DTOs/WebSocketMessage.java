package com.lorian.frontend_service.websocket.DTOs;

import java.time.Instant;
import java.util.Objects;

public class WebSocketMessage {
	
	private String sender;
	private  String content;
	private  Instant timestamp;
	
	public WebSocketMessage() {}

	public WebSocketMessage(String sender, String content, Instant timestamp) {
		this.sender = sender;
		this.content = content;
		this.timestamp = timestamp;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "WebSocketMessage [sender=" + sender + ", content=" + content + ", timestamp=" + timestamp + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, sender, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebSocketMessage other = (WebSocketMessage) obj;
		return Objects.equals(content, other.content) && Objects.equals(sender, other.sender)
				&& Objects.equals(timestamp, other.timestamp);
	}
	
}
