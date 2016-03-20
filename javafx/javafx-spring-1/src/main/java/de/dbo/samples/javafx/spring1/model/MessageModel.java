package de.dbo.samples.javafx.spring1.model;

import java.util.Observable;

public class MessageModel extends Observable{

	private String message;
	
	public MessageModel(){
		init();
	}
	
	public void init() {
	    setMessage("Default Message!");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		if (message==null || message.equals(this.message)) return;
		this.message = message;
		setChanged();
		notifyObservers();
	}
	
	
	
}
