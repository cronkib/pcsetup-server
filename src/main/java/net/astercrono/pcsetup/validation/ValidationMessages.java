package net.astercrono.pcsetup.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationMessages {
	private List<String> messages = new ArrayList<>();
	
	public List<String> getMessages() {
		return messages;
	}
	
	public void setMessages(List<String> message) {
		this.messages = messages;
	}
	
	public void add(String message) {
		messages.add(message);
	}
}
