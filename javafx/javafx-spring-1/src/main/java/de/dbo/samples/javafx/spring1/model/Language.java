package de.dbo.samples.javafx.spring1.model;

public enum Language{
	
	EN("en"), RO("ro"), DE("de");
	
	private String value;
	
	private Language(String s){
		value=s;
	}
	
	public String getValue(){
		return value;
	}
	
}
