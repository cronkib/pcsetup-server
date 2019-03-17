package net.astercrono.pcsetup.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidationMessages {
	private List<String> messages = new ArrayList<>();
	
	public ValidationMessages() { }
	
	public ValidationMessages(String...messages) {
		this.messages.addAll(Arrays.asList(messages));
	}
	
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
